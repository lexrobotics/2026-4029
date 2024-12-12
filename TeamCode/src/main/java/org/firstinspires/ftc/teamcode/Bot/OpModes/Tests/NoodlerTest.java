package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="NoodlerTest", group = "0")
public class NoodlerTest extends LinearOpMode {
    private CRServo noodler;
    @Override
    public void runOpMode(){
        noodler = hardwareMap.crservo.get("Noodler");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                noodler.setPower(50);
            }
            if (gamepad1.b) {
                noodler.setPower(-50);
            }
            if (gamepad1.atRest()) {
                noodler.setPower(0);
            }
            telemetry.update();
        }
    }
}
