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


    @Override
    public void runOpMode(){
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        setup.disableMechanism("Winch");
        setup.disableMechanism("OuttakeSlides");
        setup.disableMechanism("IntakeSlides");



        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        bot.init();
        waitForStart();

        while(opModeIsActive()){
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
}
