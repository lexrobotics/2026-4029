package org.firstinspires.ftc.teamcode.Bot.OpModes;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Sensors.LightStrip;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
import org.firstinspires.ftc.teamcode.Bot.States.IntakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.OuttakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.V4BState;

@TeleOp(name = "Blue", group = "0")
public class Q1TeleBlue extends LinearOpMode {
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
    private OuttakeStates outtakeState = OuttakeStates.TRANSFER;
    private IntakeStates intakeStates = IntakeStates.REST;
    private V4BState v4BState = V4BState.REST;
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
        }
        waitForStart();

        while(opModeIsActive()){
            driver1();
            driver2();
            bot.update();
        }

    }
    private void updateLights(){

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

    private void driver2() {
//        Outtake to Net, Outtake for Specimen, Intake w/o extending, Intake w/ extended Noodler
//        sampleOuttake button = gamepad1.?
//        specimenOuttake button = gamepad1.?
//        unextendedIntake button = gamepad1.?
//        extendedIntake button = gamepad1.?

        if(gamepad2.right_trigger > 0.1){
            actionSequences.IntakeMotor(1);
        } else if(gamepad2.left_trigger > 0.1){
            actionSequences.IntakeMotor(-1);
        } else {
            actionSequences.IntakeMotor(0);
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
            }
        }
        if(Math.abs(gamepad2.left_stick_y) > D1GPM){
            intakeStates = IntakeStates.MANUAL;
        } else if(bot.outtakeSlides.isBusy()){
            intakeStates = IntakeStates.MOVING;
        } else{
            if(gamepad2.left_bumper){
                intakeStates = IntakeStates.REST;
            } else if(gamepad2.dpad_right){
                intakeStates = IntakeStates.POS2;
            } else if(gamepad2.dpad_left){
                intakeStates = IntakeStates.POS1;
            }
        }
        if(!gamepad2.b){
            hasBPressed2 = false;
        }
        if(outtakeState != OuttakeStates.REST && outtakeState != OuttakeStates.MOVING && outtakeState != OuttakeStates.MANUAL){
            v4BState = V4BState.ANGLED_UP;
            v4bTracker = true;
        }
        if(gamepad2.b && !hasBPressed2){
            hasBPressed2 = true;
            v4bTracker = !v4bTracker;
            if(v4bTracker){
                v4BState = V4BState.ANGLED_UP;
            } else  {
                v4BState = V4BState.REST;
            }
        }

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
                break;
            case TRANSFER:
                actionSequences.PrepScore();
            case MANUAL:
                actionSequences.ManualOuttakeSlides(gamepad2.right_stick_y);
                break;
        }

        switch(intakeStates){
            case MANUAL:
                actionSequences.ManualIntakeSlides(gamepad2.left_stick_y);
                break;
            case REST:
                actionSequences.IntakeRest();
                break;
            case POS1:
                actionSequences.IntakePos1();
                break;
            case POS2:
                actionSequences.IntakePos2();
                break;
        }
        switch(v4BState){
            case REST:
                actionSequences.V4BRest();
                break;
            case ANGLED_UP:
                actionSequences.V4BAngled();
                break;
        }
    }

}