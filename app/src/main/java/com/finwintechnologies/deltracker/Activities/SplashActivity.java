package com.finwintechnologies.deltracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.finwintechnologies.deltracker.BuildConfig;
import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Utilities.LocalPreferences;
import com.finwintechnologies.deltracker.WebService.APIClient;
import com.finwintechnologies.deltracker.WebService.ApiService;
import com.finwintechnologies.deltracker.WebService.ResponseCheckVersion;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.finwintechnologies.deltracker.Utilities.Constants.database;

public class SplashActivity extends AppCompatActivity {
    String versionName;
    int verCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        versionName = BuildConfig.VERSION_NAME;
        verCode = BuildConfig.VERSION_CODE;
        TextView tvver=findViewById(R.id.tvversion);
        tvver.setText(""+versionName);
        dofetchVersion();

    }
    private void dofetchVersion() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("app_type","Delivery App");
        ApiService apiService= APIClient.getClient().create(ApiService.class);
        Call<ResponseCheckVersion> call=apiService.doFetchVersionControl(database,jsonObject);
        call.enqueue(new Callback<ResponseCheckVersion>() {
            @Override
            public void onResponse(Call<ResponseCheckVersion> call, Response<ResponseCheckVersion> response) {
                if (response.body()!=null&&response.code()==200){
                    ResponseCheckVersion responseCheckVersion=response.body();

                    int apiversioncode=Integer.parseInt(responseCheckVersion.getVersionCode());
                    if (verCode<apiversioncode){
                        startActivity(new Intent(getApplicationContext(),DemoSplash.class));
                        finish();
                    }else{
                        setLogin();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckVersion> call, Throwable t) {

            }
        });
    }

    private void setLogin() {
        final boolean isloggedin=  LocalPreferences.retrieveBooleanPreferences(getApplicationContext(),"isloggedin");

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                if (isloggedin){
                    Intent i = new Intent(SplashActivity.this, TabActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 1000);
    }


}