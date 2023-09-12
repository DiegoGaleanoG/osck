package com.co.brilla.osck.client.oauth.osck.client.services;

import com.co.brilla.osck.client.oauth.osck.client.dto.OauthResponseDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.OsckCallPackageDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.OsckClientRequestDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.ParametersDto;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IOauthClient;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IServicesCallOauthToken;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IServicesCallOsckClient;
import com.co.brilla.osck.client.oauth.osck.client.util.ClientOauthUtil;
import com.co.brilla.osck.client.oauth.osck.client.util.ConstantsCode;
import com.co.brilla.osck.client.oauth.osck.client.util.MapsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
                return new ResponseEntity<>(response.body().getAccess_token(),HttpStatus.OK);
            } else {
                log.info("Error al consumir el servicio getAccesToken");
                new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            log.error("Error Call getAccessToken()  + " + e.getMessage());
            return new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> callOsckPackage(OsckClientRequestDto osckClientRequestDto) {
        try {
            String xmlObject = this.creatXmlObject(osckClientRequestDto);
            OsckCallPackageDto osckCallPackageDto = this.createOsckCallPackageDto(osckClientRequestDto, xmlObject);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OSCK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IServicesCallOsckClient iServicesCallOsckClient = retrofit.create(IServicesCallOsckClient.class);


            Call<OsckCallPackageDto> call = iServicesCallOsckClient.SetPackage(osckClientRequestDto.getApiKey(), osckCallPackageDto);
            Response<OsckCallPackageDto> response = call.execute();

            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch(Exception e){
            log.error("Error Call callOsckPackage()  + " + e.getMessage());
            return new ResponseEntity<>("error al consumir el servicio", HttpStatus.BAD_REQUEST);
        }
    }

    public String creatXmlObject(OsckClientRequestDto objectDto) throws JsonProcessingException {
        Map<String,Object> mapsDto = MapsDto.maps();
        ModelMapper modelMapper = new ModelMapper();

        Object getMap = mapsDto.get(objectDto.getApiName());
        Object map = modelMapper.map(objectDto.getBodyRequestPackage(),getMap.getClass());

        XmlMapper xmlMapper = new XmlMapper();

        String xml = xmlMapper.writeValueAsString(map);

        xml = xml.replace(map.getClass().getSimpleName(), ConstantsCode.XML);

        return xml;
    }

    public OsckCallPackageDto createOsckCallPackageDto(OsckClientRequestDto osckClientRequestDto, String xml){
        OsckCallPackageDto osckCallPackageDtoResponse = new OsckCallPackageDto();
        ParametersDto parametersDto []= new ParametersDto[1];
        parametersDto[0]=new ParametersDto(ConstantsCode.ICLDATA,xml);

        osckCallPackageDtoResponse.setApiName(osckClientRequestDto.getApiName());
        osckCallPackageDtoResponse.setParameters(parametersDto);

        return osckCallPackageDtoResponse;
    }
}
