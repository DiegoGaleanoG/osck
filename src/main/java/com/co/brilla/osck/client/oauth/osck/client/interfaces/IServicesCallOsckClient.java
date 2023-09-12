package com.co.brilla.osck.client.oauth.osck.client.interfaces;

import com.co.brilla.osck.client.oauth.osck.client.dto.OauthResponseDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.OsckCallPackageDto;
import retrofit2.Call;
import retrofit2.http.*;

public interface IServicesCallOsckClient {
    @POST("sync")
    Call<OauthResponseDto> SetPackage(@Header("Authorization") String token, @Body OsckCallPackageDto body);

}
