package org.firstinspires.ftc.teamcode.Bot2.Sensors;

import static org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1.hardwareMap;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorDistance{
    private DistanceSensor sensor;
    public SensorDistance(String name) {
        sensor = hardwareMap.get(DistanceSensor.class, name);
    }
    public double getDistance(DistanceUnit unit){
        return sensor.getDistance(unit);
    }
}
