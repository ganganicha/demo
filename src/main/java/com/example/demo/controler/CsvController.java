package com.example.demo.controler;

import com.example.demo.service.OutStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvController {

    @Autowired
    OutStream outStream;


    @GetMapping("/writeToCsv")
    public void writeToFile(){
        outStream.writeDataAtOnce("C:\\Project\\Riwal\\Data\\OutStream\\OutStream2.csv");
    }

    @GetMapping("/writeToCsv/faultCode")
    public void writeToFileFaultCode(){
        outStream.writeDataAtOnce("C:\\Project\\Riwal\\Data\\OutStream\\faultCode.csv");
    }
}
