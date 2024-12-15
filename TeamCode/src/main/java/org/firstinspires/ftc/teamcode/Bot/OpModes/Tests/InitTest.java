package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Noodler;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
@Config
@TeleOp(name = "InitTest", group = "0")
public class InitTest extends LinearOpMode {
    Setup setup;
    Bot bot;
    private double y;
    private double x;
    private double angle;
    private double translateMag;
    private double spin;
    private double imuOffset = 0;

    @Override
    public void runOpMode(){
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        setup.disableMechanism("Winch");
//        setup.disableMechanism("V4B");

//        setup.disableMechanism("IntakeSlides");



        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        bot.init();
        waitForStart();

        while(opModeIsActive()){
            driver1();
            bot.noodler.setVelocity(Noodler.INIT);
            bot.winch.setTarget(Winch.INIT);
            bot.v4b.setTarget(V4B.INIT);
            bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
            bot.outtakeWrist.setTarget(OuttakeWrist.INIT);
            bot.outtakeSlides.setTarget(OuttakeSlides.INIT);
            bot.intakeSlides.setTarget(IntakeSlides.INIT);
            bot.intakeArm.setTarget(IntakeArm.INIT);


            bot.update();
        }


    }
    private void driver1(){
        if (gamepad1.left_trigger > 0.1){
            imuOffset = bot.drivetrain.getHeadingIMU();
        }

        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        translateMag = Math.sqrt(x*x + y*y);
        angle = Math.atan2(y, x);

        angle += (-bot.drivetrain.getHeadingIMU() + imuOffset);


        x = Math.cos(angle) * translateMag;
        y = Math.sin(angle) * translateMag;

        if (gamepad1.left_bumper) {
            x *= 0.25;
            y *= 0.25;
            spin *= 0.25;
        } else if(bot.outtakeSlides.getCurrentPosition()>100){
            x *= 0.7;
            y *= 0.7;
            spin *= 0.4;
        } else {
            x *= .95;
            y *= 1;
            spin *= .7;
        }

        bot.drivetrain.setTeleOpTargets(x,y,spin);
        telemetry.addData("translateMag", translateMag);
        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("spin", spin);
        telemetry.addData("angle", angle);
    }
}
