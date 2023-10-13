package com.example.demo.repository;

import com.example.demo.data.Error;
import com.example.demo.data.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<Machine,Integer> {

}
