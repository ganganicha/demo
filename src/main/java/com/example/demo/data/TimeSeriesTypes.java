package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TimeSeriesTypes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String timeSeriesType;

    public TimeSeriesTypes(String timeSeriesType) {
        this.timeSeriesType = timeSeriesType;
    }

    public TimeSeriesTypes() {

    }
}
