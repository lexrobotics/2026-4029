//package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Wrist;
//import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//@TeleOp(name="MECH TEST")
//public class MechTest extends LinearOpMode {
//    Setup setup;
//    Bot bot;
//    IMUStatic imu;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
//        setup.disableMechanism("intakeMotor");
//        setup.disableMechanism("drivetrain");
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        imu = new IMUStatic();
//        bot.init();
//        waitForStart();
//        while(opModeIsActive()){
//            if(-gamepad1.left_stick_y > 0.01){
//                telemetry.addLine("IS:MA");
//                bot.intakeSlides.setTarget(IntakeSlides.MAX);
//            } else if(-gamepad1.left_stick_y < -0.01){
//                telemetry.addLine("IS:MI");
//                bot.intakeSlides.setTarget(IntakeSlides.MIN);
//            }
//            if(-gamepad1.right_stick_y > 0.01){
//                telemetry.addLine("OS:MA");
//                bot.outtakeSlides.setTarget(OuttakeSlides.MAX);
//            } else if(-gamepad1.right_stick_y < -0.01){
//                telemetry.addLine("OS:MI");
//                bot.outtakeSlides.setTarget(OuttakeSlides.MIN);
//            }
//            if(gamepad1.y){
//                telemetry.addLine("OW:MA");
//                bot.outtakeWrist.setTarget(Wrist.MAX);
//            } else {
//                telemetry.addLine("OW:MI");
//                bot.outtakeWrist.setTarget(Wrist.MIN);
//            }
//            if(gamepad1.b){
//                telemetry.addLine("GR:GR");
//                bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//            } else {
//                telemetry.addLine("GR:MI");
//                bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//            }
//            telemetry.update();
//            bot.update();
//        }
//    }
//}
