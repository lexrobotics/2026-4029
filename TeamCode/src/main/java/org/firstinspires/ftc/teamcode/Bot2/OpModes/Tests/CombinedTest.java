package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@Disabled
@TeleOp
public class CombinedTest extends LinearOpMode {


    private boolean wasA;
    private boolean wasY;
    private boolean wasX;
    private boolean wasB;
    private int mode = 0; //currently being used to test servos - set to 1 for both motors and servos

    private double servoTargetPosition;
    private final double multiplier = 0.0001;
    private int servoMode = 1;
    //    private int countTest = 0;
//    private int aCountTest = 0;
//    private int modeN1Time = 0;

    private final int numServos = 1; // ITS RIGHT HERE OPEN YOUR EYES

    private Servo[] servo;
    private ServoController controller;
    private int currentServo = 0;

    private final int motorIncrement = 10000;
    private int motorMode = 1;

    private final int numMotors = 0;

    private double motorVelocity = 1;
    private double motorTargetPosition;
    private DcMotor[] motor;
    private int currentMotor = 0;
    private double LJY;
    private double RJY;
    private enum state{
        RUN,
        RIGHT,
        LEFT,
        REST
    }
    private state currentState = state.REST;

    @Override
    public void runOpMode(){



        servo = new Servo[numServos];
        for(int i = 0; i<servo.length; i++) {
            servo[i] = hardwareMap.get(Servo.class, "servo" + i);
        }


//        motor = new DcMotor[numMotors];
//        for(int i = 0; i<motor.length; i++){
//            motor[i] = hardwareMap.get(DcMotor.class, "motor"+i);
//            motor[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        }

//        controller = new ServoController()

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        int countTest = 0; //todo REMOVE THIS LIL
        while(opModeIsActive()){

            countTest+=1;

            LJY = gamepad1.left_stick_y;
            RJY = -gamepad1.right_stick_y;

            if(gamepad1.a && !wasA){
                currentState = state.RUN;
                wasA = true;
            } else if(gamepad1.right_bumper){
                currentState = state.RIGHT;
                telemetry.addLine("You did in fact press the right bumper, great job!");
            } else if(gamepad1.left_bumper){
                currentState = state.LEFT;
            } else {
                currentState = state.REST;
            }

            if(!gamepad1.a){
                wasA = false;
            }
            if(Math.abs(LJY)<0.05){
                LJY = 0;
            }
            if(Math.abs(RJY)<0.05){
                RJY = 0;
            }
            if(!gamepad1.x){
                wasX = false;
            }


            if(!gamepad1.y){
                wasY = false;
            } if(gamepad1.y && !wasY){
                mode *= -1;
                motorMode = 1;
                servoMode = 1;
                wasY = true;
            }

            if(mode == 1){
                telemetry.addData("CurrentMode", "Motors");
            } else {
                telemetry.addData("CurrentMode", "Servos");
            }
            if(mode == 1){

//                Motor Mode
//                if(!gamepad1.x){
//                    wasX = true;
//                }
//                if(gamepad1.x && !wasX){
//                    motorMode *= -1;
//                    wasX = true;
//                }
//
////                Current Motor
//                if(currentState == state.LEFT){
//                    currentMotor -=1;
//                    if(currentMotor<0){
//                        currentMotor = numMotors-1;
//                    }
//                }
//                if(currentState == state.RIGHT){
//                    currentMotor +=1;
//                    currentMotor %= numMotors;
//                }
//
////                Velocity
//                if (RJY > 0.4) {
//                    if(motorVelocity == 0){
//                        motorVelocity = 0.15;
//                    }
//                    motorVelocity = Range.clip(motorVelocity * (1 / 0.999), -1, 1);
//                }
//                if (RJY < -0.4) {
//                    if(motorVelocity == 0){
//                        motorVelocity = 0.06;
//                    }
//                    motorVelocity = motorVelocity * 0.999;
//                }
//                if(gamepad1.dpad_down){
//                    motorVelocity = 0;
//                }
//
////                Target Position
//                motorTargetPosition += -gamepad1.left_stick_y/motorIncrement;
//
////                Direction
//                if(!gamepad1.b){
//                    wasB = false;
//                }
//                if(gamepad1.b && !wasB){
//                    motorVelocity *= -1;
//                    wasB = true;
//                }
//
////                Run
//                if(motorMode == 1){
//                    if(currentState == state.RUN) {
//
//                        motor[currentMotor].setTargetPosition((int) motorTargetPosition);
//                        motor[currentMotor].setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                        motor[currentMotor].setPower(motorVelocity);
//                        wasA = true;
//                    } else {
//                        motor[currentMotor].setPower(0);
//                    }
//                } else{
//                    motor[currentMotor].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                    motor[currentMotor].setPower(motorVelocity);
//                }
//
////                Telemetry
//                telemetry.addData("Velocity", motorVelocity);
//                telemetry.addData("TargetPosition", motorTargetPosition);
//                telemetry.addData("CurrentMotor", currentMotor);
//                telemetry.addData("NumberOfMotors", numMotors);
//                if(motorMode == 1){
//                    telemetry.addData("MotorMode", "Manual");
//                } else{
//                    telemetry.addData("MotorMode", "Continuous");
//                }
//

            } else {

//                Servo Mode
//                if(!gamepad1.x){
//                    wasX = false;
//                }
                if(gamepad1.x && !wasX){
                    servoMode *= -1;
                    wasX = true;
                }

//                Current Servo
                if(currentState == state.LEFT){
                    currentServo -=1;
                    if(currentServo<0) {
                        currentServo = numServos-1;
                    }
                    servoTargetPosition = servo[currentServo].getPosition();
                }
                if(currentState == state.RIGHT){
                    currentServo +=1;
                    currentServo %= numServos;
                    servoTargetPosition = servo[currentServo].getPosition();
                }

//                Target Position
                servoTargetPosition += gamepad1.left_stick_y*0.001;
                if(servoTargetPosition > 1){
                    servoTargetPosition = 1;
                }
                else if(servoTargetPosition < 0){
                    servoTargetPosition = 0;
                }

//                Run
                if(servoMode == 1) {
                    if (currentState == state.RUN) {
                        servo[currentServo].setPosition(servoTargetPosition);
//                    aCountTest+=1;
                    }
                }
                else{
                    servo[currentServo].setPosition(servoTargetPosition);
//                modeN1Time+=1;
                }

//                Telemetry
//            telemetry.addData("aWasPressed", aCountTest);
//            telemetry.addData("modeN1Time", modeN1Time);
                telemetry.addData("TargetPosition", servoTargetPosition*100 + "%");
                telemetry.addData("CurrentPosition", servo[currentServo].getPosition());
                telemetry.addData("CurrentServo", currentServo);
                if(servoMode == 1){
                    telemetry.addData("ServoMode", "Manual");
                } else{
                    telemetry.addData("ServoMode", "Continuous");
                }

            }

            telemetry.addData("CountTest", countTest);

            telemetry.update();
        }

    }
}
