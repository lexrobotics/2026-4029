package org.firstinspires.ftc.teamcode.DEADBot.DEADSensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1.hardwareMap;

public class SensorDistance{
    private DistanceSensor sensor;
    public SensorDistance(String name) {
        sensor = hardwareMap.get(DistanceSensor.class, name);
    }
    public double getDistance(DistanceUnit unit){
        return sensor.getDistance(unit);
    }
}
