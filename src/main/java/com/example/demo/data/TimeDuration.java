package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class TimeDuration {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String fromDate;

    private String toDate;

    public TimeDuration(String from, String to) {
        this.fromDate = from;
        this.toDate = to;
    }

    public TimeDuration() {

    }
}
