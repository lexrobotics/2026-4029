package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

@TeleOp(name = "STATES TELEOP SIMPLE", group = "0")
public class TeleOpStatesSimple extends LinearOpMode {
    private Setup setup;
    private Bot bot;
    //    private IMUStatic imu;
    private ElapsedTime timer;
    private ElapsedTime transferTimer;

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

    boolean isOuttakeClawOpen = false;
    boolean isIntakeClawOpen = false;
    boolean rightWasPressed = false;
    boolean leftWasPressed = false;
    enum MechanismStates{
        REST, SCORE_PREP_SPEC, SCORE_PREP_SAMPLE, MANUAL_OUT_SLIDES, MANUAL_IN_SLIDES, EMERGENCY_STOP, INTAKE_EXTEND, TRANSFER, INTAKE_SPEC_FRONT, INTAKE_SPEC_BACK
    }
    MechanismStates mechanismStates = MechanismStates.REST;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        timer = new ElapsedTime();
        transferTimer = new ElapsedTime();

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

//        if (gamepad1.left_bumper) {
//            x *= 0.25;
//            y *= 0.25;
//            spin *= 0.25;
//        } else if(bot.outtakeSlides.getCurrentPosition()>900){
//            x *= 0.7;
//            y *= 0.7;
//            spin *= 0.4;
//        } else {
//            x *= .95;
//            y *= 1;
//            spin *= .7;
//        }

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
            transferTimer.reset();
            mechanismStates = MechanismStates.SCORE_PREP_SAMPLE;

        }else if(gamepad2.x){
            transferTimer.reset();
            mechanismStates = MechanismStates.SCORE_PREP_SPEC;

        }else if (gamepad2.y){
            transferTimer.reset();
            mechanismStates = MechanismStates.SCORE_PREP_SPEC;

        }



        //REST
        if (gamepad2.a) {
            timer.reset();
            mechanismStates = MechanismStates.REST;
        }

        //EXTEND INTAKE SUBMERSIBLE
        if(gamepad2.dpad_down){
            timer.reset();
            mechanismStates = MechanismStates.INTAKE_EXTEND;

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
            timer.reset();
            mechanismStates = MechanismStates.TRANSFER;
        }

        switch (mechanismStates) {
            case REST:
                bot.setRest();
//                bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
                isIntakeClawOpen = false;
                break;
            case SCORE_PREP_SAMPLE:
                if(outtakeClawOpen){

                    isIntakeClawOpen = false;
                }else {
                    if (transferTimer.seconds() > 0.5 && transferTimer.seconds() < 0.9) {

                        isIntakeClawOpen = false;
                    }
                    if (transferTimer.seconds() < 0.9) {
                        isOuttakeClawOpen = true;
                    }
                }
                break;
            case SCORE_PREP_SPEC:
                if(isOuttakeClawOpen){

                    isIntakeClawOpen = false;
                } else {
                    if (transferTimer.seconds() > 0.5 && transferTimer.seconds() < 0.9) {

                        isIntakeClawOpen = false;

                    }
                    if (transferTimer.seconds() < 0.9) {
                        isOuttakeClawOpen = true;
                    }
                }
                break;
            case INTAKE_EXTEND:

                isOuttakeClawOpen = false;
                break;

            case INTAKE_SPEC_BACK:

                break;

            case INTAKE_SPEC_FRONT:

                break;

            case TRANSFER:

                break;

        }
    }

    private void manual2(){

        double joystickL = gamepad2.left_stick_y;
        if(Math.abs(joystickL) > 0.05) {

//            bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + (100 / 2) * (Math.signum(-joystickL) * (Math.pow(2, Math.abs(-joystickL) * 2)) - 1), 0, mOuttakeSlides.MAX));
        }
        //        if(gamepad2.right_stick_y > 0.3){
//            mechanismStates = MechanismStates.MANUAL_OUT_SLIDES;
//            bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() - MANUAL_OUTTAKE_SLIDES_INCREMENT, 0, mOuttakeSlides.MAX));
//        } else if(gamepad2.right_stick_y < -0.3){
//            mechanismStates = MechanismStates.MANUAL_OUT_SLIDES;
//        }

//        if(Math.abs(gamepad2.right_stick_y) > 0.3 ){
//            mechanismStates = MechanismStates.MANUAL_IN_SLIDES;
//            bot.linkage.setTarget(Range.clip(intakeSlidesPosition + ( -gamepad2.right_stick_y * MANUAL_WRIST_INCREMENT), 0, 0.5));
//        }

        if(gamepad2.right_bumper && !rightWasPressed){
            isIntakeClawOpen = !isIntakeClawOpen;
            rightWasPressed = true;
        } else if(!gamepad2.right_bumper){
            rightWasPressed = false;
        }


        if(gamepad2.left_bumper && !leftWasPressed){
            isOuttakeClawOpen = !isOuttakeClawOpen;
            leftWasPressed = true;
        } else if(!gamepad2.left_bumper){
            leftWasPressed = false;
        }

        telemetry.addLine("OUTST: " + isOuttakeClawOpen + " INTST: " + isIntakeClawOpen);

    }
}