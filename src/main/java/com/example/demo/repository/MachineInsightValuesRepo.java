package com.example.demo.repository;

import com.example.demo.data.MachineInsightValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineInsightValuesRepo extends JpaRepository<MachineInsightValues,Integer> {

}
