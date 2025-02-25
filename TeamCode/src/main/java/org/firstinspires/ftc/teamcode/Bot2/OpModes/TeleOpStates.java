package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

@TeleOp(name = "PICK MEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE STATES TELEOP", group = "0")
public class TeleOpStates extends LinearOpMode {
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
    private double wrist;

    enum OuttakeStates{
        REST, TRANSFER, TRANSFER_PREP, SCORE_PREP
    }

    enum IntakeStates{
        REST, TRANSFER, TRANSFER_PREP, INTAKE_PREP
    }
    OuttakeStates outtakeState = OuttakeStates.REST;
    IntakeStates intakeState = IntakeStates.REST;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        setup.disableMechanism("IntakeClaw");
        setup.disableMechanism("IntakeSlides");
        setup.disableMechanism("IntakeRotation");
        setup.disableMechanism("IntakeWrist");
        setup.disableMechanism("IntakeCDSensor");
        setup.disableMechanism("IntakeTouchSensor");


        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        bot.init();

        waitForStart();

        while(opModeIsActive()){
            driver1();
            driver2();
            manual2();
            bot.update();
            telemetry.addData("state", outtakeState);
            telemetry.addData("state", intakeState);
            telemetry.update();
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
        } else if(bot.outtakeSlides.getCurrentPosition()>900){
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
    double MANUAL_WRIST_INCREMENT = 0.0007;

    boolean rightWasPressed;
    boolean intakeClawOpen = true;
    boolean leftWasPressed;
    boolean outtakeClawOpen = true;

    double outtakeSlidesPosition = OuttakeSlides.INIT;
    double outtakeV4BPosition = OuttakeV4B.INIT;

    private void driver2() {
        /*
           CLAW LOGIC:
           1. if right bumper is pressed, intake claw changes from previous position
           2. if right bumper and left bumper are pressed, it goes to transfer

         */
        if (gamepad2.right_bumper && !rightWasPressed && !gamepad2.left_bumper) {
            if(intakeClawOpen){
                bot.intakeClaw.setTarget(IntakeClaw.CLOSE);
            }else{
                bot.intakeClaw.setTarget(IntakeClaw.OPEN);
            }
            rightWasPressed = true;
        } else if(!gamepad2.right_bumper){
            rightWasPressed = false;
        }else if(gamepad2.right_bumper && gamepad2.left_bumper){
            outtakeState = OuttakeStates.TRANSFER_PREP;
        }
        if(gamepad2.left_bumper && !leftWasPressed){
            if(outtakeClawOpen){
                bot.outtakeClaw.setTarget(OuttakeClaw.CLOSE);
            }else{
                bot.outtakeClaw.setTarget(OuttakeClaw.OPEN);
            }
            leftWasPressed = true;
        } else if(!gamepad2.left_bumper){
            leftWasPressed = false;
        }

        // SCORING, excluding low specimen
        if (gamepad2.b) {
            outtakeSlidesPosition = OuttakeSlides.HIGH_BUCKET;
            outtakeV4BPosition = OuttakeV4B.HIGH_BUCKET;
            outtakeState = OuttakeStates.SCORE_PREP;
        }else if(gamepad2.x){
            outtakeSlidesPosition = OuttakeSlides.HIGH_SPECIMEN;
            outtakeV4BPosition = OuttakeV4B.HIGH_SPECIMEN;
            outtakeState = OuttakeStates.SCORE_PREP;
        }else if (gamepad2.y){
            outtakeSlidesPosition = OuttakeSlides.LOW_BUCKET;
            outtakeV4BPosition = OuttakeV4B.LOW_BUCKET;
            outtakeState = OuttakeStates.SCORE_PREP;
        }

        //REST AND TRANSFER LOGIC
        if (gamepad2.a || gamepad2.dpad_down) {
            outtakeState = OuttakeStates.REST;
            intakeState = IntakeStates.REST;
        }

        if(outtakeState == OuttakeStates.REST && intakeState == IntakeStates.REST){
            outtakeState = OuttakeStates.TRANSFER_PREP;
            intakeState = IntakeStates.TRANSFER_PREP;
        } else if(outtakeState == OuttakeStates.TRANSFER_PREP && intakeState == IntakeStates.TRANSFER_PREP){
            outtakeState = OuttakeStates.TRANSFER;
            intakeState = IntakeStates.TRANSFER;
        }

        switch (outtakeState) {
            case REST:
                bot.outtakeSlides.setTarget(OuttakeSlides.REST);
                bot.outtakeClaw.setTarget(OuttakeClaw.REST);
                bot.outtakeV4B.setTarget(OuttakeV4B.REST);
                break;
            case TRANSFER:
                bot.outtakeSlides.setTarget(OuttakeSlides.TRANSFER);
                bot.outtakeClaw.setTarget(OuttakeClaw.TRANSFER);
                bot.outtakeV4B.setTarget(OuttakeV4B.TRANSFER);
                break;
            case TRANSFER_PREP:
                bot.outtakeSlides.setTarget(OuttakeSlides.TRANSFER_PREP);
                bot.outtakeClaw.setTarget(OuttakeClaw.TRANSFER_PREP);
                bot.outtakeV4B.setTarget(OuttakeV4B.TRANSFER_PREP);
                break;
        }
        switch (intakeState) {
            case REST:
                bot.intakeSlides.setTarget(IntakeSlides.REST);
                bot.intakeClaw.setTarget(IntakeClaw.REST);
                bot.intakeRotation.setTarget(Turret.REST);
                break;
            case TRANSFER:
                bot.intakeSlides.setTarget(IntakeSlides.TRANSFER);
                bot.intakeClaw.setTarget(IntakeClaw.TRANSFER);
                bot.intakeRotation.setTarget(Turret.TRANSFER);
                break;
            case TRANSFER_PREP:
                bot.intakeSlides.setTarget(IntakeSlides.TRANSFER_PREP);
                bot.intakeClaw.setTarget(IntakeClaw.TRANSFER_PREP);
                bot.intakeRotation.setTarget(Turret.TRANSFER_PREP);
                break;
        }
    }
    private void manual2(){
    }
}