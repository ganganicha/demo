package com.example.demo.repository;

import com.example.demo.data.DiagnosticCodes;
import com.example.demo.data.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticCodesRepo extends JpaRepository<DiagnosticCodes,Integer> {

}
