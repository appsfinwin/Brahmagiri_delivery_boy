
package com.finwintechnologies.deltracker.WebService;


import com.finwintechnologies.deltracker.Activities.ResponseToken;
import com.finwintechnologies.deltracker.ResponseLogin;
import com.finwintechnologies.deltracker.Responses.ResponseFetchOrder;
import com.finwintechnologies.deltracker.Responses.ResponseLedger;
import com.finwintechnologies.deltracker.Responses.ResponseOrderDetails;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("authtoken/get_token")
    Call<ResponseLogin> dologinoutlet(@Field("db") String db, @Field("login") String login, @Field("password") String password,@Field("app_type") String app_type);

    @POST("active/delivery_boy")
    Call<JsonObject> doofflineonline(@Header("Access-Token") String Access_Token,
                                     @Header("database") String database, @Body JsonObject locationPost);

    @POST("firebase/token_save")
    Call<ResponseToken> dupushToken(@Header("Access-Token") String Access_Token,
                                    @Header("database") String database, @Body JsonObject locationPost);

    @POST("assigned/orders")
    Call<ResponseFetchOrder> doFetchOrdertodeliver(@Header("Access-Token") String Access_Token,
                                                   @Header("database") String database, @Body JsonObject locationPost);

    @POST("invoice/get_details")
    Call<ResponseOrderDetails> dofetchorderdetails(@Header("Access-Token") String Access_Token,
                                                   @Header("database") String database, @Body JsonObject locationPost);

    @POST("move/to_customer")
    Call<JsonObject> doupdateDelivered(@Header("Access-Token") String Access_Token,
                                       @Header("database") String database, @Body JsonObject locationPost);

    @POST("undelivered/no_delivery")
    Call<JsonObject> doUnabletodeliver(@Header("Access-Token") String Access_Token,
                                       @Header("database") String database, @Body JsonObject locationPost);

    @POST("return/to_outlet")
    Call<JsonObject> doReturnoutlet(@Header("Access-Token") String Access_Token,
                                    @Header("database") String database, @Body JsonObject locationPost);
    @POST("cash_receive/boy_receive")

    Call<JsonObject> doMarkCashcollected(@Header("Access-Token") String Access_Token,
                                    @Header("database") String database, @Body JsonObject locationPost);

    @POST("outlet_ledger/ledger")
    Call<ResponseLedger> doFetchLedger(@Header("Access-Token") String Access_Token,
                                       @Header("database") String database, @Body JsonObject locationPost);

    @POST("version/controller")
    Call<ResponseCheckVersion> doFetchVersionControl(@Header("database") String database, @Body JsonObject cartbody);

    @POST("change/password")
    Call<JsonObject> doChangepwd(@Header("database") String database,
                                 @Body JsonObject cartbody);
}

