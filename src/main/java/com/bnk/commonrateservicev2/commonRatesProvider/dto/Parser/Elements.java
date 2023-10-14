package com.bnk.commonrateservicev2.commonRatesProvider.dto.Parser;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Elements {
    private Distance distance;
    private Duration duration;
    private String status;
    public Elements(){

    }
}
