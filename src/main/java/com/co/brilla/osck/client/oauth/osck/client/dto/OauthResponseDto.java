package com.co.brilla.osck.client.oauth.osck.client.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthResponseDto {
    private String access_token;
    private String token_type;
    private Long Bearer;
    private Long expires_in;
    private String scope;
}
