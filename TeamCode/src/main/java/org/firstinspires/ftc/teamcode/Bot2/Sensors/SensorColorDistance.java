package org.firstinspires.ftc.teamcode.Bot2.Sensors;


//import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import static org.firstinspires.ftc.teamcode.Bot2.Setup.hardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorColorDistance {
    private ColorSensor color;
    private DistanceSensor distance;
    public SensorColorDistance(String name) {
        distance = hardwareMap.get(DistanceSensor.class, name);
        color = hardwareMap.get(ColorSensor.class, name);
    }
    public SensorColorDistance(String name, HardwareMap map) {
        distance = map.get(DistanceSensor.class, name);
        color = map.get(ColorSensor.class, name);
    }
    public double getDistance(DistanceUnit unit){
        return distance.getDistance(unit);
    }
    public double[] getColor(){
        return new double[]{color.alpha(),color.red(),color.green(),color.blue()};
    }

    public void setI2cAddress(I2cAddr newAddress){
        color.setI2cAddress(newAddress);
    }

    public void enableLed(boolean isOn){
        color.enableLed(isOn);
    }
//    private ColorRangeSensor sensor;
//    public SensorColorDistance(String name){
//        sensor = hardwareMap.get(ColorRangeSensor.class, name);
//    }
//    public double getDistance(DistanceUnit unit){
//        return sensor.getDistance(unit);
//    }
//    public double[] getColor(){
//        return new double[]{sensor.alpha(),sensor.red(),sensor.green(),sensor.blue()};
//    }
//
//    public void setI2cAddress(I2cAddr newAddress){
//        sensor.setI2cAddress(newAddress);
//    }
//
//    public void enableLed(boolean isOn){
//        sensor.enableLed(isOn);
//    }

}
