package com.example.demo.controler;

import com.example.demo.service.FaultCodeService;
import com.example.demo.service.MachineService;
import com.example.demo.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MachineController {


    @Autowired
    private MachineService machineService;

    @Autowired
    UtilService addTimeSeriesTypes;

    @Autowired
    FaultCodeService faultCodeService;


    @GetMapping("/getMachineInsights")
    void getMachineInsights() {
        machineService.getMachineInsights();
    }


    @GetMapping("/addTimeSeriesTypes")
    void addTimeSeriesTypes() throws IOException {
        addTimeSeriesTypes.addTimeSeriesTypes();
    }

    @GetMapping("/getMachineDiagnosticCodes")
    void getMachineDiagnosticCodes() throws IOException {
        faultCodeService.getMachineDiagnosticCodes();
    }

}
