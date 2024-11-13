package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Setup;
@TeleOp(name="MECH TEST")
public class MechTest extends LinearOpMode {
    Setup setup;
    Bot bot;
    IMUStatic imu;
    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        setup.disableMechanism("intakeMotor");
        setup.disableMechanism("drivetrain");
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        imu = new IMUStatic();
        bot.init();
        waitForStart();
        while(opModeIsActive()){
            if(-gamepad1.left_stick_y > 0.01){
                bot.intakeSlides.setTarget(IntakeSlides.MAX);
            } else if(-gamepad1.left_stick_y < -0.01){
                bot.intakeSlides.setTarget(IntakeSlides.MIN);
            }
            if(-gamepad1.right_stick_y > 0.01){
                bot.intakeSlides.setTarget(IntakeSlides.MAX);
            } else if(-gamepad1.right_stick_y < -0.01){
                bot.intakeSlides.setTarget(IntakeSlides.MIN);
            }
            if(gamepad1.y){
                bot.outtakeWrist.setTarget(OuttakeWrist.MAX);
            } else {
                bot.outtakeWrist.setTarget(OuttakeWrist.MIN);
            }
            if(gamepad1.b){
                bot.outtakeClaw.setTarget(OuttakeClaw.GRIP);
            } else {
                bot.outtakeClaw.setTarget(OuttakeClaw.MIN);
            }
        }
    }
}
