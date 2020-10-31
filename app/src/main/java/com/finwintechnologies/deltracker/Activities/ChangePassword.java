package com.finwintechnologies.deltracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Utilities.LocalPreferences;
import com.finwintechnologies.deltracker.WebService.APIClient;
import com.finwintechnologies.deltracker.WebService.ApiService;
import com.finwintechnologies.deltracker.databinding.ActivityChangePasswordBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.finwintechnologies.deltracker.Utilities.Constants.database;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_changepassword);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_password);
        binding.btnPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=binding.edPwd.getText().toString();
                String edcpass=binding.edCpasswd.getText().toString();
                if(pass.equals("")){

                }else if (edcpass.equals("")){

                }else if(!pass.equals(edcpass)){
                    Toast.makeText(ChangePassword.this, "", Toast.LENGTH_SHORT).show();
                }else{
                    doChangePass(pass,edcpass);
                }
            }
        });

    }

    private void doChangePass(String pass, String edcpass) {

       String userid= LocalPreferences.retrieveStringPreferences(getApplicationContext(),"userid");

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("enter_password",pass);
        jsonObject.addProperty("confirm_password",edcpass);
        jsonObject.addProperty("user_id",Integer.parseInt(userid));


        ApiService apiService= APIClient.getClient().create(ApiService.class);
        Call<JsonObject>call=apiService.doChangepwd(database,jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.body()!=null&&response.code()==200){

                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        String  msg = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), TabActivity.class));
                        finish();
                        finishAffinity();

//
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void onBack(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}