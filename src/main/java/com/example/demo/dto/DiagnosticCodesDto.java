package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DiagnosticCodesDto {

    public ArrayList<Link> links;
    public ArrayList<FaultCode> faultCode;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class FaultCode {
        public String codeIdentifier;
        public String codeDescription;
        public String codeSeverity;
        public String codeSource;
        public Date datetime;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Link {
        public String rel;
        public String href;
    }
}
