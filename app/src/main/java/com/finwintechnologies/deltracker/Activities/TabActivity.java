package com.finwintechnologies.deltracker.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.finwintechnologies.deltracker.Fragments.CompletedFragment;
import com.finwintechnologies.deltracker.Fragments.HomeFragment;
import com.finwintechnologies.deltracker.Fragments.ProfileFragment;
import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Utilities.LocalPreferences;
import com.finwintechnologies.deltracker.WebService.APIClient;
import com.finwintechnologies.deltracker.WebService.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.finwintechnologies.deltracker.Utilities.Constants.database;

public class TabActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        //loading the default fragment
        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("TAG", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token

                            String token  = Objects.requireNonNull(task.getResult()).getToken();
                            doUpdateToken(token);

                            // Log and toast
                            //  String msg = getString(R.string.msg_token_fmt, token);
                            Log.d("TAG", token);
                            // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
        }




    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

           case R.id.navigation_dashboard:
                fragment = new CompletedFragment();
                break;



            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }
    private void doUpdateToken(String ftoken) {



        String AccessToekn = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "Accesstoken");

        String outid = LocalPreferences.retrieveStringPreferences(getApplicationContext(), "partnerid");
        //   String json = "{\"outlet_id\":" + Integer.parseInt(outid) + ",\"token\":" + ftoken + "}";

        // String json = "{\"outlet_id\":" + Integer.parseInt(outid) + ",\"token\":" + ftoken + "}";
        //   JsonParser parser = new JsonParser();

        //  JsonObject jsonObject = (JsonObject) parser.parse(json);
        JsonObject student1 = new JsonObject();

        student1.addProperty("outlet_id", Integer.parseInt(outid));
        student1.addProperty("token", ftoken);




        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<ResponseToken> call = apiService.dupushToken(AccessToekn, database, student1);
        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if (response.body() != null && response.code() == 200) {

                } else {
                    // Toast.makeText(HomeActivity.this, "Unable to fetch data from server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {

            }
        });
    }

}