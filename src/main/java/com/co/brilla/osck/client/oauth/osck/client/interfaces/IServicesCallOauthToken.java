package com.co.brilla.osck.client.oauth.osck.client.interfaces;

import com.co.brilla.osck.client.oauth.osck.client.dto.Oauth.OauthResponseDto;
import com.co.brilla.osck.client.oauth.osck.client.util.ConstantsCode;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IServicesCallOauthToken {
    @POST("client")
    @FormUrlEncoded
    Call<OauthResponseDto> getTokenOauht(@Header(ConstantsCode.AUTHORIZATION) String token, @Field(ConstantsCode.GRANTTYPE) String grantType, @Field(ConstantsCode.SCOPE) String scope);

}
