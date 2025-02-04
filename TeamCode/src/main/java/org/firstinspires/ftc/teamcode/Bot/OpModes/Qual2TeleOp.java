package org.firstinspires.ftc.teamcode.Bot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
@Disabled
@TeleOp
public class Qual2TeleOp extends LinearOpMode {
    Bot bot;
    Setup setup;
    ElapsedTime timer =new ElapsedTime();
    ActionSequences actionSequences;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        actionSequences = new ActionSequences(bot);
        bot.init();

        double imuOffset = 0;

        waitForStart();
        timer.reset();

        while(opModeIsActive()){
            driverOne(imuOffset, actionSequences);
            driverTwo(actionSequences);
            bot.update();
            bot.telemetry();
            telemetry.update();
        }
    }
    private void driverOne(double imuOffset, ActionSequences actionSequences){

        if (gamepad1.a){
            imuOffset = bot.drivetrain.getHeadingIMU();
        }

        double x = Math.abs(gamepad1.left_stick_x) > 0.04 ? gamepad1.left_stick_x : 0;
        double y = Math.abs(gamepad1.left_stick_y) > 0.04 ? -gamepad1.left_stick_y : 0;
        double spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        double translateMag = Math.sqrt(x * x + y * y);
        double angle = Math.atan2(y, x);

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

        if(gamepad1.dpad_down){
            actionSequences.hang(0);
        } else if(gamepad1.dpad_left){
            actionSequences.hang(1);
        } else if(gamepad1.dpad_up){
            actionSequences.hang(2);
        }

        bot.drivetrain.setTeleOpTargets(x, y, spin);
        Setup.telemetry.addData("translateMag", translateMag);
        Setup.telemetry.addData("x", x);
        Setup.telemetry.addData("y", y);
        Setup.telemetry.addData("spin", spin);
        Setup.telemetry.addData("angle", angle);
    }
    enum MechanismStates{
        REST, SCORE_PREP_SPEC, SCORE_PREP_SAMPLE, SCORE, MANUAL, EMERGENCY_STOP, INTAKE_PREP, INTAKE
    }
    private MechanismStates mechanismState = MechanismStates.REST;

    private void driverTwo(ActionSequences actionSequences){
        if(gamepad2.b){ // JUST SLIDES
            bot.slides.setTarget(950);
//            if(gamepad2.a || gamepad2.dpad_down){
//                bot.slides.setTarget(Slides.REST);
//            } else if(gamepad2.x){
//                bot.slides.setTarget(Slides.SPC1);
//            } else if(gamepad2.y){
//                bot.slides.setTarget(Slides.SPC2);
//            } else if(gamepad2.dpad_left){
//                bot.slides.setTarget(Slides.BUC1);
//            } else if(gamepad2.dpad_up){
//                bot.slides.setTarget(Slides.BUC2);
//            }
        } else {
            if(gamepad2.a || gamepad2.dpad_down){
                actionSequences.rest();
                mechanismState = MechanismStates.REST;
            } else if(gamepad2.x){
                actionSequences.specimenScorePrep(1);
                mechanismState = MechanismStates.SCORE_PREP_SPEC;
            } else if(gamepad2.y){
                actionSequences.specimenScorePrep(2);
                mechanismState = MechanismStates.SCORE_PREP_SPEC;
            } else if(gamepad2.dpad_left){
                actionSequences.sampleScorePrep(1);
                mechanismState = MechanismStates.SCORE_PREP_SAMPLE;
            } else if(gamepad2.dpad_up){
                actionSequences.sampleScorePrep(2);
                mechanismState = MechanismStates.SCORE_PREP_SAMPLE;
            }
        }
        if(gamepad2.right_trigger > 0.1 && bot.sensors.getTouchStatus(0)){ // SMART INTAKE
            actionSequences.intake(1);
        }else if (gamepad2.left_trigger > 0.1){ //EJECT
            actionSequences.intake(-1);
        }else if(gamepad2.right_trigger<0.1 && gamepad2.left_trigger < 0.1){ //REST
            actionSequences.intake(0);
        }

        if(gamepad2.left_bumper && mechanismState == MechanismStates.SCORE_PREP_SPEC){
            mechanismState = MechanismStates.SCORE;
            actionSequences.specimenOuttake();
        }
        if(gamepad2.right_bumper && mechanismState == MechanismStates.SCORE_PREP_SAMPLE){
            mechanismState = MechanismStates.SCORE;
            actionSequences.sampleOuttake();
        }
        driverTwoManual(bot);
    }
    private void driverTwoManual(Bot bot){
        double joystickL = gamepad2.left_stick_y;
        double joystickR = gamepad2.right_stick_y;
        bot.slides.setVelocity(0.5);
        bot.slides.setTarget(Range.clip(bot.slides.getCurrentPosition() + (100/2)*(Math.signum(-joystickR)*(Math.pow(2,Math.abs(-joystickR) * 2)) - 1), 0, Slides.MAX));

        bot.wrist.setTarget(Range.clip(bot.wrist.getCurrentPosition() + (0.01)*(Math.signum(-joystickL)*(Math.pow(2,Math.abs(-joystickL) * 2)) - 1), 0, Wrist.MAX));
    }

}
