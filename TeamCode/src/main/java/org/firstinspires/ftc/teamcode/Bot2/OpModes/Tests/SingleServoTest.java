package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp
public class SingleServoTest extends LinearOpMode{
    private final double amount = 0.001;
    private Servo servo;
    private double currentPosition=0;
    @Override
    public void runOpMode(){

        servo = hardwareMap.get(Servo.class, "servo");



        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo.setPosition(0);

        waitForStart();

        while(opModeIsActive()){

            if(gamepad1.dpad_right){
                telemetry.addLine("DpadRight");
                currentPosition += amount;
                if(currentPosition>=1){currentPosition=1;}
            }
            if(gamepad1.dpad_left){
                telemetry.addLine("DpadLeft");
                currentPosition-=amount;
                if(currentPosition<=0){currentPosition=0;}
            }


            servo.setPosition(currentPosition);
            telemetry.addLine("Current Position: " + currentPosition);

            telemetry.update();
        }

    }
}