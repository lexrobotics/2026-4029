package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.INTAKE1;
import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.INTAKE2;
import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.INTAKE3;
import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.OUTTAKE1;
import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.OUTTAKE2;
import static org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel.OUTTAKE3;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

import java.util.Arrays;

@TeleOp
public class Qual1TeleopRevised extends LinearOpMode{

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

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private IMU imu;

    //private double velocity_shift = 0.0;


    private boolean prevDriver2X;
    private boolean prevDriver2B;

    private Integer index = 0;

    private double[] states = { INTAKE3, OUTTAKE2, INTAKE1, OUTTAKE3, INTAKE2, OUTTAKE1 };

    // Velocity testing
    private double outtake_speed_change = 0.0;
    private boolean prevDriver2O1;
    private boolean prevDriver2O2;


    @Override
    public void runOpMode() throws InterruptedException {

        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);

        frontLeftMotor = hardwareMap.dcMotor.get("rightFront");
        backLeftMotor = hardwareMap.dcMotor.get("leftRear");
        frontRightMotor = hardwareMap.dcMotor.get("leftFront");
        backRightMotor = hardwareMap.dcMotor.get("rightRear");

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

        bot.carousel.setTarget(states[index]);

        while (opModeIsActive()) {

            driver1();
            driver2();
            bot.update();
            telemetry.addData("Color", Arrays.toString(bot.CDSensor.getColor()));
            telemetry.addData("Distance", bot.CDSensor.getDistance(DistanceUnit.CM));

            // 707 131 357 228 (green)  1019 186 512 327
            // 578 196 165 232 (purple) 515  172 147 206
            telemetry.update();
        }

    }



    private void driver1() {

        gamepadX = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        gamepadY = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;//
        translateMag = Math.sqrt(gamepadX*gamepadX + gamepadY*gamepadY);
        angle = Math.atan2(gamepadY, gamepadX);
        botAngle += angle;

        x = Math.cos(angle) * translateMag;
        y = Math.sin(angle) * translateMag;

        if (gamepad1.left_bumper) {  // Slowmode
            x *= 0.25;
            y *= 0.25;
            spin *= -0.25; // Don't know why the spin reversed for some reason, had to set it to - to fix, I think issue is in SampleMecanumDrive
        } else {  // ADJUST THESE VALUES AS NEEDED
            x *= 1;
            y *= 1;
            spin *= -0.7;
        }

        bot.drivetrain.setTeleOpTargets(x, y, spin);

    }

    private void driver2() {
        // This is for velocity testing
        if (gamepad2.dpad_right && !prevDriver2O1){
            outtake_speed_change += 0.005;
            prevDriver2O1 = true;
        } else if (!gamepad2.dpad_right && prevDriver2O1) {
            prevDriver2O1 = false;
        }

        if (gamepad2.dpad_left && !prevDriver2O2){
            outtake_speed_change -= 0.005;
            prevDriver2O2 = true;
        } else if (!gamepad2.dpad_left && prevDriver2O2) {
            prevDriver2O2 = false;
        }
        telemetry.addData("outtake_speed_change", outtake_speed_change);

        bot.carousel.getCurrentPosition();

        if (gamepad2.x && !prevDriver2X) {
            // back
            index = (index - 1 + states.length) % states.length;
            bot.carousel.setTarget(states[index]);
            prevDriver2X = true;
        } else if (!gamepad2.x && prevDriver2X) {
            prevDriver2X = false;
        }


        if (gamepad2.b && !prevDriver2B) {
            // forwards
            index = (index + 1 + states.length) % states.length;
            bot.carousel.setTarget(states[index]);
            prevDriver2B = true;
        } else if (!gamepad2.b && prevDriver2B) {
            prevDriver2B = false;
        }


        if (gamepad2.right_bumper) {
            //outtake
            bot.outtakeLeft.setVelocity(-(mOuttake.SLOW + outtake_speed_change));
            bot.outtakeRight.setVelocity((mOuttake.SLOW + outtake_speed_change));
        } else if (gamepad2.right_trigger > 0.5) {
            bot.outtakeLeft.setVelocity(-(mOuttake.FAST + outtake_speed_change));
            bot.outtakeRight.setVelocity((mOuttake.FAST + outtake_speed_change));
        } else {
            bot.outtakeLeft.setVelocity(-mOuttake.REST);
            bot.outtakeRight.setVelocity(mOuttake.REST);
        }

        if (gamepad2.left_bumper){ // && (bot.intake.getCurrentPosition() == INTAKE2 || bot.intake.getCurrentPosition() == INTAKE3 || bot.intake.getCurrentPosition() == INTAKE1)) {
            bot.intake.setVelocity(mIntake.SLOW);
        } else if (gamepad2.left_trigger > 0.5){ // && (bot.intake.getCurrentPosition() == INTAKE2 || bot.intake.getCurrentPosition() == INTAKE3 || bot.intake.getCurrentPosition() == INTAKE1)) {
            bot.intake.setVelocity(mIntake.FAST);
        } else if (gamepad2.dpad_up) {
            bot.intake.setVelocity(mIntake.EJECT);
        } else {
            bot.intake.setVelocity(mIntake.REST);
        }

        if (gamepad2.y) {
            bot.transfer.setTarget(mTransfer.TRANSFER);
        } else {
            bot.transfer.setTarget(mTransfer.REST);
        }


    }
}
