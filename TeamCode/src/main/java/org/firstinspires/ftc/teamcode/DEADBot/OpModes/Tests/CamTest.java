//package org.firstinspires.ftc.teamcode.DEADBot.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.Vision.Camera;
//import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.Vision.Pipelines.Contour;
//
//@Disabled
//@Autonomous
//public class CamTest extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Contour pipeline = new Contour();
//        pipeline.init(true);
//        Camera cam = new Camera("webcam","Blue", hardwareMap);
//        cam.setPipeline(Camera.basePipelines.Contour);
//        cam.openCamera();
//        waitForStart();
//    }
//}
