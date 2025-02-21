package org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot2.Setup;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision.Processors.ColorProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;

public class Vision {
    private HashMap<String, HardwareStates> visionStates;
    public AprilTagProcessor aprilTag;
    public ColorProcessor colorProcessor;
    public VisionPortal visionPortal;

    public Vision(HashMap<String, HardwareStates> sensorStates){
        this.visionStates = sensorStates;
        aprilTag = null;
        visionPortal = null;
        colorProcessor = null;
    }

    public void init(){
        if(this.visionStates.get("webcam").isEnabled){
            aprilTag = new AprilTagProcessor.Builder().build();
            colorProcessor = new ColorProcessor();
            visionPortal = new VisionPortal.Builder()
                    .setCamera(Setup.hardwareMap.get(WebcamName.class, "Webcam 1"))
                    .addProcessors(colorProcessor, aprilTag)
                    .build();
        }
    }

    public void telemetry(){

    }
}
