package com.example.demo.service;

import com.example.demo.data.Machine;
import com.example.demo.data.TimeDuration;
import com.example.demo.data.TimeSeriesTypes;
import com.example.demo.repository.MachineRepo;
import com.example.demo.repository.TimeDurationRepo;
import com.example.demo.repository.TimeSeriesTypeRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UtilService {

    @Autowired
    TimeSeriesTypeRepo timeSeriesTypeRepo;

    @Autowired
    TimeDurationRepo timeDurationRepo;

    @Autowired
    MachineRepo machineRepo;

    public static List<TimeSeriesTypes> timeSeriesTypes;
    public static HashMap<String, String>  timeIntervals;

    public static List<String> machineIds;

    final static Logger logger = Logger.getLogger(UtilService.class);

//    @Bean
    public void addTimeSeriesTypes() throws IOException {

        logger.info("Starting to read TimeSeriesTypes");
        List<TimeSeriesTypes> timeSeriesTypes = new ArrayList<>();
        BufferedReader lineReader = new BufferedReader(new FileReader("C:\\Project\\Riwal\\demo\\src\\main\\resources\\machine_Insite_types.txt"));
        lineReader.readLine(); // skip header line
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {
            TimeSeriesTypes timeSeriesType = new TimeSeriesTypes(lineText);
            timeSeriesTypes.add(timeSeriesType);
        }
        timeSeriesTypeRepo.saveAll(timeSeriesTypes);
        logger.info("TimeSeriesTypes saved to DB successfully");
    }

    @Bean
    public void getTimeSeriesTypes() {
        timeSeriesTypes = timeSeriesTypeRepo.findAll();
        logger.info("TimeSeriesTypes loaded successfully");
    }


//    @Bean
    public void addTimeIntervals() throws IOException {

        logger.info("Starting to read TimeIntervals");
        List<TimeDuration> timeIntervals = new ArrayList<>();
        BufferedReader lineReader = new BufferedReader(new FileReader("C:\\Project\\Riwal\\demo\\src\\main\\resources\\time_duration.txt"));
        lineReader.readLine(); // skip header line
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {

            String[] interval = lineText.split(",");
            TimeDuration timeDuration = new TimeDuration(interval[0],interval[1]);
            timeIntervals.add(timeDuration);
        }
        timeDurationRepo.saveAll(timeIntervals);
        logger.info("TimeIntervals saved to DB successfully");
    }

    @Bean
    public void getTimeDurations() {
        timeIntervals = new HashMap<>();
        timeDurationRepo.findAll().forEach(interval -> {
            timeIntervals.put(interval.getFromDate(), interval.getToDate());
        });
        logger.info("TimeIntervals loaded successfully");
    }

    @Bean
    public void loadMachines() throws IOException {

        logger.info("Starting to read MachineIds");
        List<Machine> machineList = new ArrayList<>();
        machineIds = new ArrayList<>();
        BufferedReader lineReader = new BufferedReader(new FileReader("C:\\Project\\Riwal\\demo\\src\\main\\resources\\machine_ids.txt"));
        lineReader.readLine(); // skip header line
        String lineText = null;

        while ((lineText = lineReader.readLine()) != null) {
            Machine machine = new Machine(lineText);
            machineIds.add(lineText);
            machineList.add(machine);
        }
//        machineRepo.saveAll(machineList);
        logger.info("MachineIds loaded successfully");
    }
}
