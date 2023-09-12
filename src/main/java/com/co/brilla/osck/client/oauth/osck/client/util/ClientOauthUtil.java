package com.co.brilla.osck.client.oauth.osck.client.util;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ClientOauthUtil {

    public String generateAuthorizationCode(String clientId, String clientSecret){
       return "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
    }
}
