//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.SensorColorDistance;
//
//@Disabled
//@TeleOp
//public class ColorDistanceSensorTest extends LinearOpMode {
//    private SensorColorDistance sensor;
//    private double[] colors;
//    private double distance;
//
//    private final int minColor = 30;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        sensor = new SensorColorDistance("sensor", hardwareMap);
//        telemetry.addData("Status", "Initialized'");
//        telemetry.update();
//
//        waitForStart();
//
//        while(opModeIsActive()){
//            colors = sensor.getColor();
//            distance = sensor.getDistance(DistanceUnit.CM);
//
//            telemetry.addLine("I see " + colors[2] + " yellow!");
//            telemetry.addLine("I see " + colors[1] + " red!");
//            telemetry.addLine("I see " + colors[3] + " blue!");
//
//            if(1.5*colors[2] > colors[1] && colors[2] > colors[3]) {
//                telemetry.addLine("It's yellow!");
//            } else if(colors[1] > colors[2] && colors[1] > colors[3]){
//                telemetry.addLine("It's red!");
//            } else if(colors[3] > colors[1] && colors[3] > colors[2]){
//                telemetry.addLine("It's blue!");
//            } else{
//                telemetry.addLine("I can't see any colors!");
//            }
//
//            telemetry.addLine("It's " + Math.round(distance * 1000)/1000.0 + " cm away!");
//
//            telemetry.update();
//        }
//    }
//}
