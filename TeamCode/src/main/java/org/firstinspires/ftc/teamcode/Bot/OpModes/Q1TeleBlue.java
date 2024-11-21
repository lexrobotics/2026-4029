package org.firstinspires.ftc.teamcode.Bot.OpModes;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.AutoSequences;
import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Sensors.LightStrip;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.States.IntakeStates;
import org.firstinspires.ftc.teamcode.Bot.States.OuttakeStates;

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
    private OuttakeStates outtakeStates;
    private IntakeStates intakeStates;

    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        imu = new IMUStatic();
        LightStrip lights = new LightStrip("lights", RevBlinkinLedDriver.BlinkinPattern.BLUE);
        waitForStart();


    }
    public void updateLights(){

    }
    public void driver1(){
        if (gamepad1.left_trigger > 0.1){
            imu.resetYaw();
        }

        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        translateMag = Math.sqrt(x*x + y*y);
        angle = Math.atan2(y, x);

        angle += (-bot.drivetrain.getHeadingIMU());


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

    public void driver2() {
//        Outtake to Net, Outtake for Specimen, Intake w/o extending, Intake w/ extended Noodler
//        sampleOuttake button = gamepad1.?
//        specimenOuttake button = gamepad1.?
//        unextendedIntake button = gamepad1.?
//        extendedIntake button = gamepad1.?
//
    }
}
