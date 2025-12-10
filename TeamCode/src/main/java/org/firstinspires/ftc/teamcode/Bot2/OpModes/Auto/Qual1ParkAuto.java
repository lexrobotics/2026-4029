package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;


import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

@Autonomous(name = "Qual1ParkAuto", group = "1")
public class Qual1ParkAuto extends LinearOpMode{
    //private Setup setup;
    private ElapsedTime timer;

    //private double gamepadX;
//private double gamepadY;

    private Bot bot;
    private Setup setup;
    private double y;
    private double x;
    private double spin;
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private IMU imu;
    private double botHeading;

    private Drivetrain drivetrain = new Drivetrain();



    @Override
    public void runOpMode() throws InterruptedException{

//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);



//        frontLeftMotor = hardwareMap.dcMotor.get("rightFront");
//        backLeftMotor = hardwareMap.dcMotor.get("leftRear");
//        frontRightMotor = hardwareMap.dcMotor.get("leftFront");
//        backRightMotor = hardwareMap.dcMotor.get("rightRear");

//        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        timer=new ElapsedTime();



//        imu = hardwareMap.get(IMU.class, "imu");
//        // Adjust the orientation parameters to match your robot
//        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
//                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
//                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
//        imu.initialize(parameters);


        x = 0;
        y = -0.5;
        spin = 0;

        drivetrain.init(new Pose2d(0, 0, 0));
//        bot.init();


        waitForStart();
        timer.reset();
        while(opModeIsActive()){
            if(timer.seconds() < 2) {
                drivetrain.setTeleOpTargets(x, y, spin);
//                bot.drivetrain.setTeleOpTargets(x, y, spin);
//                bot.update();
            } else{
                break;
            }

        }

    }
}