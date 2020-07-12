package com.example.runner.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.runner.Activities.LoginActivity;
import com.example.runner.Adapters.ProductAdapter;
import com.example.runner.R;
import com.example.runner.Responses.OrderToDeliver;
import com.example.runner.Responses.ResponseFetchOrder;
import com.example.runner.Utilities.LocalPreferences;
import com.example.runner.WebService.APIClient;
import com.example.runner.WebService.ApiService;
import com.example.runner.databinding.FragmentHomeBinding;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String deliveryboyStatus;
    ProgressDialog mdialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        mdialog = new ProgressDialog(getActivity());
        mdialog.setMessage("Updating...");
        mdialog.setCancelable(false);
        mdialog.setCanceledOnTouchOutside(false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        boolean islogged = LocalPreferences.retrieveBooleanPreferences(getActivity(), "isonline");
        if (islogged) {
            binding.switch1.setChecked(true);
        } else {
            binding.switch1.setChecked(false);

        }

        binding.recyneworder.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    deliveryboyStatus = "True";
                    doUpdatestatus(deliveryboyStatus);

                } else {
                    // The toggle is disabled
                    deliveryboyStatus = "False";
                    doUpdatestatus(deliveryboyStatus);


                }
            }
        });

        return view;


    }

    private void doFetchOrdertodeliver() {
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getActivity(), "Accesstoken");
        String id = LocalPreferences.retrieveStringPreferences(getActivity(), "partnerid");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("partner_id", Integer.parseInt(id));
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<ResponseFetchOrder> call = apiService.doFetchOrdertodeliver(mAccesstoken, "test", jsonObject);
        call.enqueue(new Callback<ResponseFetchOrder>() {
            @Override
            public void onResponse(Call<ResponseFetchOrder> call, Response<ResponseFetchOrder> response) {
                if (response.body()!=null&&response.code()==200){
                    if (response.body().getMessage().equalsIgnoreCase("token seems to have expired or invalid")){

                    }
                    ResponseFetchOrder responseFetchOrder=response.body();
                    List<OrderToDeliver>dataset=responseFetchOrder.getOrderToDeliver();
                    binding.recyneworder.setAdapter(new ProductAdapter(getActivity(),dataset));
                    if (dataset.isEmpty()){
                        binding.emptytext.setVisibility(View.VISIBLE);

                    }else{
                        binding.emptytext.setVisibility(View.GONE);
                    }


                }else{
                    LocalPreferences.clearPreferences(getActivity());
                    Toast.makeText(getActivity(), "Session Expired ...Logged Out", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                }
            }

            @Override
            public void onFailure(Call<ResponseFetchOrder> call, Throwable t) {

            }
        });
    }

    private void doUpdatestatus(final String deliveryboyStatus) {

        mdialog.show();
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getActivity(), "Accesstoken");
        String id = LocalPreferences.retrieveStringPreferences(getActivity(), "partnerid");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("partner_id", Integer.parseInt(id));
        jsonObject.addProperty("active", deliveryboyStatus);

        ApiService apiService = APIClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.doofflineonline(mAccesstoken, "test", jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                mdialog.dismiss();

                if (response.body() != null && response.code() == 200) {
                    if (deliveryboyStatus.equalsIgnoreCase("true")) {
                        LocalPreferences.storeBooleanPreference(getActivity(), "isonline", true);
                    } else {
                        LocalPreferences.storeBooleanPreference(getActivity(), "isonline", false);

                    }
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        String msg = json.getString("message");
                        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mdialog.dismiss();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        doFetchOrdertodeliver();
    }
}