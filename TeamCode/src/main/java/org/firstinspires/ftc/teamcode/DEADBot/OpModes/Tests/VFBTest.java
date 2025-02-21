//package org.firstinspires.ftc.teamcode.DEADBot.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.Servo;
//
//@Disabled
//@TeleOp
//public class VFBTest extends LinearOpMode {
//
//    private Servo arm;
//    private Servo wrist;
//
//
//    private double[] positions;
//
//    private final double zeroed = 0;
//    private final double maxHeight = 0.56; //estimated value
//    private final double furthest = 1;
//
//    private int current = 0;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        arm = hardwareMap.get(Servo.class, "Arm");
//        wrist = hardwareMap.get(Servo.class, "Wrist");
//
//        positions = new double[] {zeroed, maxHeight, furthest};
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        while(opModeIsActive()){
//
//            if(gamepad1.x){
//                current +=1;
//                if(current>2){
//                    current = 0;
//                }
//            }
//            if(gamepad1.y){
//                current -=1;
//                if(current<0){
//                    current = 2;
//                }
//            }
//
//            if(gamepad1.a){
//                arm.setPosition(positions[current]);
//            }
//
//            telemetry.addLine("Current Position: "+arm.getPosition());
//            telemetry.addLine("New Position: " + current + ". Value: " + positions[current]);
//            telemetry.update();
//        }
//
//    }
//
//}
