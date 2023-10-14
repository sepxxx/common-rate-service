package com.bnk.commonrateservicev2.commonRatesProvider.dto.Parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Duration {
    private String text;
    private int value;
}
