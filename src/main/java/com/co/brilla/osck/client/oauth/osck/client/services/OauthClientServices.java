package com.co.brilla.osck.client.oauth.osck.client.services;

import com.co.brilla.osck.client.oauth.osck.client.dto.OauthResponseDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.OsckCallPackageDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.OsckClientRequestDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.ParametersDto;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IOauthClient;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IServicesCallOauthToken;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IServicesCallOsckClient;
import com.co.brilla.osck.client.oauth.osck.client.util.ClientOauthUtil;
import com.co.brilla.osck.client.oauth.osck.client.util.MapsDto;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.Map;

@Service
public class OauthClientServices implements IOauthClient {


    @Value("${oauth.base-url}")
    private String OAUTH_BASE_URL;

    @Value("${osck.url}")
    private String OSCK_URL;

    @Autowired
    private ClientOauthUtil clientOauthUtil;



    @Override
    public ResponseEntity<?> getAccessToken(String clientId, String clientSecret, String scope,String grantType) {
        String authorization = clientOauthUtil.generateAuthorizationCode(clientId, clientSecret);
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OAUTH_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IServicesCallOauthToken serviceCallEnrollmentApi = retrofit.create(IServicesCallOauthToken.class);

            Call<OauthResponseDto> call = serviceCallEnrollmentApi.getTokenOauht(authorization,grantType,scope);
            Response<OauthResponseDto> response = call.execute();

            if (response.isSuccessful()) {
                System.out.println("response retrofit " + response.body());
                return new ResponseEntity<>(response.body().getAccess_token(),HttpStatus.OK);
            } else {
                System.out.println("error");
                new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            System.out.println("Error Call getParam()  + " + e);
            return new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> callOsckPackage(OsckClientRequestDto osckClientRequestDto) {
        Object xmlObject = this.creatXmlObject(osckClientRequestDto);
        System.out.println(xmlObject.getClass());
        ModelMapper modelMapper = new ModelMapper();
        Object map = modelMapper.map(osckClientRequestDto.getBodyRequestPackage(),xmlObject.getClass());
try {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(OSCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    IServicesCallOsckClient iServicesCallOsckClient = retrofit.create(IServicesCallOsckClient.class);
    OsckCallPackageDto osckCallPackageDto = new OsckCallPackageDto();
    ParametersDto parametersDto []= new ParametersDto[1];
    parametersDto[0]=new ParametersDto("iclData","<XML><DESCRIPCION>PRUEBA 2</DESCRIPCION><ESTADO>Y</ESTADO></XML>");

    osckCallPackageDto.setApiName(osckClientRequestDto.getApiName());
    osckCallPackageDto.setParameters(parametersDto);
    System.out.println(osckClientRequestDto.getApiKey());
    Call<OauthResponseDto> call = iServicesCallOsckClient.SetPackage(osckClientRequestDto.getApiKey(), osckCallPackageDto);
    Response<OauthResponseDto> response = call.execute();
   System.out.println(response.message());

}catch(Exception e){
    System.out.println("Error Call getParam()  + " + e);
    return new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
}
        System.out.println(map);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    public Object creatXmlObject(OsckClientRequestDto objectDto){
        Map<String,Object> mapsDto = MapsDto.maps();
        ModelMapper modelMapper = new ModelMapper();

        Object getMap = mapsDto.get(objectDto.getApiName());
        Object map = modelMapper.map(objectDto.getBodyRequestPackage(),getMap.getClass());

        return mapsDto.get(objectDto.getApiName());
    }
}
