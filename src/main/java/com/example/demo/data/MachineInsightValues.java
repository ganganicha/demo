package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MachineInsightValues {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String timeSeriesType;

    private String value;

    private Date datetime;

    private String machineId;

    public MachineInsightValues(String timeSeriesType, String value, Date datetime, String machineId) {
        this.timeSeriesType = timeSeriesType;
        this.value = value;
        this.datetime = datetime;
        this.machineId = machineId;
    }


    public MachineInsightValues() {

    }
}
