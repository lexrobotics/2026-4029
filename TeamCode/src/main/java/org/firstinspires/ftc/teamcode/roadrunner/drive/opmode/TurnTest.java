package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
@Autonomous(group = "drive")
public class TurnTest extends LinearOpMode {
    public static double ANGLE = 90; // deg

    private ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));

        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("finalHeading", poseEstimate.getHeading());
        telemetry.addData("finalHeadingDeg", Math.toDegrees(poseEstimate.getHeading()));
        telemetry.update();
        timer.reset();
        while (opModeIsActive() && timer.milliseconds() < 10000) {
            sleep(5);
        }

//        drive.turn(Math.toRadians(-ANGLE));
//
//        Pose2d poseEstimate2 = drive.getPoseEstimate();
//        telemetry.addData("finalHeading", poseEstimate2.getHeading());
//        telemetry.addData("finalHeadingDeg", Math.toDegrees(poseEstimate2.getHeading()));
//        telemetry.update();
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 10000) {
//            sleep(5);
//        }
    }

}
