package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Error {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String error;

    private String exp;

    private String machineId;

    public Error(String error, String exp, String machineId) {
        this.error = error;
        this.exp = exp;
        this.machineId = machineId;
    }

    public Error() {

    }
}
