package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Machine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String machineId;

    public Machine(String machineId) {
        this.machineId = machineId;
    }

    public Machine() {

    }
}
