package com.co.brilla.osck.client.oauth.osck.client.dto.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetInfoUsuarioDto {
    @JsonProperty("IDENT_TYPE")
    private int identType;
    private Long identification;

}
