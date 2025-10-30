package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

@Disabled
@TeleOp
public class Qual1Teleop extends LinearOpMode{

    private Bot bot;
    private Setup setup;
    private ElapsedTime timer;

    private double gamepadX;
    private double gamepadY;
    private double y;
    private double x;
    private double angle;
    private double botAngle = 0;
    private double translateMag;
    private double spin;
    private String[] colors;

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private IMU imu;
    private double botHeading;

    @Override
    public void runOpMode() throws InterruptedException {

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);

        frontLeftMotor = hardwareMap.dcMotor.get("rightFront");
        backLeftMotor = hardwareMap.dcMotor.get("leftRear");
        frontRightMotor = hardwareMap.dcMotor.get("leftFront");
        backRightMotor = hardwareMap.dcMotor.get("rightRear");

        //This may need to swap. If bot goes backwards, try swapping
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        bot.init();


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            driver1();
            driver2();
            bot.update();

        }

    }



    private void driver1() {

        gamepadX = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        gamepadY = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        translateMag = Math.sqrt(gamepadX*gamepadX + gamepadY*gamepadY);
        angle = Math.atan2(gamepadY, gamepadX);
        botAngle += angle;

        x = Math.cos(angle) * translateMag;
        y = Math.sin(angle) * translateMag;

        if (gamepad1.left_bumper) {  // Slowmode
            x *= 0.25;
            y *= 0.25;
            spin *= 0.25;
        } else {  // ADJUST THESE VALUES AS NEEDED
            x *= 1;
            y *= 1;
            spin *= 1;  // WAS 0.7
        }

        bot.drivetrain.setTeleOpTargets(x, y, spin);

        telemetry.addData("translateMag", translateMag);
        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("spin", spin);
        telemetry.addData("angle", angle);

//        if (gamepad1.options) {
//            imu.resetYaw();
//        }
//
//        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
//        // Rotate the movement direction counter to the bot's rotation
//        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//
//        rotX = rotX * 1.1;  // Counteract imperfect strafing
//
//        // Denominator is the largest motor power (absolute value) or 1
//        // This ensures all the powers maintain the same ratio,
//        // but only if at least one is out of the range [-1, 1]
//        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
//        double frontLeftPower = (rotY + rotX + rx) / denominator;
//        double backLeftPower = (rotY - rotX + rx) / denominator;
//        double frontRightPower = (rotY - rotX - rx) / denominator;
//        double backRightPower = (rotY + rotX - rx) / denominator;
//
//        frontLeftMotor.setPower(frontLeftPower);
//        backLeftMotor.setPower(backLeftPower);
//        frontRightMotor.setPower(frontRightPower);
//        backRightMotor.setPower(backRightPower);

    }

    private void driver2() {

        //colors need to be defined
        if(gamepad2.a){
            outtakePurple(colors);
        }
        if(gamepad2.b){
            outtakeGreen(colors);
        }
        if(gamepad2.x){
            intakeEmpty(colors);
        }
        if(gamepad2.y) {
            bot.transfer.setTarget(mTransfer.TRANSFER);
        } else {
            bot.transfer.setTarget(mTransfer.REST);
        }

        if(gamepad2.right_bumper) {
            //outtake
            bot.outtake.setVelocity(mOuttake.SLOW);
        } else if(gamepad2.right_trigger>0.5){
            bot.outtake.setVelocity(mOuttake.FAST);
        } else {
            bot.outtake.setVelocity(mOuttake.REST);
        }

        if(gamepad2.left_bumper) {
            bot.intake.setVelocity(mIntake.SLOW);
        } else if(gamepad2.right_trigger > 0.5){
            bot.intake.setVelocity(mIntake.FAST);
        } else {
            bot.intake.setVelocity(mIntake.REST);
        }

    }

    public boolean outtakePurple(String[] colors) {
        boolean found = false;
        if (colors[0] == "purple") {
            bot.carousel.setTarget(mCarousel.OUTTAKE1);
            found = true;
        } else if (colors[1] == "purple") {
            bot.carousel.setTarget(mCarousel.OUTTAKE2);
            found = true;
        } else if (colors[2] == "purple") {
            bot.carousel.setTarget(mCarousel.OUTTAKE3);
            found = true;
        }

        return found;
    }

    public boolean outtakeGreen(String[] colors) {
        boolean found = false;
        if (colors[0] == "green") {
            bot.carousel.setTarget(mCarousel.OUTTAKE1);
            found = true;
        } else if (colors[1] == "green") {
            bot.carousel.setTarget(mCarousel.OUTTAKE2);
            found = true;
        } else if (colors[2] == "green") {
            bot.carousel.setTarget(mCarousel.OUTTAKE3);
            found = true;
        }

        return found;
    }

    public boolean intakeEmpty(String[] colors) {
        boolean found = false;
        if (colors[0] == "null") {
            bot.carousel.setTarget(mCarousel.INTAKE1);
            found = true;
        } else if (colors[1] == "null") {
            bot.carousel.setTarget(mCarousel.INTAKE2);
            found = true;
        } else if (colors[2] == "null") {
            bot.carousel.setTarget(mCarousel.INTAKE3);
            found = true;
        }

        return found;
    }
}
