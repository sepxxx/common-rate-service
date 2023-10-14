package com.bnk.commonrateservicev2.loadRatesProvider.services;


import com.bnk.commonrateservicev2.commonRatesProvider.services.HttpClientJdk;
import com.bnk.commonrateservicev2.loadRatesProvider.dtos.Ids;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class GetLoadRatesService {

//    @Value("${load_rates_service.url:http://localhost:8081}")
//    static String hostUrl;
    String hostUrl = "http://localhost:8081";
    String templateUrl = "%s/offices/load/rating/%s";
    HttpClientJdk httpClientJdk;
    ObjectMapper objectMapper;

    public Map<Long, Double> getLoadRates (List<Long> idsList, String faceType) {
        String fullUrl = String.format(templateUrl, hostUrl, faceType);
        log.info("getLoadRates fullUrl:{}",fullUrl);


        Ids ids = new Ids();
        ids.setIds(idsList);

        String idsToJsonToString;
        try {
            idsToJsonToString = objectMapper.writeValueAsString(ids);
            log.info("getLoadRates listToJsonToString:{}", idsToJsonToString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String response = httpClientJdk.performRequest(fullUrl, idsToJsonToString);
        log.info("getLoadRates response:{}", response);
        try {
            Map<Long, Double> longDoubleMap=  objectMapper.readValue(response,
                    new TypeReference<Map<Long, Double>>() {});

            log.info("getLoadRates longDoubleMap:{}", longDoubleMap);

            return longDoubleMap;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
