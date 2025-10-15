//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Old;
//package org.firstinspires.ftc.teamcode.Bot.OpModes;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//
//public class LinearOpModeTemplate extends LinearOpMode {
//    Bot bot;
//    Setup setup;
//    ElapsedTime timer = new ElapsedTime();
//
//    @Override
//    public void runOpMode()throws InterruptedException {
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
//        setup.disableMechanism("outtakeSlides");
//
//        bot = new Bot(setup.mechStates, setup.sensorStates);
//        bot.init();
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//        timer.reset();
//
//        while (opModeIsActive()){
//            driver1();
//            driver2();
//            manualDriver2();
//
//            bot.update();
//            bot.telemetry();
//        }
//
//    }
//    double x, y, spin, angle, translateMag;
//    double imuOffset = Math.toRadians(0);
//    void driver1(){
//        if (gamepad1.left_trigger > 0.1){
//            imuOffset = bot.drivetrain.getHeadingIMU();
//        }
//
//        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
//        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
//        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
//        translateMag = Math.sqrt(x*x + y*y);
//        angle = Math.atan2(y, x);
//
//        angle += (-bot.drivetrain.getHeadingIMU() + imuOffset);
//
//
//        x = Math.cos(angle) * translateMag;
//        y = Math.sin(angle) * translateMag;
//
//        if (gamepad1.left_bumper) {
//            x *= 0.25;
//            y *= 0.25;
//            spin *= 0.25;
//        } else if(bot.outtakeSlides.getCurrentPosition()>100){
//            x *= 0.7;
//            y *= 0.7;
//            spin *= 0.4;
//        }
////        else if (gamepad1.right_bumper) {
////            x *= 1;
////            y *= 1;
////            spin *= 1;
////        }
//        else {
//            x *= .95;
//            y *= 1;
//            spin *= .7;
//        }
//
//        bot.drivetrain.setPowerTargets(new double[]{x, y, spin}, false, false);
//        telemetry.addData("translateMag", translateMag);
//        telemetry.addData("x", x);
//        telemetry.addData("y", y);
//        telemetry.addData("spin", spin);
//        telemetry.addData("angle", angle);
//
//    }
//    }
//}
