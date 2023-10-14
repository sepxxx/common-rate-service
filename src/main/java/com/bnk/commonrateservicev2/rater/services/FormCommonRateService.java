package com.bnk.commonrateservicev2.rater.services;


import com.bnk.commonrateservicev2.loadRatesProvider.services.GetLoadRatesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormCommonRateService {
    GetLoadRatesService getLoadRatesService;

    public Map<Long, Double> getCommonRatesMap(Map<Long, Long> idsToTime, String faceType) {
        Map<Long, Double> commonRatesMap = getLoadRatesService.getLoadRates(
                idsToTime.keySet().stream().toList(),
                faceType
        );
        //commonRatesMap содердит loadRatesMap,
        //нужно его пересчитать с учетом времени

        for(Long id:idsToTime.keySet()) {
            Double loadRate = commonRatesMap.get(id);
            Long timeInSecs = idsToTime.get(id);
            Double timeRate = formRateForTime(timeInSecs);

            commonRatesMap.put(id, (loadRate+timeRate)/2);
        }
        return commonRatesMap;
    }

    public Double formRateForTime(Long timeInSecs) {
        double timeRate;
        if(timeInSecs <=180) {
            timeRate=100;
        } else if(timeInSecs<=360){
            timeRate =70;
        } else if(timeInSecs<=720) {
            timeRate=50;
        } else {
            timeRate=30;
        }
        return timeRate;
    }
}
