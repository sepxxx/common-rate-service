package com.bnk.commonrateservicev2.rater.services;


import com.bnk.commonrateservicev2.loadRatesProvider.services.GetLoadRatesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FormCommonRateService {
    GetLoadRatesService getLoadRatesService;

    public Map<Long, Double> getCommonRatesMap(Map<Long, Long> idsToTime, String faceType, String moveType) {
        log.info("getCommonRatesMap idsToTime:{} faceType:{}", idsToTime, faceType);
        Map<Long, Double> commonRatesMap = getLoadRatesService.getLoadRates(
                idsToTime.keySet().stream().toList(),
                faceType
        );
        //commonRatesMap содердит loadRatesMap,
        //нужно его пересчитать с учетом времени

        for(Long id:idsToTime.keySet()) {
            Double loadRate = commonRatesMap.get(id);
            Long timeInSecs = idsToTime.get(id);
            Double timeRate = formRateForTime(timeInSecs, moveType);
            log.info("getCommonRatesMap timeRate:{}", timeRate);
            commonRatesMap.put(id, (loadRate+timeRate)/2);
        }
        log.info("getCommonRatesMap commonRatesMap:{}", commonRatesMap);
        return commonRatesMap;
    }

    public Double formRateForTime(Long timeInSecs, String moveType) {
        double timeRate;
        if(Objects.equals(moveType, "walking")) {
            if (timeInSecs <= 360) {
                timeRate = 100;
            } else if (timeInSecs <= 720) {
                timeRate = 70;
            } else if (timeInSecs <= 1440) {
                timeRate = 50;
            } else {
                timeRate = 30;
            }
        } else {
            if (timeInSecs <= 360) {
                timeRate = 100;
            } else if (timeInSecs <= 1200) { //20min
                timeRate = 70;
            } else if (timeInSecs <= 1500) { //25min
                timeRate = 50;
            } else {
                timeRate = 30;
            }
        }
        return timeRate;
    }
}
