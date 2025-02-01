package org.firstinspires.ftc.teamcode.Bot.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RightGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartIntake;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
import org.firstinspires.ftc.teamcode.Bot.States.IntakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.OuttakeStates;

@TeleOp(name = "QUAL 2 TELEOP", group = "0")
public class Qual2Tele extends LinearOpMode {
    private Setup setup;
    private Bot bot;
//    private IMUStatic imu;
    private ElapsedTime timer;
    private double y;
    private double x;
    private double angle;
    private double translateMag;
    private double spin;
    private double imuOffset = 0;
    private boolean v4bTracker = false;
    private boolean hasBPressed2 = false;
    private double D1GPM = 0.01;
    private double gamepad2LeftStick = 0;
    private SmartIntake smartIntake;

    private ActionSequences actionSequences;
    enum MechStates{
        REST, SCORE_PREP_SPEC, SCORE_PREP_SAMPLE, SCORE_SPEC, SCORE_SAMPLE, INTAKE, EJECT, MANUAL, MOVING, INTAKE_PREP
    }
    MechStates mechState = MechStates.REST;
    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        imu = new IMUStatic();
        actionSequences = new ActionSequences(bot);
//        LightStrip lights = new LightStrip("lights", RevBlinkinLedDriver.BlinkinPattern.BLUE);
        bot.init();
        smartIntake = new SmartIntake(actionSequences, false, bot.sensors);

        while(opModeInInit()){
            bot.slides.telemetry();
            telemetry.update();
//            bot.intakeSlides.update();
        }
        waitForStart();
        bot.update();

        while(opModeIsActive()){
            driver1();
            driver2();
            bot.update();
            telemetry.addData("state", mechState);
            telemetry.update();
            bot.arm.update();
        }

    }
    private void driver1(){
        if (gamepad1.a){
            imuOffset = bot.drivetrain.getHeadingIMU();
        }
        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        translateMag = Math.sqrt(x*x + y*y);
        angle = Math.atan2(y, x);

        angle += (-bot.drivetrain.getHeadingIMU() + imuOffset);
        telemetry.addLine("IMU Offset: " + imuOffset);
        telemetry.addLine("IMU Reading: " + (-bot.drivetrain.getHeadingIMU() + imuOffset));



        x = Math.cos(angle) * translateMag;
        y = Math.sin(angle) * translateMag;

        if (gamepad1.left_bumper) {
            x *= 0.25;
            y *= 0.25;
            spin *= 0.25;
        } else if(bot.slides.getCurrentPosition()>900){
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
    double MANUAL_OUTTAKE_SLIDES_INCREMENT = 150; //was 200
    double MANUAL_WRIST_INCREMENT = 0.01;

    private double SlidesPosition = 0;
    private void driver2() {

        if (gamepad2.right_trigger > 0.3 && gamepad2.left_trigger < 0.3) {
            bot.leftGripper.setTarget(LeftGripper.INTAKE);
            bot.rightGripper.setTarget(RightGripper.INTAKE);
        }
        if (gamepad2.left_trigger < 0.3 && gamepad2.right_trigger < 0.3) {
            bot.leftGripper.setTarget(LeftGripper.STOP);
            bot.rightGripper.setTarget(RightGripper.STOP);
        }
        if (gamepad2.left_trigger > 0.3 && gamepad2.right_trigger < 0.3) {
            bot.leftGripper.setTarget(LeftGripper.EJECT);
            bot.rightGripper.setTarget(RightGripper.EJECT);
        }
        if (gamepad2.right_bumper) {
            mechState = MechStates.INTAKE;
        }
        if(gamepad2.left_bumper){
            mechState = MechStates.INTAKE_PREP;
        }
        if (gamepad2.dpad_up) {
                bot.arm.setTarget((Arm.REST));
                bot.wrist.setTarget(Wrist.REST);
        }
        if (Math.abs(gamepad2.left_stick_y) > D1GPM || Math.abs(gamepad2.right_stick_y) > D1GPM) {
                mechState = MechStates.MANUAL;
        } else if (bot.slides.isBusy()) {
                mechState = MechStates.MOVING;
        } else {
                if (gamepad2.dpad_down) {
                    mechState = MechStates.REST;
                } else if (gamepad2.a) {
                    mechState = MechStates.SCORE_PREP_SAMPLE;
                    SlidesPosition = Slides.BUC1;
                } else if (gamepad2.x) {
                    mechState = MechStates.SCORE_PREP_SAMPLE;
                    SlidesPosition = Slides.BUC2;
                } else if (gamepad2.y) {
                    mechState = MechStates.SCORE_PREP_SPEC;
                    SlidesPosition = Slides.SPC2;
                }
        }
        switch (mechState) {
                case REST:
                    bot.slides.setTarget(Slides.REST);
                    bot.arm.setTarget(Arm.REST);
                    bot.wrist.setTarget(Wrist.REST);
                    break;
                case SCORE_PREP_SAMPLE:
                    bot.slides.setTarget(SlidesPosition);
                    bot.arm.setTarget(Arm.BUCKET);
                    bot.wrist.setTarget(Wrist.BUCKET);
                    break;
                case SCORE_PREP_SPEC:
                    bot.slides.setTarget(SlidesPosition);
                    bot.arm.setTarget(Arm.SPECIMEN);
                    bot.wrist.setTarget(Wrist.SPECIMEN);
                    break;
                case MANUAL:
                    bot.wrist.setTarget(Range.clip(0, bot.wrist.getCurrentPosition() + MANUAL_WRIST_INCREMENT * (-gamepad2.right_stick_y), 1));
                    bot.slides.setTarget(Range.clip(0, bot.slides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT * (-gamepad2.left_stick_y), Slides.MAX));
                    break;
                case INTAKE_PREP:
                    bot.arm.setTarget(Arm.INTAKE_PREP);
                    bot.wrist.setTarget(Wrist.INTAKE_PREP);
                    bot.slides.setTarget(Slides.INTAKE);
                case INTAKE:
                    bot.arm.setTarget(Arm.INTAKE);
                    bot.wrist.setTarget(Wrist.INTAKE);
                    bot.slides.setTarget(Slides.INTAKE);
        }

    }
}