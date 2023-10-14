package com.bnk.commonrateservicev2.commonRatesProvider.services;

import com.bnk.commonrateservicev2.commonRatesProvider.dto.firstService.Container;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class Parser {

    @Value("${api_key}")
    private String key;

    private final ObjectMapper objectMapper;
    private final HttpClientJdk jdk;

    public Container request(double lat1, double lng1, double lat2, double lng2, long idDepartment, String mode) throws JsonProcessingException {
        String body = "https://maps.googleapis.com/maps/api/distancematrix/json";
        String url = body + "?destinations=" + lat1 + "%2C" + lng1 + "&mode=" + mode + "&origins=" + lat2 + "%2C" + lng2 + "&key=" + key;
        Container container = objectMapper.readValue(jdk.performRequest(url), Container.class);
        container.setId(idDepartment);
        return container;
    }

}
