package com.example.demo.service;

import com.example.demo.data.DiagnosticCodes;
import com.example.demo.data.Error;
import com.example.demo.dto.DiagnosticCodesDto;
import com.example.demo.repository.DiagnosticCodesRepo;
import com.example.demo.repository.ErrorRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaultCodeService {

    @Autowired
    DiagnosticCodesRepo diagnosticCodesRepo;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    ErrorRepo errorRepo;

    private static String token;

    private static HttpClient httpClient;

    private static final Logger logger = LoggerFactory.getLogger(MachineService.class);

    List<DiagnosticCodes> diagnosticCodesList;

    List<Error> errorList;

    public void getMachineDiagnosticCodes() {
        long startingTime = System.currentTimeMillis();
        token = authorizationService.getAuthToken();
        httpClient = HttpClient.newHttpClient();
        UtilService.machineIds.forEach(id -> {
            diagnosticCodesList = new ArrayList<>();
            errorList = new ArrayList<>();
            token = authorizationService.regenerateAuthToken(token);
            UtilService.timeIntervals.entrySet()
                    .parallelStream()
                    .forEach(entry -> {
                        String from = entry.getKey();
                        String to = entry.getValue();
                        getMachineDiagnosticCodes(id, from, to);
                    });
            diagnosticCodesRepo.saveAll(diagnosticCodesList);
            errorRepo.saveAll(errorList);
        });

        logger.info("Time taken for all processing all machine : {} ms", System.currentTimeMillis() - startingTime);
    }

    public void getMachineDiagnosticCodes(String machineId, String from, String to) {
        String isisUrl = "https://iris.trackunit.com/public/api/aemp/v2/15143/-3/Fleet/Equipment/ID/";
        String url = isisUrl.concat(machineId).concat("/")
                .concat("Faults").concat("/")
                .concat(from).concat("/")
                .concat(to).concat("/")
                .concat("1");
        long startingTime = System.currentTimeMillis();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().headers("Authorization", "Bearer " + token)
                    .header("accept", "application/json")
                    .uri(new URI(url)).build();
        } catch (URISyntaxException e) {
            String errorString = "Error in creating API request for machine : " + machineId + " during from " + from + " to " + to;
            Error error = new Error(errorString, "exp", machineId);
            errorList.add(error);
        }
        try {
            logger.info("Executing API for machine : {}  during from {} to {}", machineId, from, to);
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Response received for machine : {} during from {} to {} : {} ", machineId, from, to, httpResponse.statusCode());
            ObjectMapper mapper = new ObjectMapper();
            if (httpResponse.statusCode() == 200) {
                DiagnosticCodesDto diagnosticCodes = mapper.readValue(httpResponse.body(), DiagnosticCodesDto.class);
                if (diagnosticCodes != null && diagnosticCodes.getFaultCode() != null && !diagnosticCodes.getFaultCode().isEmpty()) {

                    diagnosticCodes.getFaultCode().forEach(dto -> {
                        DiagnosticCodes codes = new DiagnosticCodes();
                        BeanUtils.copyProperties(dto, codes);
                        codes.setMachineId(machineId);
                        diagnosticCodesList.add(codes);
                    });
//                    diagnosticCodesRepo.saveAll(diagnosticCodesList);
                }

            } else {
                logger.info("Number of insights for during from {} to {} : {} ", from, to, 0);
                String errorString = "Error in Executing API for machine : " + machineId
                        + " during from " + from + " to " + to;
                Error error = new Error(errorString, String.valueOf(httpResponse.statusCode()), machineId);
                errorList.add(error);
            }
        } catch (IOException | InterruptedException e) {
            String errorString = "Error in Executing API for machine : " + machineId
                    + " during from " + from + " to " + to;
            Error error = new Error(errorString, "exp", machineId);
            errorList.add(error);
        }
        errorRepo.saveAll(errorList);
    }
}
