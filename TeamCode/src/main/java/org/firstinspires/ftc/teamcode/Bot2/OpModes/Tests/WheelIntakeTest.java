//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.SensorColorDistance;
//import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.SensorTouch;
//
//@Disabled
//@TeleOp
//public class WheelIntakeTest extends LinearOpMode{
//
//    private CRServo servo1;
//    private CRServo servo2;
//    private double LJY;
//
//    private SensorColorDistance colorSensor;
//    private double[] colors;
////    private double distance;
//
//    private SensorTouch touchSensor;
//    private boolean touching;
//
//    private boolean run = true;
//    private boolean autoRun = false;
//
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        servo1 = hardwareMap.get(CRServo.class, "servo1");
//        servo2 = hardwareMap.get(CRServo.class, "servo2");
//        servo2.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        colorSensor = new SensorColorDistance("colorSensor", hardwareMap);
//        touchSensor = new SensorTouch("touchSensor", false, hardwareMap);
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        while(opModeIsActive()){
//            LJY = -gamepad1.left_stick_y;
//            colors = colorSensor.getColor();
////            distance = colorSensor.getDistance(DistanceUnit.CM);
//            touching = touchSensor.getStatus();
//
//            // gamepad1.y acts as the reset button after the wheels stop
//            if(gamepad1.y){
//                run = true;
//                autoRun = false;
//            }
//
//            if(gamepad1.x){
//                autoRun = true;
//            }
//
//            if(autoRun){
//                servo1.setPower(-1);
//                servo2.setPower(-1);
//            } else if(run){
//                servo1.setPower(LJY);
//                servo2.setPower(LJY);
//            } else{
//                servo1.setPower(0);
//                servo2.setPower(0);
//            }
//
//            if(touching){
//                run = false;
//                autoRun = false;
//                telemetry.addLine("Alert: Contact has been made!");
//                if(1.5*colors[2] > colors[1] && colors[2] > colors[3]) {
//                    telemetry.addLine("It's yellow!");
//                } else if(colors[1] > colors[2] && colors[1] > colors[3]){
//                    telemetry.addLine("It's red!");
//                } else if(colors[3] > colors[1] && colors[3] > colors[2]){
//                    telemetry.addLine("It's blue!");
//                } else{
//                    telemetry.addLine("I can't see any colors!");
//                }
//            } else {
//                telemetry.addLine("Nothing inside");
//                telemetry.addLine("I can't see any colors!");
//            }
//
//
//
//            telemetry.addLine("I see " + colors[2] + " yellow!");
//            telemetry.addLine("I see " + colors[1] + " red!");
//            telemetry.addLine("I see " + colors[3] + " blue!");
//
////            telemetry.addLine("It's " + Math.round(distance * 1000)/1000.0 + " cm away!");
//
//            telemetry.update();
//        }
//    }
//}
