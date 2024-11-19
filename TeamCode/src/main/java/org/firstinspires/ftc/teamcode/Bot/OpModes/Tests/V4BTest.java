package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Setup;
@TeleOp(name="V4B Test (be careful)")
public class V4BTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        setup.disableMechanism("intakeMotor");
        setup.disableMechanism("drivetrain");
        Bot bot = new Bot(Setup.mechStates, Setup.sensorStates);
        IMUStatic imu = new IMUStatic();
        bot.init();
        waitForStart();
        while(opModeIsActive()){
            if(Math.abs(gamepad1.left_stick_y) > 0.2 && gamepad1.right_bumper){
                bot.outtakeWrist.setTarget(((OuttakeWrist.MAX - OuttakeWrist.MIN) / 2.0) + ((OuttakeWrist.MAX - OuttakeWrist.MIN) / 2.0) * -gamepad1.left_stick_y);
            } else if(Math.abs(gamepad1.left_stick_y) > 0.2){
               bot.outtakeWrist.setTarget(bot.outtakeWrist.targetPos + (Math.signum(-gamepad1.left_stick_y) * 0.0005));
            }
            if(Math.abs(gamepad1.right_stick_y) > 0.2 && gamepad1.right_bumper){
                bot.v4b.setTarget(((V4B.MAX - OuttakeWrist.MIN) / 2.0) + ((V4B.MAX - OuttakeWrist.MIN) / 2.0) * -gamepad1.right_stick_y);
            } else if(Math.abs(gamepad1.right_stick_y) > 0.2){
                bot.v4b.setTarget(bot.v4b.targetPos + (Math.signum(-gamepad1.right_stick_y) * 0.0005));
            }
            telemetry.addLine("Wrist: " + bot.outtakeWrist.targetPos);
            telemetry.addLine("V4B: " + bot.v4b.targetPos);


            if(!gamepad1.left_bumper){
                bot.update();
                telemetry.update();
            }

        }
    }
}
