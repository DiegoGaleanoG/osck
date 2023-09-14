package com.co.brilla.osck.client.oauth.osck.client.util;

import com.co.brilla.osck.client.oauth.osck.client.dto.pregistra.PeRegitraLineaDto;
import com.co.brilla.osck.client.oauth.osck.client.dto.usuarios.GetInfoUsuarioDto;

import java.util.HashMap;
import java.util.Map;

public class MapsDto {

    public static Map<String,Object> maps(){
        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put(ConstantsCode.PEPREGISTRALINEA, new PeRegitraLineaDto());
        mapObject.put(ConstantsCode.PEGETINFOUSUARIO, new GetInfoUsuarioDto());
        return mapObject;
    }
}
