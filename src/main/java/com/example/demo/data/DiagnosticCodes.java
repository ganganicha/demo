package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DiagnosticCodes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String machineId;
    public String codeIdentifier;
    public String codeDescription;
    public String codeSeverity;
    public String codeSource;
    public Date datetime;

}
