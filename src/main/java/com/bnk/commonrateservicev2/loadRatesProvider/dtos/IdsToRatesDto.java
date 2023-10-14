package com.bnk.commonrateservicev2.loadRatesProvider.dtos;

import lombok.Value;

import java.util.Map;


@Value
public class IdsToRatesDto {
    Map<Long, Double> idsToRates;
}
