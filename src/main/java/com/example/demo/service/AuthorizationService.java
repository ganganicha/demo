package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.AuthTokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

@Service
public class AuthorizationService
{
    public String authToken;

    private DecodedJWT decodedJWT;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    public String getAuthToken() {
        String updatePersonUrl = "https://auth.trackunit.com/token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic MG9hOTQzaDkxNGVoRlVPQ1MzNTc6T3Y0OGlvX1dCcmVMaDU0YWRnQUM4dHFFN1QtUVFDZDRoVXFwazFaZQ==");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "TU-API-10095-7");
        params.add("password", "86mu)rwiZb#qYuaT");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        ResponseEntity<AuthTokenDto> responseEntity = restTemplate.postForEntity(updatePersonUrl, request, AuthTokenDto.class);
        authToken = Objects.requireNonNull(responseEntity.getBody()).getAccess_token();
        logger.info("Auth token generated successfully");
        try {
            decodedJWT = JWT.decode(authToken);
        } catch (JWTDecodeException e) {
            throw e;
        }
        return authToken;

    }

    public boolean isJWTExpired() {
        Date expiresAt = decodedJWT.getExpiresAt();
        System.out.println( expiresAt.before(new Date()));
        return expiresAt.before(new Date());
    }

    public String regenerateAuthToken(String token) {
        if (isJWTExpired()) {
            logger.info("Auth token expired");
            logger.info("Auth token regenerated successfully");
            return getAuthToken();
        }
        return token;
    }
}
