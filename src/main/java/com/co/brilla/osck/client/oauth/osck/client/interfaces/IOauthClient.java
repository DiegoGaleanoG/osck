package com.co.brilla.osck.client.oauth.osck.client.interfaces;

import com.co.brilla.osck.client.oauth.osck.client.dto.osck.OsckClientRequestDto;
import org.springframework.http.ResponseEntity;

public interface IOauthClient {
    public ResponseEntity<?> getAccessToken(String clientId, String clientSecret, String scope, String grantType);

    public ResponseEntity<?> callOsckPackage(OsckClientRequestDto osckClientRequestDto);
}
