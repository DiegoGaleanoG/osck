package com.co.brilla.osck.client.oauth.osck.client.dto.osck;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OsckCallPackageDto {
    private String apiName;
    private ParametersDto[] parameters;
}
