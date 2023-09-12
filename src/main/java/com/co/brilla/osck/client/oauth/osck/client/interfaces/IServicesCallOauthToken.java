package com.co.brilla.osck.client.oauth.osck.client.interfaces;

import com.co.brilla.osck.client.oauth.osck.client.dto.OauthResponseDto;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IServicesCallOauthToken {
    @POST("client")
    @FormUrlEncoded
    Call<OauthResponseDto> getTokenOauht(@Header("Authorization") String token, @Field("grant_type") String grantType, @Field("scope") String scope);

}
