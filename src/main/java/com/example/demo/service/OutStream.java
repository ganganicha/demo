package com.example.demo.service;

import com.example.demo.data.DiagnosticCodes;
import com.example.demo.data.MachineInsightValues;
import com.example.demo.repository.DiagnosticCodesRepo;
import com.example.demo.repository.MachineInsightValuesRepo;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OutStream {

    @Autowired
    MachineInsightValuesRepo machineInsightValuesRepo;

    @Autowired
    DiagnosticCodesRepo diagnosticCodesRepo;

    public void writeDataAtOnce(String filePath)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "id", "MachineId", "time_series_type", "datetime","value"};
            writer.writeNext(header);
            List<String[]> data = new ArrayList<String[]>();
            List<MachineInsightValues> machineInsightValues = machineInsightValuesRepo.findAll();
            machineInsightValues.forEach(value -> {
                data.add(new String[] { value.getId().toString(), value.getMachineId(),
                        value.getTimeSeriesType(),value.getDatetime().toString(),value.getValue() });
            });
            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void writeDataAtOnceForFaultCode(String filePath)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { "id", "MachineId", "Code Identifier", "Code Description","Code Severity","Code Source","Datetime"};
            writer.writeNext(header);
            List<String[]> data = new ArrayList<String[]>();
            List<DiagnosticCodes> diagnosticCodesList = diagnosticCodesRepo.findAll();
            diagnosticCodesList.forEach(value -> {
                data.add(new String[] { value.getId().toString(), value.getMachineId(),
                        value.getCodeIdentifier(),value.getCodeDescription(),value.getCodeSeverity()
                        ,value.getCodeSource(),value.getDatetime().toString() });
            });
            writer.writeAll(data);
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
