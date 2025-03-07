package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

@TeleOp(name = "STATES TELEOP SIMPLE", group = "0")
public class TeleOpStatesSimple extends LinearOpMode {
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

    enum MechanismStates{
        REST, SCORE_PREP_SPEC, SCORE_PREP_SAMPLE, MANUAL_OUT_SLIDES, MANUAL_IN_SLIDES, EMERGENCY_STOP, INTAKE_EXTEND, TRANSFER, INTAKE_SPEC_FRONT, INTAKE_SPEC_BACK
    }
    MechanismStates mechanismStates = MechanismStates.REST;

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
            telemetry.addData(" state", mechanismStates);
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

    boolean rightBumperState = false;
    boolean intakeClawOpen = true;
    boolean leftBumperState = false;
    boolean outtakeClawOpen = true;
    boolean scorePrepPressed = false;
    boolean transferring = true;

    double outtakeSlidesPosition = mOuttakeSlides.INIT;
    double outtakeWristPosition = mOuttakeWrist.INIT;
    double outtakeV4BPosition = mOuttakeV4B.INIT;

    double intakeSlidesPosition = mLinkage.INIT;

    double intakeSlidesIncrement = 0.1;
    double outtakeSlidesIncrement = 0.1;

    private void driver2() {

        /*
           SCORING LOGIC:
           1. b,x,y account for scoring high buckets, high scec, and low bucket respectively
           2. when they are first pressed, the intake retracts to transfer position
           3. the second time it is pressed extends it.
         */

        if (gamepad2.b) {
            mechanismStates = MechanismStates.SCORE_PREP_SAMPLE;
            outtakeSlidesPosition = mOuttakeSlides.HIGH_BUCKET;
            outtakeV4BPosition = mOuttakeV4B.HIGH_BUCKET;
            outtakeWristPosition = mOuttakeWrist.BUCKET;
        }else if(gamepad2.x){
            mechanismStates = MechanismStates.SCORE_PREP_SPEC;
            outtakeSlidesPosition = mOuttakeSlides.HIGH_SPECIMEN;
            outtakeV4BPosition = mOuttakeV4B.SPECIMEN;
            outtakeWristPosition = mOuttakeWrist.SPECIMEN;
        }else if (gamepad2.y){
            mechanismStates = MechanismStates.SCORE_PREP_SPEC;
            outtakeSlidesPosition = mOuttakeSlides.LOW_BUCKET;
            outtakeV4BPosition = mOuttakeV4B.LOW_BUCKET;
            outtakeWristPosition = mOuttakeWrist.BUCKET;
        }



        //REST
        if (gamepad2.a) {
            mechanismStates = MechanismStates.REST;
        }

        //EXTEND INTAKE SUBMERSIBLE
        if(gamepad2.dpad_down){
            mechanismStates = MechanismStates.INTAKE_EXTEND;
            intakeSlidesPosition = mLinkage.EXTEND;
        }

        //INTAKE WALL SPEC FRONT
        if(gamepad2.dpad_right){
            mechanismStates = MechanismStates.INTAKE_SPEC_FRONT;
        }
        //INTAKE WALL SPEC BACK
        if(gamepad2.dpad_left){
            mechanismStates = MechanismStates.INTAKE_SPEC_BACK;

        }

        //TRANSFER
        if(gamepad2.dpad_up){
            mechanismStates = MechanismStates.TRANSFER;
        }

        switch (mechanismStates) {
            case REST:
                bot.setRest();
                break;
//            case SCORE_PREP_SAMPLE:
//                bot.outtakeSlides.setTarget(outtakeSlidesPosition);
//                bot.outtakeV4B.setTarget(outtakeV4BPosition);
//                bot.outtakeWrist.setTarget(outtakeWristPosition);
//                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
////                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
//                bot.linkage.setTarget(mLinkage.TRANSFER);
//                bot.turret.setTarget(mTurret.TRANSFER);
//                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
//                break;
            case SCORE_PREP_SPEC:
                bot.outtakeSlides.setTarget(outtakeSlidesPosition);
                bot.outtakeV4B.setTarget(outtakeV4BPosition);
                bot.outtakeWrist.setTarget(outtakeWristPosition);
                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
                bot.linkage.setTarget(mLinkage.TRANSFER);
                bot.turret.setTarget(mTurret.TRANSFER);
                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
                break;
            case INTAKE_EXTEND:
                bot.outtakeSlides.setTarget(mOuttakeSlides.REST);
                bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
                bot.outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
                bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
                bot.linkage.setTarget(mLinkage.EXTEND);
                bot.intakeWrist.setTarget(mIntakeWrist.INTAKE);
                bot.turret.setTarget(mTurret.INIT);
                break;

            case INTAKE_SPEC_BACK:
                bot.outtakeSlides.setTarget(mOuttakeSlides.INIT);
                bot.outtakeV4B.setTarget(mOuttakeV4B.INTAKE_SPEC_BACK);
                bot.outtakeWrist.setTarget(mOuttakeWrist.INTAKE_SPEC_BACK);
                bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
                bot.linkage.setTarget(mLinkage.REST);
                bot.turret.setTarget(mTurret.TRANSFER);
                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
                break;

            case INTAKE_SPEC_FRONT:
                bot.outtakeSlides.setTarget(mOuttakeSlides.INIT);
                bot.outtakeV4B.setTarget(mOuttakeV4B.INTAKE_SPEC_FRONT);
                bot.outtakeWrist.setTarget(mOuttakeWrist.INTAKE_SPEC_FRONT);
                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
                bot.linkage.setTarget(mLinkage.REST);
                bot.turret.setTarget(mTurret.TRANSFER);
                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
                break;

            case TRANSFER:
                bot.outtakeSlides.setTarget(mOuttakeSlides.REST);
                bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
                bot.outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
                bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
                bot.linkage.setTarget(mLinkage.TRANSFER);
                bot.turret.setTarget(mTurret.TRANSFER);
                bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
                bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
                break;

        }
    }
    boolean isOuttakeClawOpen = false;
    boolean isIntakeClawOpen = false;
    boolean rightWasPressed = false;
    boolean leftWasPressed = false;
    private void manual2(){

        double joystickL = gamepad2.left_stick_y;
        if(Math.abs(joystickL) > 0.05) {
            bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + (100 / 2) * (Math.signum(-joystickL) * (Math.pow(2, Math.abs(-joystickL) * 2)) - 1), 0, mOuttakeSlides.MAX));
        }
        //        if(gamepad2.right_stick_y > 0.3){
