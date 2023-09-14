package com.co.brilla.osck.client.oauth.osck.client.controller;

import com.co.brilla.osck.client.oauth.osck.client.dto.Oauth.OauthClientRequestDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.osck.OsckClientRequestDto;
import com.co.brilla.osck.client.oauth.osck.client.interfaces.IOauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}")
public class ClientController {
    @Autowired
    private IOauthClient iOauthClient;

    @PostMapping("/get_token")
    public ResponseEntity<?> getToken(@RequestBody OauthClientRequestDto oauthClientRequestDto){
        return iOauthClient.getAccessToken(oauthClientRequestDto.getClientId(),oauthClientRequestDto.getClientSecret(),oauthClientRequestDto.getScope(),oauthClientRequestDto.getGrantType());
    }

    @PostMapping("/call_ock_service")
    public ResponseEntity<?> callOckSservice(@RequestBody OsckClientRequestDto osckClientRequestDto){
        return iOauthClient.callOsckPackage(osckClientRequestDto);
    }
}
