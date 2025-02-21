package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp
public class ServoTest extends LinearOpMode {
    private double targetPosition1 = 0.5;
    private double targetPosition2 = 0.5;
    private double targetPosition3 = 0.5;

    private final double multiplier = 0.0005;
    private int mode = 1;
//    private int countTest = 0;
//    private int aCountTest = 0;
//    private int modeN1Time = 0;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;

//    Going to 0 will go clockwise
//    Going to 1 will go counter-clockwise
    @Override
    public void runOpMode(){

        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");

//        controller = new ServoController()

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
//            countTest+=1;

            if(gamepad1.a){
                servo1.setPosition(targetPosition1);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetPosition1 += gamepad1.left_stick_y*multiplier;
                    if(targetPosition1 > 1){
                        targetPosition1 = 1;
                    }
                    else if(targetPosition1 < 0){
                        targetPosition1 = 0;
                    }
                }
            }
            if(gamepad1.b){
                servo2.setPosition(targetPosition2);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetPosition2 += gamepad1.left_stick_y*multiplier;
                    if(targetPosition2 > 1){
                        targetPosition2 = 1;
                    }
                    else if(targetPosition2 < 0){
                        targetPosition2 = 0;
                    }
                }            }
            if(gamepad1.x){
                servo3.setPosition(targetPosition3);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetPosition3 += gamepad1.left_stick_y*multiplier;
                    if(targetPosition3 > 1){
                        targetPosition3 = 1;
                    }
                    else if(targetPosition3 < 0){
                        targetPosition3 = 0;
                    }
                }
            }



//            telemetry.addData("aWasPressed", aCountTest);
//            telemetry.addData("modeN1Time", modeN1Time);
            telemetry.addData("TargetPosition1", targetPosition1*100 + "%");
            telemetry.addData("TargetPosition2", targetPosition2*100 + "%");
            telemetry.addData("TargetPosition2", targetPosition3*100 + "%");
//            telemetry.addData("CountTest", countTest);

            telemetry.update();
        }

    }
}
