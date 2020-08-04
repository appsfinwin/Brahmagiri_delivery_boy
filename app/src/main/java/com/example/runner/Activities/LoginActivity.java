package com.example.runner.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.runner.R;
import com.example.runner.ResponseLogin;
import com.example.runner.Utilities.LocalPreferences;
import com.example.runner.WebService.APIClient;
import com.example.runner.WebService.ApiService;
import com.example.runner.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=binding.edUsername.getText().toString().trim();
                String password=binding.edPass.getText().toString().trim();
                if (username.equals("")){
                    binding.textinputusername.setError("This field is empty");
                }else if (password.equals("")){
                    binding.textinputpasswd.setError("This field is empty");
                }else{
                    doLogin(username,password);
                }

            }
        });

    }

    public void tologin(View view) {
       // startActivity(new Intent(getApplicationContext(), TabActivity.class));
    }

    private void doLogin(String username, String password) {
        ApiService apiService= APIClient.getClient().create(ApiService.class);
        Call<ResponseLogin> call=apiService.dologinoutlet("test",username,password,"delivery_boy");
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response!=null&&response.code()==200){
                    String mAccesstoken=response.body().getAccessToken();
                    if (mAccesstoken!=null){
                        LocalPreferences.storeStringPreference(getApplicationContext(),"Accesstoken",mAccesstoken);
                        LocalPreferences.storeBooleanPreference(getApplicationContext(),"isloggedin",true);
                        LocalPreferences.storeStringPreference(getApplicationContext(),"partnerid",response.body().getPartnerId().toString());
                        startActivity(new Intent(getApplicationContext(), TabActivity.class));

                    }else{
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}