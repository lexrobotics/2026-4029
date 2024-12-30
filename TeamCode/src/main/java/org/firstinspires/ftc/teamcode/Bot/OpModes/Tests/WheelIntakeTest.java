package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class WheelIntakeTest extends LinearOpMode{

    private CRServo servo1;
    private CRServo servo2;
    private double LJY;


    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");
        servo2.setDirection(CRServo.Direction.REVERSE);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            LJY = -gamepad1.left_stick_y;
            servo1.setPower(LJY);
            servo2.setPower(LJY);
        }
    }
}
