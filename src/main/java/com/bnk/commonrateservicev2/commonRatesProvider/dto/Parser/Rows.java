package com.bnk.commonrateservicev2.commonRatesProvider.dto.Parser;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class Rows {

    private List<Elements> elements;

    public Rows(){

    }
}
