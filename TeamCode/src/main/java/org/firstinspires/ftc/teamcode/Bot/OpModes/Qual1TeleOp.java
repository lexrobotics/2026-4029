package org.firstinspires.ftc.teamcode.Bot.OpModes;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Sensors.LightStrip;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
import org.firstinspires.ftc.teamcode.Bot.States.IntakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.OuttakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.V4BState;

@TeleOp(name = "Qual1TeleOp", group = "0")
public class Qual1TeleOp extends LinearOpMode {
    private Setup setup;
    private Bot bot;
    private IMUStatic imu;
    private ElapsedTime timer;
    private double y;
    private double x;
    private double angle;
    private double translateMag;
    private double spin;
    private double imuOffset = 0;
    private OuttakeStates outtakeState = OuttakeStates.SCORE_PREP;
    private IntakeStates intakeStates = IntakeStates.REST;
//    private V4BState v4BState = V4BState.REST;
    private boolean v4bTracker = false;
    private boolean hasBPressed2 = false;
    private double D1GPM = 0.01;
    private ActionSequences actionSequences;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        setup.disableMechanism("Winch");

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        imu = new IMUStatic();
        actionSequences = new ActionSequences(bot);
//        LightStrip lights = new LightStrip("lights", RevBlinkinLedDriver.BlinkinPattern.BLUE);
        bot.init();
        while(opModeInInit()){
            bot.outtakeSlides.telemetry();
            telemetry.update();
            bot.intakeSlides.update();
        }
        waitForStart();

        bot.noodler.setVelocity(0);
        bot.update();

        while(opModeIsActive()){
            driver1();
            driver2();
            bot.update();
            telemetry.addData("state out", outtakeState);
            telemetry.addData("state in", intakeStates);
            telemetry.addData("noodler pos", bot.noodler.getVelocity());

            telemetry.update();
        }

    }
    private void updateLights(){

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

    private void driver2() {
//        Outtake to Net, Outtake for Specimen, Intake w/o extending, Intake w/ extended Noodler
//        sampleOuttake button = gamepad1.?
//        specimenOuttake button = gamepad1.?
//        unextendedIntake button = gamepad1.?
//        extendedIntake button = gamepad1.?

        if(gamepad2.right_trigger > 0.1){
            actionSequences.IntakeMotor(1, true);
        } else if(gamepad2.left_trigger > 0.1){
            actionSequences.IntakeMotor(-1, false);
        } else {
            actionSequences.IntakeMotor(0, false);
        }
        if(Math.abs(gamepad2.right_stick_y) > D1GPM){
            outtakeState = OuttakeStates.MANUAL;
        } else if(bot.outtakeSlides.isBusy()){
            outtakeState = OuttakeStates.MOVING;
        } else{
            if(gamepad2.a){
                outtakeState = OuttakeStates.REST;
            } else if(gamepad2.x){
                outtakeState = OuttakeStates.RU1;
            } else if(gamepad2.y){
                outtakeState = OuttakeStates.RU2;
            } else if(gamepad2.dpad_left){
                outtakeState = OuttakeStates.BU1;
            } else if(gamepad2.dpad_up){
                outtakeState = OuttakeStates.BU2;
            }else if (gamepad2.left_bumper){
                outtakeState = OuttakeStates.DROP;
            }else if (gamepad2.right_bumper) {
                outtakeState = OuttakeStates.HOLD;
            }else if (gamepad2.start){
                outtakeState = OuttakeStates.GRAB;
            }
        }
        if(Math.abs(gamepad2.left_stick_y) > D1GPM){
            intakeStates = IntakeStates.MANUAL;
        } else if(bot.outtakeSlides.isBusy()){
            intakeStates = IntakeStates.MOVING;
        } else{
            if(gamepad2.back){
                intakeStates = IntakeStates.TRANSFER;
            } else if(gamepad2.dpad_right){
                intakeStates = IntakeStates.POS2;
            } else if(gamepad2.dpad_down){
                intakeStates = IntakeStates.POS1;
            }
        }

        if(gamepad2.b){
            outtakeState = OuttakeStates.ANGLE;
        }
//        if(outtakeState != OuttakeStates.REST && outtakeState != OuttakeStates.MOVING && outtakeState != OuttakeStates.MANUAL){
////            v4BState = V4BState.ANGLED_UP;
//            v4bTracker = true;
//        }
//        if(gamepad2.b && !hasBPressed2){
//            hasBPressed2 = true;
//            v4bTracker = !v4bTracker;
//            if(v4bTracker){
//                4BState = V4BState.ANGLED_UP;
//            } else  {
//                v4BState = V4BState.REST;
//            } v
//        }

        switch(outtakeState){
            case BU1:
                actionSequences.Bucket1();
                break;
            case BU2:
                actionSequences.Bucket2();
                break;
            case RU1:
                actionSequences.Specimen1();
                break;
            case RU2:
                actionSequences.Specimen2();
                break;
            case REST:
                actionSequences.OuttakeRest(true);
//                actionSequences.AttemptTransfer(outtakeState, intakeStates);
                break;
            case SCORE_PREP:
                bot.v4b.setTarget(V4B.TRANSFER);
                bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
                bot.outtakeWrist.setTarget(OuttakeWrist.TRA);
            case MANUAL:
                actionSequences.ManualOuttakeSlides(gamepad2.right_stick_y);
                break;
            case DROP:
                bot.outtakeClaw.setTarget(OuttakeClaw.DROP);
                break;

            case TRANSFER:
                bot.outtakeSlides.setTarget(0);
                bot.v4b.setTarget(V4B.TRANSFER);
                bot.outtakeWrist.setTarget(OuttakeWrist.TRA);
                bot.outtakeClaw.setTarget(OuttakeClaw.DROP);
                break;

            case HOLD:
                bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
                break;

            case GRAB:
//                bot.outtakeClaw.setTarget(OuttakeClaw.DROP);
//                bot.outtakeSlides.setTarget(OuttakeSlides.GRA);
                bot.v4b.setTarget(V4B.HOR);
                bot.outtakeWrist.setTarget((OuttakeWrist.MIN));
                break;
            case ANGLE:
                bot.v4b.setTarget(V4B.ANG);
                bot.outtakeWrist.setTarget(OuttakeWrist.MIN);
                break;
        }

        switch(intakeStates){
            case MANUAL:
                actionSequences.ManualIntakeSlides(gamepad2.left_stick_y);
                break;
            case REST:
                actionSequences.IntakeRest();
//                actionSequences.AttemptTransfer(outtakeState, intakeStates);
                break;
            case POS1:
                actionSequences.IntakePos1();
                break;
            case POS2:
                actionSequences.IntakePos2();
                break;
            case TRANSFER:
                bot.intakeArm.setTarget(IntakeArm.TRANSFER);
                bot.intakeSlides.setTarget(0);
//                actionSequences.AttemptTransfer(outtakeState, IntakeStates.REST);
                break;
        }
//        switch(v4BState){
//            case REST:
//                actionSequences.V4BRest();
//                break;
//            case ANGLED_UP:
//                actionSequences.V4BAngled();
//                break;
//        }
    }

}