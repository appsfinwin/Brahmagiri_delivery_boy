package com.finwintechnologies.deltracker.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.finwintechnologies.deltracker.Adapters.LedgerAdapter;
import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Responses.Ledger;
import com.finwintechnologies.deltracker.Responses.ResponseLedger;
import com.finwintechnologies.deltracker.Utilities.LocalPreferences;
import com.finwintechnologies.deltracker.WebService.APIClient;
import com.finwintechnologies.deltracker.WebService.ApiService;
import com.finwintechnologies.deltracker.databinding.FragmentCompletedBinding;
import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.finwintechnologies.deltracker.Utilities.Constants.database;

public class CompletedFragment extends Fragment {
    FragmentCompletedBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mYear, mMonth, mDay, mHour, mMinute;
    String startdate,enddate;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompletedFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_completed, container, false);
        binding.recyvledger.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.btnStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                binding.btnStartdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
            });

        binding.btnEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                binding.btnEnddate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

      binding.btnShow.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              enddate=binding.btnEnddate.getText().toString();
              startdate=binding.btnStartdate.getText().toString();
              if (startdate.equalsIgnoreCase("Start Date")){

              }else if (enddate.equalsIgnoreCase("End Date")){

              }else{
                  doFetchLedger(startdate,enddate);

              }
          }
      });
        return binding.getRoot();

    }

    private void doFetchLedger(String startdate, String enddate) {
        String mAccesstoken = LocalPreferences.retrieveStringPreferences(getActivity(), "Accesstoken");
        String id = LocalPreferences.retrieveStringPreferences(getActivity(), "partnerid");
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("delivery_boy_id",Integer.parseInt(id));
        jsonObject.addProperty("from_date",startdate);
        jsonObject.addProperty("to_date",enddate);

        ApiService apiService= APIClient.getClient().create(ApiService.class);
        Call<ResponseLedger>call=apiService.doFetchLedger(mAccesstoken,database,jsonObject);
        call.enqueue(new Callback<ResponseLedger>() {
            @Override
            public void onResponse(Call<ResponseLedger> call, Response<ResponseLedger> response) {
                if ( response.body()!=null&&response.code()==200){
                    ResponseLedger responseLedger=response.body();
                    List<Ledger>dataset=responseLedger.getLedger();
                    if (dataset!=null&&!dataset.isEmpty()) {
                        binding.recyvledger.setAdapter(new LedgerAdapter(getActivity(), dataset));
                    }else{
                        Toast.makeText(getActivity(),"No data Found",Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseLedger> call, Throwable t) {

            }
        });
    }
}
