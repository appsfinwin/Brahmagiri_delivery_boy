package com.finwintechnologies.deltracker.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.finwintechnologies.deltracker.Adapters.OrderdetailAdapter;
import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Responses.LineItem;
import com.finwintechnologies.deltracker.Responses.ResponseOrderDetails;
import com.finwintechnologies.deltracker.Utilities.LocalPreferences;
import com.finwintechnologies.deltracker.WebService.APIClient;
import com.finwintechnologies.deltracker.WebService.ApiService;
import com.finwintechnologies.deltracker.databinding.ActivityOrderDetailsBinding;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.finwintechnologies.deltracker.Utilities.Constants.database;

public class OrderDetails extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;
    ProgressDialog progressDialog;

    String selectedid, billid;
    String consumerName,Consumermob,consumerAdderss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_order_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        binding.recyvmyorderdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        String orderid = getIntent().getStringExtra("id");
        billid = getIntent().getStringExtra("billid");
        consumerName = getIntent().getStringExtra("consumerName");
        Consumermob = getIntent().getStringExtra("Consumermob");
        consumerAdderss = getIntent().getStringExtra("consumerAdderss");
        binding.tvCustomerName.setText(consumerName);
        binding.tvCustomerMobile.setText(Consumermob);
        binding.tvCustomerAddress.setText(consumerAdderss);
        binding.btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkpermisiion(Consumermob);
            }
        });








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
               if( binding.rdCash.isChecked()){
                   doMarkcashollected("cash");
               }else if( binding.rdioupi.isChecked()){
                   doMarkcashollected("digi");
               }else{
                   Toast.makeText(getApplicationContext(),"Please choose collection option",Toast.LENGTH_SHORT).show();

                }

            }
        });

        Log.e("onCreate", "onCreate:" + orderid);
        Log.e("onCreate", "onCreate:" + getIntent().getExtras());


    }

    private void checkpermisiion(final String no) {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted, open the camera
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+no));//change the number
                        startActivity(callIntent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();





    }

    private void doMarkcashollected(String paymode) {
        {
            progressDialog.show();
            JsonObject student1 = new JsonObject();
            student1.addProperty("bill_id", Integer.parseInt(billid));
            student1.addProperty("pay_mode", paymode);
            String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
            ApiService apiService = APIClient.getClient().create(ApiService.class);
            Call<JsonObject> call = apiService.doMarkCashcollected(mAccesstoken, database, student1);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    progressDialog.dismiss();
                    if (response.body() != null && response.code() == 200) {


                        try {
                            JSONObject json = new JSONObject(response.body().toString());
                            String status = json.getString("status");
                            String msg = json.getString("message");
                            Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                            if (status.equalsIgnoreCase("cash_received_by_boy")){
                                startActivity(new Intent(getApplicationContext(),TabActivity.class));
                                finishAffinity();
                            }else{
                                Toast.makeText(getApplicationContext(), " Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }


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
    }

    private void domovetocustomer(final String billid) {
        progressDialog.show();
        JsonObject student1 = new JsonObject();
        student1.addProperty("bill_id", Integer.parseInt(billid));
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.doupdateDelivered(mAccesstoken, database, student1);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.body() != null && response.code() == 200) {
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        String msg = json.getString("message");
                        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
                        binding.cashlayt.setVisibility(View.VISIBLE);
                        binding.deliverylayt.setVisibility(View.GONE);

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
        Call<JsonObject> call = apiService.doReturnoutlet(mAccesstoken, database, student1);
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
        Call<JsonObject> call = apiService.doUnabletodeliver(mAccesstoken, database, student1);
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
        Call<ResponseOrderDetails> call = apiService.dofetchorderdetails(mAccesstoken, database, student1);
        call.enqueue(new Callback<ResponseOrderDetails>() {
            @Override
            public void onResponse(Call<ResponseOrderDetails> call, Response<ResponseOrderDetails> response) {
                if (response.body() != null && response.code() == 200) {
                    ResponseOrderDetails responseOrderDetails = response.body();
                    List<LineItem> dataset = responseOrderDetails.getLineItems();
                    binding.tvdelchrg.setText("₹ "+response.body().getDelivery_charges());
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
