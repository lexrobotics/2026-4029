package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Bot.Old.Bot1;
import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Old.Setup1;
//import org.firstinspires.ftc.teamcode.PedroPathing.follower.Follower;

@Autonomous
public class AutoTesting extends LinearOpMode {
    Drivetrain drivetrain;
    Setup1 setup;
    Bot1 bot;
    IMUStatic imu;

    @Override
    public void runOpMode(){
        setup = new Setup1(hardwareMap, telemetry, true, this, Setup1.OpModeType.TELEOP, Setup1.Team.Q1);
        bot = new Bot1(Setup1.mechStates, Setup1.sensorStates);
        imu = new IMUStatic();
//        Follower follower = new Follower(hardwareMap);
        waitForStart();

//        follower.followPath(drivetrain.BLInitToScoreClip());
        while(opModeIsActive()) {
//            if(gamepad1.right_bumper) follower.update();
        }
    }
}
