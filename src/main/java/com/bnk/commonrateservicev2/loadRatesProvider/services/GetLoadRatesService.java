package com.bnk.commonrateservicev2.loadRatesProvider.services;


import com.bnk.commonrateservicev2.loadRatesProvider.dtos.IdsToRatesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GetLoadRatesService {

    @Value("${load_rates_service.url}")
    static String hostUrl;
    String templateUrl = "%s/offices/load/rating/%s";
    HttpClientJdk httpClientJdk;
    ObjectMapper objectMapper;

    public Map<Long, Double> getLoadRates (List<Long> ids, String faceType) {
        String fullUrl = String.format(templateUrl, hostUrl, faceType);
        String response = httpClientJdk.performRequest(fullUrl);
        try {
            IdsToRatesDto idsToRatesDto =  objectMapper.readValue(response, IdsToRatesDto.class);
            return idsToRatesDto.getIdsToRates();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
