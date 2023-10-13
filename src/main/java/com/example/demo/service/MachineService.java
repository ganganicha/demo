package com.example.demo.service;


import com.example.demo.data.DiagnosticCodes;
import com.example.demo.data.Error;
import com.example.demo.data.MachineInsightValues;
import com.example.demo.dto.DiagnosticCodesDto;
import com.example.demo.dto.MachineInsightsDto;
import com.example.demo.repository.DiagnosticCodesRepo;
import com.example.demo.repository.ErrorRepo;
import com.example.demo.repository.MachineInsightValuesRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class MachineService {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    MachineInsightValuesRepo machineInsightValuesRepo;

    @Autowired
    ErrorRepo errorRepo;

    @Autowired
    DiagnosticCodesRepo diagnosticCodesRepo;
    private static String token;

    private static HttpClient httpClient;

    private static final Logger logger = LoggerFactory.getLogger(MachineService.class);

    private static List<MachineInsightValues> machineInsightValues;

    private static List<Error> errorList;

    public void getMachineInsights() {
        long startingTime = System.currentTimeMillis();
        token = authorizationService.getAuthToken();
        httpClient = HttpClient.newHttpClient();
        UtilService.machineIds.forEach(id -> {
            if (authorizationService.isJWTExpired()) {
                logger.info("Auth token expired");
                token = authorizationService.getAuthToken();
                logger.info("Auth token regenerated successfully");
            }
            machineInsightValues = new ArrayList<>();
            errorList = new ArrayList<>();
            machineInsightsForIntervalAndType(id);
            machineInsightValuesRepo.saveAll(machineInsightValues);
            errorRepo.saveAll(errorList);
        });
        logger.info("Time taken for all processing all machine : {} ms", System.currentTimeMillis() - startingTime);
    }

    public void machineInsightsForIntervalAndType(String machineId) {
        long startingTime = System.currentTimeMillis();
        logger.info("Starting to load machine insights for : {} ", machineId);
        UtilService.timeSeriesTypes.forEach(type -> {
            for (HashMap.Entry<String, String> entry : UtilService.timeIntervals.entrySet()) {
                String from = entry.getKey();
                String to = entry.getValue();
                getMachineInsights(machineId, type.getTimeSeriesType(), from, to);
                regenerateAuthToken();
//                try {
//                    Thread.sleep(900000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        });
        logger.info("Time taken for processing : {} machine : {} ms", machineId, System.currentTimeMillis() - startingTime);
    }

    public void getMachineInsights(String machineId, String type, String from, String to) {
        String isisUrl = "https://iris.trackunit.com/public/api/aemp/v2/15143/-3/Fleet/Equipment/ID/";
        String url = isisUrl.concat(machineId).concat("/")
                .concat(StringUtils.capitalize(type)).concat("/")
                .concat(from).concat("/")
                .concat(to).concat("/")
                .concat("1");

        long startingTime = System.currentTimeMillis();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().headers("Authorization", "Bearer " + token).header("accept", "application/json").uri(new URI(url)).build();
        } catch (URISyntaxException e) {
            String errorString = "Error in creating API request for machine : " + machineId + " type : " + type
                    + " during from " + from + " to " + to;
            Error error = new Error(errorString, "exp", machineId);
            errorList.add(error);
        }
        try {
            logger.info("Executing API for machine : {} type : {} during from {} to {}", machineId, type, from, to);
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Response received for machine : {} type  : {} during from {} to {} : {} ", machineId, type, from, to, httpResponse.statusCode());
            ObjectMapper mapper = new ObjectMapper();
            if (httpResponse.statusCode() == 200) {
                MachineInsightsDto machineInsights = mapper.readValue(httpResponse.body(), MachineInsightsDto.class);
                List<MachineInsightsDto.Value> values = machineInsights.getValues(machineInsights);
                if (values != null) {
                    logger.info("Number of insights for machine : {} type : {} during from {} to {}  : {} ", machineId, type, from, to, values.size());
                    values.forEach(val -> {
                        MachineInsightValues machineInsightValue = new MachineInsightValues(type, val.getValue(), val.getDatetime(), machineId);
                        machineInsightValues.add(machineInsightValue);
                    });
                    logger.info("Time taken for processing machine : {} type : from :  {} to  : {} ", machineId, type, System.currentTimeMillis() - startingTime);
                } else {
                    logger.info("Number of insights for type : {} during from {} to {} : {} ", type, from, to, 0);
                    String errorString = "Error in Executing API for machine : " + machineId + " type : " + type
                            + " during from " + from + " to " + to;
                    Error error = new Error(errorString, "Null values, lis is empty", machineId);
                    errorList.add(error);
                }
            } else {
                logger.info("Number of insights for type : {} during from {} to {} : {} ", type, from, to, 0);
                String errorString = "Error in Executing API for machine : " + machineId + " type : " + type
                        + " during from " + from + " to " + to;
                Error error = new Error(errorString, String.valueOf(httpResponse.statusCode()), machineId);
                errorList.add(error);
            }
        } catch (IOException | InterruptedException e) {
            String errorString = "Error in Executing API for machine : " + machineId + " type : " + type
                    + " during from " + from + " to " + to;
            Error error = new Error(errorString, "exp", machineId);
            errorList.add(error);
        }
    }



    private void regenerateAuthToken() {
        if (authorizationService.isJWTExpired()) {
            logger.info("Auth token expired");
            token = authorizationService.getAuthToken();
            logger.info("Auth token regenerated successfully");
        }
    }



}
