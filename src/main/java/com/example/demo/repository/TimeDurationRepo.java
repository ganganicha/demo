package com.example.demo.repository;

import com.example.demo.data.TimeDuration;
import com.example.demo.data.TimeSeriesTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDurationRepo extends JpaRepository<TimeDuration,Integer> {

}
