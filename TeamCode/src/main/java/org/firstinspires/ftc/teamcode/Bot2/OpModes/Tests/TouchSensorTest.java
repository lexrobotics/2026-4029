package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.SensorTouch;

@Disabled
@TeleOp
public class TouchSensorTest extends LinearOpMode {
    private SensorTouch sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        sensor = new SensorTouch("sensor", false, hardwareMap);
        telemetry.addData("Status", "Initialized'");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

            if(sensor.getStatus()){
                telemetry.addLine("Boop!");
            } else {
                telemetry.addLine("No boop >:3");
            }
            telemetry.addLine(sensor.toString());

            telemetry.update();
        }
    }
}
