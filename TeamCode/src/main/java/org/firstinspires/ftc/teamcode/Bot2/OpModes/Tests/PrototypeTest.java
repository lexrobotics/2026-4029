package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class PrototypeTest extends LinearOpMode{
    private Servo servo1; //Doesn't work why?
    private DcMotor motor1; //Doesn't work why?

    @Override
    public void runOpMode() throws InterruptedException {
        //Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        servo1 = hardwareMap.get(Servo.class, "servo1");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        waitForStart();

        double current_servo = 0.5;
        int current_motor = 1;
        while(opModeIsActive()) {
            double y_left = -gamepad1.left_stick_y;
            if (Math.abs(y_left) > 0.4){
                current_servo += y_left/1000;
                if (current_servo > 1){current_servo = 1;}
                if (current_servo < 0){current_servo = 0;}
                servo1.setPosition(current_servo);
                telemetry.addLine("current = " + current_servo);
                telemetry.update();
            }
            double y_right = -gamepad1.right_stick_y; // Motor code still doesn't work for some reason
            if (Math.abs(y_right) > 0.4){
                if (y_right > 0.0){
                    current_motor = 1;
                } else if (y_right < 0.0){
                    current_motor = -1;
                }
                motor1.setTargetPosition(current_motor);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.addLine("current = " + current_motor);
                telemetry.update();
            }
        }
    }
}
