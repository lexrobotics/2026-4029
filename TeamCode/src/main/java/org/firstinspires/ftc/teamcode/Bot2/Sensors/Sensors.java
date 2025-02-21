package org.firstinspires.ftc.teamcode.Bot2.Sensors;

import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision.Vision;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.HashMap;

public class Sensors {
    private HashMap<String, HardwareStates> sensorStates;
    public Vision vision;
    public VisionPortal visionPortal;

    public Sensors(HashMap<String, HardwareStates> sensorStates){
        this.sensorStates = sensorStates;
    }

    public void init(){
        vision.init();
    }

    public void telemetry(){

    }
}