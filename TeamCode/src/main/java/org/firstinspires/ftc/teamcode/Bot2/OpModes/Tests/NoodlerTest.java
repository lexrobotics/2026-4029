package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp(name="NoodlerTest", group = "0")
public class NoodlerTest extends LinearOpMode {
    private CRServo noodler;
    private DcMotorEx motor;
    private int currentPos = 0 ;
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotorEx.class, "IntakeSlides");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor.setTargetPosition(currentPos);
            }
            if (gamepad1.b) {
                motor.setTargetPosition(currentPos + 5);
            }
            if (gamepad1.atRest()) {
                motor.setTargetPosition(currentPos);
            }
            telemetry.update();
        }
    }
}
