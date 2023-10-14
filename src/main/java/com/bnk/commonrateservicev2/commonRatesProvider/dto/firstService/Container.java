package com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.Parser.Rows;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class Container {
    private List<String> destinationAddresses;
    private List<String> originAddresses;
    private List<Rows> rows;
    {
        if (destinationAddresses == null && originAddresses == null && rows == null) {
            destinationAddresses = new ArrayList<>();
            originAddresses = new ArrayList<>();
            rows = new ArrayList<>();
        }
    }

    private Long id;
    private String status;

    @JsonCreator
    public Container(@JsonProperty("destination_addresses") List<String> destination,
                        @JsonProperty("origin_addresses") List<String> origin,
                     @JsonProperty("rows") List<Rows> rows,
                     @JsonProperty String status) {
        destinationAddresses = destination;
        originAddresses = origin;
        this.rows = rows;
        this.status = status;
    }

    public Container(){

    }
}
