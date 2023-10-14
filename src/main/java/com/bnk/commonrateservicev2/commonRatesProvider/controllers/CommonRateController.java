package com.bnk.commonrateservicev2.commonRatesProvider.controllers;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService.InfoToGetRates;
import com.bnk.commonrateservicev2.commonRatesProvider.services.TimePathService;
import com.bnk.commonrateservicev2.rater.services.FormCommonRateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommonRateController {

    private final TimePathService timePathService;
    private final FormCommonRateService formCommonRateService;

//    @GetMapping("/optimal")
//    public ResponseEntity<?> setPoints(@RequestBody Coordinates coordinates) throws JsonProcessingException {
//        return ResponseEntity.ok(rateService.getRatesByRoads(coordinates));
//    }


    @GetMapping("/offices/optimal")
    public ResponseEntity<?> getCommonRates(@RequestBody InfoToGetRates infoToGetRates){
        log.info("getCommonRates officesIds:{}", infoToGetRates.getId());
        Map<Long, Double> commonRatesMap;
        try {
            Map<Long, Long> idsToTime = timePathService.getRatesByRoads(infoToGetRates);
            commonRatesMap = formCommonRateService.getCommonRatesMap(idsToTime,
                    infoToGetRates.getServiceType(),
                    infoToGetRates.getMoveType());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(commonRatesMap);
    }
}
