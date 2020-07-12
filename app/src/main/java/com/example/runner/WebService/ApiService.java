
package com.example.runner.WebService;



import com.example.runner.Activities.ResponseToken;
import com.example.runner.ResponseLogin;
import com.example.runner.Responses.ResponseFetchOrder;
import com.example.runner.Responses.ResponseOrderDetails;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("authtoken/get_token")
    Call<ResponseLogin>dologinoutlet(@Field("db")String db, @Field("login")String login, @Field("password")String password);

    @POST("active/delivery_boy")
    Call<JsonObject>doofflineonline(@Header("Access-Token") String Access_Token,
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







}

