package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mLinkage;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTurret;
import org.firstinspires.ftc.teamcode.Bot2.Setup;



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


        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        bot.init();
        waitForStart();

        while(opModeIsActive()){
            driver1();
            bot.intakeClaw.init(mIntakeClaw.INIT);
            bot.linkage.init(mLinkage.INIT);
            bot.turret.init(mTurret.INIT);
            bot.intakeWrist.init(mIntakeWrist.INIT);
            bot.outtakeClaw.init(mOuttakeClaw.INIT);
            bot.outtakeSlides.init(mOuttakeSlides.INIT);
            bot.outtakeV4B.init(mOuttakeV4B.INIT);
            bot.outtakeWrist.init(mOuttakeWrist.INIT);
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
