package com.co.brilla.osck.client.oauth.osck.client.dto.Oauth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthClientRequestDto {
    private String clientId;
    private String clientSecret;
    private String scope;
    private String grantType;

}
