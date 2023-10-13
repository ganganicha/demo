package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MachineInsightsDto {

    public ArrayList<Link> links;
    public ArrayList<Value> engineFuelRate;
    public ArrayList<Value> engineSpeed;
    public ArrayList<Value> engineOilTemperature;
    public ArrayList<Value> batteryPotential;
    public ArrayList<Value> enginePercentLoadAtCurrentSpeed;
    public ArrayList<Value> engineCoolantTemperature;
    public ArrayList<Value> accelerometerXAxis;
    public ArrayList<Value> accelerometerYAxis;
    public ArrayList<Value> accelerometerZAxis;
    public ArrayList<Value> actualEnginePercentTorque;
    public ArrayList<Value> ambientAirTemperature;
    public ArrayList<Value> batteryStateOfChargePercent;
    public ArrayList<Value> engineIntakeManifoldTemperature;
    public ArrayList<Value> barometricPressure;
    public ArrayList<Value> engineFuelDeliveryPressure;
    public ArrayList<Value> engineIntakeManifoldPressure;
    public ArrayList<Value> engineOilPressure;
    public ArrayList<Value> batteryChargerOutputCurrent;
    public ArrayList<Value> BatteryChargerOutputVoltage;
    public ArrayList<Value> payloadPercentage;
    public ArrayList<Value> waterInFuelIndicator;
    public ArrayList<Value> wheelBasedVehicleSpeed;
    public ArrayList<Value> dEFRemaining;
    public ArrayList<Value> afterTreatmentScrOperatorInducementSeverity;
    public ArrayList<Value> engineAirFilterDifferentialPressure;
    public ArrayList<Value> afterTreatmentDieselParticulateFilterStatus;
    public ArrayList<Value> dieselParticulateFilterActiveRegenerationInhibitedDueToInhibitSwitch;
    public ArrayList<Value> afterTreatmentDieselExhaustFluidTankTemperature;
    public ArrayList<Value> engineIntakeAirTemperature;
    public ArrayList<Value> afterTreatmentDieselParticulateFilterSootLoadPercent;
    public ArrayList<Value> afterTreatmentDieselParticulateFilterAshLoadPercent;
    public ArrayList<Value> afterTreatmentDieselParticulateFilterDifferentialPressure;
    public ArrayList<Value> engineCoolantLevel;
    public ArrayList<Value> afterTreatmentDieselExhaustFluidConcentration;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Value {
        public String value;
        public Date datetime;
    }

    public static class Link {
        public String rel;
        public String href;
    }

    public List<Value> getValues(MachineInsightsDto machineInsights) {
        if (machineInsights.getAccelerometerXAxis() != null && !machineInsights.getAccelerometerXAxis().isEmpty())
            return machineInsights.getAccelerometerXAxis();
        if (machineInsights.getAccelerometerYAxis() != null && !machineInsights.getAccelerometerYAxis().isEmpty())
            return machineInsights.getAccelerometerYAxis();
        if (machineInsights.getAccelerometerZAxis() != null && !machineInsights.getAccelerometerZAxis().isEmpty())
            return machineInsights.getAccelerometerZAxis();
        if (machineInsights.getActualEnginePercentTorque() != null && !machineInsights.getActualEnginePercentTorque().isEmpty())
            return machineInsights.getActualEnginePercentTorque();
        if (machineInsights.getAmbientAirTemperature() != null && !machineInsights.getAmbientAirTemperature().isEmpty())
            return machineInsights.getAmbientAirTemperature();
        if (machineInsights.getBatteryStateOfChargePercent() != null && !machineInsights.getBatteryStateOfChargePercent().isEmpty())
            return machineInsights.getBatteryStateOfChargePercent();
        if (machineInsights.getEnginePercentLoadAtCurrentSpeed() != null && !machineInsights.getEnginePercentLoadAtCurrentSpeed().isEmpty())
            return machineInsights.getEnginePercentLoadAtCurrentSpeed();
        if (machineInsights.getEngineIntakeManifoldTemperature() != null && !machineInsights.getEngineIntakeManifoldTemperature().isEmpty())
            return machineInsights.getEngineIntakeManifoldTemperature();
        if (machineInsights.getBarometricPressure() != null && !machineInsights.getBarometricPressure().isEmpty())
            return machineInsights.getBarometricPressure();
        if (machineInsights.getEngineFuelDeliveryPressure() != null && !machineInsights.getEngineFuelDeliveryPressure().isEmpty())
            return machineInsights.getEngineFuelDeliveryPressure();
        if (machineInsights.getEngineIntakeManifoldPressure() != null && ! machineInsights.getEngineIntakeManifoldPressure().isEmpty())
            return machineInsights.getEngineIntakeManifoldPressure();
        if (machineInsights.getEngineFuelRate() != null && !machineInsights.getEngineFuelRate().isEmpty())
            return machineInsights.getEngineFuelRate();
        if (machineInsights.getEngineSpeed() != null && !machineInsights.getEngineSpeed().isEmpty())
            return machineInsights.getEngineSpeed();
        if (machineInsights.getEngineOilPressure() != null && !machineInsights.getEngineOilPressure().isEmpty())
            return machineInsights.getEngineOilPressure();
        if (machineInsights.getEngineOilTemperature() != null && !machineInsights.getEngineOilTemperature().isEmpty())
            return machineInsights.getEngineOilTemperature();
        if (machineInsights.getBatteryPotential() != null && !machineInsights.getBatteryPotential().isEmpty())
            return machineInsights.getBatteryPotential();
        if (machineInsights.getEngineCoolantTemperature() != null && !machineInsights.getEngineCoolantTemperature().isEmpty())
            return machineInsights.getEngineCoolantTemperature();
        if (machineInsights.getBatteryChargerOutputCurrent() != null && !machineInsights.getBatteryChargerOutputCurrent().isEmpty())
            return machineInsights.getBatteryChargerOutputCurrent();
        if (machineInsights.getBatteryChargerOutputVoltage() != null && !machineInsights.getBatteryChargerOutputVoltage().isEmpty())
            return machineInsights.getBatteryChargerOutputVoltage();
        if (machineInsights.getPayloadPercentage() != null && !machineInsights.getPayloadPercentage().isEmpty())
            return machineInsights.getPayloadPercentage();
        if (machineInsights.getWaterInFuelIndicator() != null && !machineInsights.getWaterInFuelIndicator().isEmpty())
            return machineInsights.getWaterInFuelIndicator();
        if (machineInsights.getWheelBasedVehicleSpeed() != null && !machineInsights.getWheelBasedVehicleSpeed().isEmpty())
            return machineInsights.getWheelBasedVehicleSpeed();
        if (machineInsights.getDEFRemaining() != null && !machineInsights.getDEFRemaining().isEmpty())
            return machineInsights.getDEFRemaining();
        if (machineInsights.getAfterTreatmentScrOperatorInducementSeverity() != null
                && !machineInsights.getAfterTreatmentScrOperatorInducementSeverity().isEmpty())
            return machineInsights.getAfterTreatmentScrOperatorInducementSeverity();
        if (machineInsights.getEngineAirFilterDifferentialPressure() != null
                && !machineInsights.getEngineAirFilterDifferentialPressure().isEmpty())
            return machineInsights.getEngineAirFilterDifferentialPressure();
        if (machineInsights.getAfterTreatmentDieselParticulateFilterStatus() != null
                && !machineInsights.getAfterTreatmentDieselParticulateFilterStatus().isEmpty())
            return machineInsights.getAfterTreatmentDieselParticulateFilterStatus();
        if (machineInsights.getDieselParticulateFilterActiveRegenerationInhibitedDueToInhibitSwitch() != null
                && !machineInsights.getDieselParticulateFilterActiveRegenerationInhibitedDueToInhibitSwitch().isEmpty())
            return machineInsights.getDieselParticulateFilterActiveRegenerationInhibitedDueToInhibitSwitch();
        if (machineInsights.getAfterTreatmentDieselExhaustFluidTankTemperature() != null && !machineInsights.getAfterTreatmentDieselExhaustFluidTankTemperature().isEmpty())
            return machineInsights.getAfterTreatmentDieselExhaustFluidTankTemperature();
        if (machineInsights.getEngineIntakeAirTemperature() != null && !machineInsights.getEngineIntakeAirTemperature().isEmpty())
            return machineInsights.getEngineIntakeAirTemperature();
        if (machineInsights.getAfterTreatmentDieselParticulateFilterSootLoadPercent() != null && !machineInsights.getAfterTreatmentDieselParticulateFilterSootLoadPercent().isEmpty())
            return machineInsights.getAfterTreatmentDieselParticulateFilterSootLoadPercent();
        if (machineInsights.getAfterTreatmentDieselParticulateFilterAshLoadPercent() != null && !machineInsights.getAfterTreatmentDieselParticulateFilterAshLoadPercent().isEmpty())
            return machineInsights.getAfterTreatmentDieselParticulateFilterAshLoadPercent();
        if (machineInsights.getAfterTreatmentDieselParticulateFilterDifferentialPressure() != null && !machineInsights.getAfterTreatmentDieselParticulateFilterDifferentialPressure().isEmpty())
            return machineInsights.getAfterTreatmentDieselParticulateFilterDifferentialPressure();
        if (machineInsights.getEngineCoolantLevel() != null && !machineInsights.getEngineCoolantLevel().isEmpty())
            return machineInsights.getEngineCoolantLevel();
        if (machineInsights.getAfterTreatmentDieselExhaustFluidConcentration() != null && !machineInsights.getAfterTreatmentDieselExhaustFluidConcentration().isEmpty())
            return machineInsights.getAfterTreatmentDieselExhaustFluidConcentration();
//        if(!machineInsights.().isEmpty())
//            return machineInsights.();
        else
            return null;
    }

}