//            mechanismStates = MechanismStates.MANUAL_OUT_SLIDES;
//            bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() - MANUAL_OUTTAKE_SLIDES_INCREMENT, 0, mOuttakeSlides.MAX));
//        } else if(gamepad2.right_stick_y < -0.3){
//            mechanismStates = MechanismStates.MANUAL_OUT_SLIDES;
//            bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT, 0, mOuttakeSlides.MAX));
//        }

        if(Math.abs(gamepad2.right_stick_y) > 0.3 ){
            mechanismStates = MechanismStates.MANUAL_IN_SLIDES;
            bot.linkage.setTarget(Range.clip(-gamepad2.right_stick_y, 0.05,0.4));
        }

        if(gamepad2.right_bumper && !rightWasPressed){
            isIntakeClawOpen = !isIntakeClawOpen;
            rightWasPressed = true;
        } else if(!gamepad2.right_bumper){
            rightWasPressed = false;
        }
        if(isIntakeClawOpen){
            bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
        } else {
            bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
        }

        if(gamepad2.left_bumper && !leftWasPressed){

            isOuttakeClawOpen = !isOuttakeClawOpen;
            leftWasPressed = true;
        } else if(!gamepad2.left_bumper){
            leftWasPressed = false;
        }
        if(isOuttakeClawOpen){
            bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
        } else {
            bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
        }
        telemetry.addLine("OUTST: " + isOuttakeClawOpen + " INTST: " + isIntakeClawOpen);

        if(gamepad2.right_trigger > 0.5){
            bot.turret.setTarget(mTurret.DIAGONAL_RIGHT);
        }else if(gamepad2.left_trigger > 0.5){
            bot.turret.setTarget(mTurret.DIAGONAL_LEFT);
        }else if(gamepad2.right_trigger < 0.5 && gamepad2.left_trigger < 0.5){
            bot.turret.setTarget(mTurret.INIT);
        }
    }
}