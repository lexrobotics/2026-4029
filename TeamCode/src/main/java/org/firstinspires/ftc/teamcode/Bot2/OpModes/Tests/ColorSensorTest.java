package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorColorDistance;
//import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorColor;

@TeleOp
public class ColorSensorTest extends LinearOpMode{

    private double[] colors;
    private double distance;
    private SensorColorDistance sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        sensor = new SensorColorDistance("sensor", hardwareMap);
        //sensor = hardwareMap.get(SensorColorDistance.class, "sensor");

        telemetry.addLine("Ready!");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            colors = sensor.getColor();
            distance = sensor.getDistance(DistanceUnit.CM);
            String ball_color = "N/A";

            if(distance < 7.0){
                if((colors[1] > colors[2])){
                    ball_color = "Purple";
                } else {
                    ball_color = "Green";
                }
            } else {
                ball_color = "N/A";
            }

            telemetry.addLine("I see " + colors[1] + " red");
            telemetry.addLine("I see " + colors[2] + " green");
            telemetry.addLine("I see " + colors[3] + " blue");
            telemetry.addLine("Distance: " + distance);
            telemetry.addLine("I see a " + ball_color + " ball");
            telemetry.update();
        }
    }
}
