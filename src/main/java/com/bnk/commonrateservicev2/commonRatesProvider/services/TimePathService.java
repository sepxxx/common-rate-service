package com.bnk.commonrateservicev2.commonRatesProvider.services;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService.Container;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService.InfoToGetRates;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TimePathService {

    private Map<Long, Long> times;
    private final Parser parser;

    public Map<Long, Long> getRatesByRoads(InfoToGetRates infoToGetRates) throws JsonProcessingException {
        times = new HashMap<>();
        for (int i = 0; i < infoToGetRates.getId().size(); i++){
            Container container = parser.request(
                    infoToGetRates.getCoords().get(i).getLat(),
                    infoToGetRates.getCoords().get(i).getLng(),
                    infoToGetRates.getUserGeo().getLat(),
                    infoToGetRates.getUserGeo().getLng(),
                    infoToGetRates.getId().get(i),
                    infoToGetRates.getMoveType()
            );
            times.put(Long.valueOf(infoToGetRates.getId().get(i)),
                    Long.valueOf(container.getRows().get(0)
                            .getElements().get(0)
                            .getDuration().getValue())
            );
        }
       return times;
    }



}
