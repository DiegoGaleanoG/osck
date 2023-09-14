package com.co.brilla.osck.client.oauth.osck.client.dto.osck;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParametersDto {
    private String parameterName;
    private String parameterValue;

    public ParametersDto(String iclData, String s) {
        this.parameterName=iclData;
        this.parameterValue=s;
    }
}
