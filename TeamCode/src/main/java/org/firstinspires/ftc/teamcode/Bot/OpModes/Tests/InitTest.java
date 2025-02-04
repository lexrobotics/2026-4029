package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;

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
            bot.leftGripper.setVelocity(LeftGripper.INIT);
            bot.arm.setTarget(Arm.INIT);
            bot.wrist.setTarget(Wrist.INIT);
            bot.slides.setTarget(Slides.INIT);
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
        } else if(bot.slides.getCurrentPosition()>100){
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
