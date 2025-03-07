package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mLinkage;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTurret;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

@TeleOp(name = "STATES TELEOP", group = "0")
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
        REST, TRANSFER, TRANSFER_PREP, SCORE_PREP, SPEC_WALL_FRONT, SPEC_WALL_BACK
    }

    enum IntakeStates{
        REST, TRANSFER, TRANSFER_PREP, INTAKE_PREP
    }
    OuttakeStates outtakeState = OuttakeStates.REST;
    IntakeStates intakeState = IntakeStates.REST;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        setup.disableMechanism("OuttakeSlides");

        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        bot.init();

        waitForStart();

        while(opModeIsActive()){
            driver1();
            driver2();
            manual2();
            bot.update();
            telemetry.addData("outtake state", outtakeState);
            telemetry.addData("intake state", intakeState);
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

    boolean rightWasPressed = false;
    boolean intakeClawOpen = true;
    boolean leftWasPressed = false;
    boolean outtakeClawOpen = true;
    boolean scorePrepPressed = false;
    boolean transferring = true;

    double outtakeSlidesPosition = mOuttakeSlides.INIT;
    double outtakeWristPosition = mOuttakeWrist.INIT;
    double outtakeV4BPosition = mOuttakeV4B.INIT;

    double intakeSlidesPosition = mLinkage.INIT;

    boolean isOuttakeClawOpen = false;
    boolean isIntakeClawOpen = false;

    private void driver2() {
        /*
           CLAW LOGIC:
           1. if right bumper is pressed, intake claw changes from previous position
           2. if right bumper and left bumper are pressed, it goes to transfer
         */
        if(gamepad2.right_bumper && !rightWasPressed){
            if(isIntakeClawOpen){
                bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
            } else {
                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
            }
            isIntakeClawOpen = !isIntakeClawOpen;
            rightWasPressed = true;
        } else if(!gamepad2.right_bumper){
            rightWasPressed = false;
        }else if(gamepad2.right_bumper && gamepad2.left_bumper){
            outtakeState = OuttakeStates.TRANSFER_PREP;
        }

        if(gamepad2.left_bumper && !leftWasPressed){
            if(isOuttakeClawOpen){
                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
            } else {
                bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
            }
            isOuttakeClawOpen = !isOuttakeClawOpen;
            leftWasPressed = true;
        } else if(!gamepad2.left_bumper){
            leftWasPressed = false;
        }
//        if (gamepad2.right_bumper && !rightWasPressed && !gamepad2.left_bumper) {
//            if(intakeClawOpen){
//                bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
//            }else{
//                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
//            }
//            rightWasPressed = true;
//        } else if(!gamepad2.right_bumper){
//            rightWasPressed = false;
//        }
//        if(gamepad2.left_bumper && !leftWasPressed){
//            if(outtakeClawOpen){
//                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//            }else{
//                bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//            }
//            leftWasPressed = true;
//        } else if(!gamepad2.left_bumper){
//            leftWasPressed = false;
//        }

        /*
           SCORING LOGIC:
           1. b,x,y account for scoring high buckets, high scec, and low bucket respectively
           2. when they are first pressed, the intake retracts to transfer position
           3. the second time it is pressed extends it.
         */
        if((gamepad2.b || gamepad2.x || gamepad2.y) && !scorePrepPressed){
            if(transferring){
                intakeState = IntakeStates.REST;
            }else{
                if (gamepad2.b) {
                    intakeState = IntakeStates.TRANSFER;
                    outtakeSlidesPosition = mOuttakeSlides.HIGH_BUCKET;
                    outtakeV4BPosition = mOuttakeV4B.HIGH_BUCKET;
                    outtakeWristPosition = mOuttakeWrist.BUCKET;
                    outtakeState = OuttakeStates.SCORE_PREP;
                }else if(gamepad2.x){
                    intakeState = IntakeStates.TRANSFER;
                    outtakeSlidesPosition = mOuttakeSlides.HIGH_SPECIMEN;
                    outtakeV4BPosition = mOuttakeV4B.SPECIMEN;
                    outtakeWristPosition = mOuttakeWrist.SPECIMEN;
                    outtakeState = OuttakeStates.SCORE_PREP;
                }else if (gamepad2.y){
                    outtakeSlidesPosition = mOuttakeSlides.LOW_BUCKET;
                    outtakeV4BPosition = mOuttakeV4B.LOW_BUCKET;
                    outtakeWristPosition = mOuttakeWrist.BUCKET;
                    outtakeState = OuttakeStates.SCORE_PREP;
                }
                scorePrepPressed = true;
            }
        }else if (!(gamepad2.b || gamepad2.x || gamepad2.y)){
            scorePrepPressed = false;
        }

        //REST AND TRANSFER LOGIC
        if (gamepad2.a) {
            if(intakeState == IntakeStates.REST && !bot.linkage.isBusy()) {
                intakeState = IntakeStates.TRANSFER;
            } else {
                intakeState = IntakeStates.REST;
            }
            if(outtakeState == OuttakeStates.REST && !bot.outtakeSlides.isBusy()) {
                outtakeState = OuttakeStates.TRANSFER;
            } else {
                outtakeState = OuttakeStates.REST;
            }
        }

        //EXTEND INTAKE SUBMERSIBLE
        if(gamepad2.dpad_down){
            intakeState = IntakeStates.INTAKE_PREP;
            intakeSlidesPosition = mLinkage.EXTEND;
        }

        //INTAKE WALL SPEC FRONT
        if(gamepad2.dpad_right){
            intakeState = IntakeStates.REST;
            outtakeState = OuttakeStates.SPEC_WALL_FRONT;
        }
        //INTAKE WALL SPEC BACK
        if(gamepad2.dpad_left){
            outtakeState = OuttakeStates.SPEC_WALL_BACK;
            intakeState = IntakeStates.REST;
        }
//
//        if(outtakeState == OuttakeStates.REST && intakeState == IntakeStates.REST){
//            outtakeState = OuttakeStates.TRANSFER_PREP;
//            intakeState = IntakeStates.TRANSFER_PREP;
//        } else if(outtakeState == OuttakeStates.TRANSFER_PREP && intakeState == IntakeStates.TRANSFER_PREP){
//            outtakeState = OuttakeStates.TRANSFER;
//            intakeState = IntakeStates.TRANSFER;
//        }

        //INTAKE ROTATION LOGIC
        if (gamepad2.dpad_right){
            outtakeState = OuttakeStates.TRANSFER;
            intakeState = IntakeStates.INTAKE_RIGHT;
        }
        if (gamepad2.dpad_left){
            outtakeState = OuttakeStates.TRANSFER;
            intakeState = IntakeStates.INTAKE_LEFT;
        }
        if (gamepad2.dpad_down){
            outtakeState = OuttakeStates.TRANSFER;
            intakeState = IntakeStates.INTAKE_STRAIGHT;
        }

//        //SPECIMIN INTAKES
//        if(gamepad2.dpad_left){
//            outtakeState = OuttakeStates.REST;
//            intakeState = IntakeStates.INTAKE_SPEC_FRONT;
//        }
//        if(gamepad2.dpad_right){
//            outtakeState = OuttakeStates.REST;
//            intakeState = IntakeStates.INTAKE_SPEC_BACK;
//        }

        switch (outtakeState) {
            case REST:
                bot.outtakeSlides.setTarget(mOuttakeSlides.REST);
                bot.outtakeClaw.setTarget(mOuttakeClaw.REST);
                bot.outtakeV4B.setTarget(mOuttakeV4B.REST);
                bot.outtakeWrist.setTarget(mOuttakeWrist.REST);
                break;

            case SCORE_PREP:
              bot.outtakeSlides.setTarget(outtakeSlidesPosition);
              bot.outtakeV4B.setTarget(outtakeV4BPosition);
              if(outtakeV4BPosition == mOuttakeV4B.HIGH_BUCKET){
                  bot.outtakeWrist.setTarget(mOuttakeWrist.BUCKET2);
              } else if(outtakeV4BPosition == mOuttakeV4B.LOW_BUCKET){
                  bot.outtakeWrist.setTarget(mOuttakeWrist.BUCKET);
              } else {
                  bot.outtakeWrist.setTarget(mOuttakeWrist.SPECIMEN);
              }

            case TRANSFER:
                bot.outtakeSlides.setTarget(mOuttakeSlides.TRANSFER);
                bot.outtakeClaw.setTarget(mOuttakeClaw.TRANSFER);
                bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
                bot.outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
                break;
            case SCORE_PREP:
                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
                bot.outtakeSlides.setTarget(outtakeSlidesPosition);
                bot.outtakeV4B.setTarget(outtakeV4BPosition);
                bot.outtakeWrist.setTarget(outtakeWristPosition);
                break;

        }
        switch (intakeState) {
            case REST:
                bot.linkage.setTarget(mLinkage.REST);
                bot.intakeClaw.setTarget(mIntakeClaw.REST);
                bot.intakeWrist.setTarget(mIntakeWrist.REST);
                bot.turret.setTarget(mTurret.REST);
                break;
            case TRANSFER:
                bot.linkage.setTarget(mLinkage.TRANSFER);
                bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
                bot.turret.setTarget(mTurret.TRANSFER);
                break;
            case INTAKE_LEFT:
                bot.intakeWrist.setTarget(mIntakeWrist.LEFT);
                break;
            case INTAKE_RIGHT:
                bot.intakeWrist.setTarget(mIntakeWrist.RIGHT);
                break;
        }
    }
    private void manual2(){
        double joystickL = gamepad2.left_stick_y;
        double joystickR = gamepad2.right_stick_y;
        bot.outtakeSlides.setVelocity(0.5);
        bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + (100/2)*(Math.signum(-joystickR)*(Math.pow(2,Math.abs(-joystickR) * 2)) - 1), 0, mOuttakeSlides.MAX));

        bot.linkage.setTarget(Range.clip(bot.linkage.getCurrentPosition() + (0.01)*(Math.signum(-joystickL)*(Math.pow(2,Math.abs(-joystickL) * 2)) - 1), 0, mLinkage.MAX));
    }
}