package com.co.brilla.osck.client.oauth.osck.client.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParametersDto {
    private String parameterName = "iclData";
    private String parameterValue = "<XML><DESCRIPCION>PRUEBA 2</DESCRIPCION><ESTADO>Y</ESTADO></XML>";

    public ParametersDto(String iclData, String s) {
        this.parameterName=iclData;
        this.parameterValue=s;
    }
}
