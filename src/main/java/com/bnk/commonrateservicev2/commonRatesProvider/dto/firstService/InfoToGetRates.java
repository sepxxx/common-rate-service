package com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.Parser.Coords;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
public class InfoToGetRates {
    private List<Coords> coords;
    private List<Integer> id;
    private Coords userGeo;
    private String moveType;
    private String serviceType;

    public InfoToGetRates() {
    }

    @JsonCreator
    public InfoToGetRates(@JsonProperty("cords") List<Coords> coords,
                       @JsonProperty("id") List<Integer> id,
                       @JsonProperty("user_geo") Coords userGeo,
                       @JsonProperty("move_type") String moveType,
                       @JsonProperty("service_type") String serviceType){
        this.coords = coords;
        this.id = id;
        this.userGeo = userGeo;
        this.moveType = moveType;
        this.serviceType = serviceType;
    }


}
