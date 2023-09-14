package com.co.brilla.osck.client.oauth.osck.client.interfaces;

import com.co.brilla.osck.client.oauth.osck.client.dto.osck.OsckCallPackageDto;
import com.co.brilla.osck.client.oauth.osck.client.util.ConstantsCode;
import retrofit2.Call;
import retrofit2.http.*;

public interface IServicesCallOsckClient {
    @POST("sync")
    Call<OsckCallPackageDto> SetPackage(@Header(ConstantsCode.AUTHORIZATION) String token, @Body OsckCallPackageDto body);

}
