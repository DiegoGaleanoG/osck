package com.co.brilla.osck.client.oauth.osck.client.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OsckClientRequestDto {
    private String apiName;
    private String apiKey;
    private Object bodyRequestPackage;


}
