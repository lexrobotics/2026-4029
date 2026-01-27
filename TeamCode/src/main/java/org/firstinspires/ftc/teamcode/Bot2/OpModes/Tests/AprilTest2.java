package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


@TeleOp
public class AprilTest2 extends LinearOpMode {
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() throws InterruptedException {

        initAprilTag();
        int code = -1;

        waitForStart();
        while(opModeIsActive()){
            for(AprilTagDetection d : aprilTag.getDetections()) {
                if(d.metadata != null){
                    code = d.id;
                    break;
                }
            }
            telemetry.addLine("The code is: " + code);
            telemetry.update();
        }

    }

    private void initAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam"));
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();
    }
}