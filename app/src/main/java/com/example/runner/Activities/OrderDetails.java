package com.example.runner.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.runner.Adapters.OrderdetailAdapter;
import com.example.runner.R;
import com.example.runner.Responses.LineItem;
import com.example.runner.Responses.ResponseOrderDetails;
import com.example.runner.Utilities.LocalPreferences;
import com.example.runner.WebService.APIClient;
import com.example.runner.WebService.ApiService;
import com.example.runner.databinding.ActivityOrderDetailsBinding;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetails extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;
    ProgressDialog progressDialog;

    String selectedid, billid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_order_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        binding.recyvmyorderdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        String orderid = getIntent().getStringExtra("id");
        billid = getIntent().getStringExtra("billid");
        doOrderdetails(orderid);
        progressDialog = new ProgressDialog(OrderDetails.this);
        progressDialog.setMessage("Updating...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
        binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rdbtn1:
                        // TODO Something
                        binding.edcomments.setVisibility(View.GONE);
                        Log.d("onClick", "onClick: " + billid);


                        break;
                    case R.id.rdbtn2:
                        binding.edcomments.setVisibility(View.VISIBLE);

                        // TODO Something
                        break;
                    case R.id.rdbtn3:
                        binding.edcomments.setVisibility(View.GONE);

                        // TODO Something
                        break;
                }
            }
        });
        binding.btnUpdatestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edcomments.setError(null);

                if (binding.rdbtn1.isChecked()) {
                    domovetocustomer(billid);
                } else if (binding.rdbtn2.isChecked()) {
                    if (binding.edcomments.getText().toString().equals("")) {
                        binding.edcomments.setError("Please enter reason here");
                    } else {
                        doupdateundelivered(billid, binding.edcomments.getText().toString());
                    }

                }
                if (binding.rdbtn3.isChecked()) {
                    dooutletreturn(billid);

                }
            }
        });

        binding.btnCashcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doMarkcashollected();
            }
        });

        Log.e("onCreate", "onCreate:" + orderid);
        Log.e("onCreate", "onCreate:" + getIntent().getExtras());


    }

    private void doMarkcashollected() {
        {
            progressDialog.show();
            JsonObject student1 = new JsonObject();
            student1.addProperty("bill_id", Integer.parseInt(billid));
            String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
            ApiService apiService = APIClient.getClient().create(ApiService.class);
            Call<JsonObject> call = apiService.doMarkCashcollected(mAccesstoken, "test", student1);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    progressDialog.dismiss();
                    if (response.body() != null && response.code() == 200) {
                      /*  try {
                            JSONObject json = new JSONObject(response.body().toString());
                            String msg = json.getString("message");
                            Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),TabActivity.class));
                            finishAffinity();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });

        }
    }

    private void domovetocustomer(String billid) {
        progressDialog.show();
        JsonObject student1 = new JsonObject();
        student1.addProperty("bill_id", Integer.parseInt(billid));
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.doupdateDelivered(mAccesstoken, "test", student1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.body() != null && response.code() == 200) {
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        String msg = json.getString("message");
                        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TabActivity.class));
                        finishAffinity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private void dooutletreturn(String billid) {
        progressDialog.show();
        JsonObject student1 = new JsonObject();
        student1.addProperty("bill_id", Integer.parseInt(billid));
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.doReturnoutlet(mAccesstoken, "test", student1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.body() != null && response.code() == 200) {
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        String msg = json.getString("message");
                        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TabActivity.class));
                        finishAffinity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private void doupdateundelivered(String billid, String reason) {
        progressDialog.show();
        JsonObject student1 = new JsonObject();
        student1.addProperty("bill_id", Integer.parseInt(billid));
        student1.addProperty("reason", reason);

        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.doUnabletodeliver(mAccesstoken, "test", student1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.body() != null && response.code() == 200) {
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        String msg = json.getString("message");
                        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TabActivity.class));
                        finishAffinity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    private void doOrderdetails(String orderid) {
        JsonObject student1 = new JsonObject();

        student1.addProperty("invoice_id", Integer.parseInt(orderid));
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");

        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<ResponseOrderDetails> call = apiService.dofetchorderdetails(mAccesstoken, "test", student1);
        call.enqueue(new Callback<ResponseOrderDetails>() {
            @Override
            public void onResponse(Call<ResponseOrderDetails> call, Response<ResponseOrderDetails> response) {
                if (response.body() != null && response.code() == 200) {
                    ResponseOrderDetails responseOrderDetails = response.body();
                    List<LineItem> dataset = responseOrderDetails.getLineItems();
                    binding.tvSubtotal.setText("₹ " + response.body().getSubtotal());
                    binding.totalAmt.setText("₹ " + response.body().getTotalAmount());
                    binding.tvTaxamt.setText("₹ " + response.body().getTaxAmount());
                    binding.invoiceId.setText("Invoice Id : " + response.body().getInvoiceNumber());
                    String paymode = response.body().getPayment_mode();
                    if (paymode.equalsIgnoreCase("cod")) {
                        binding.btnCashcollect.setVisibility(View.VISIBLE);

                    } else {
                        binding.btnCashcollect.setVisibility(View.INVISIBLE);

                    }
                    binding.recyvmyorderdetails.setAdapter(new OrderdetailAdapter(getApplicationContext(), dataset));
                }

            }

            @Override
            public void onFailure(Call<ResponseOrderDetails> call, Throwable t) {

            }
        });
    }


}
