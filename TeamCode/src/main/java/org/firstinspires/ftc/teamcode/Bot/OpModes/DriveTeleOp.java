package org.firstinspires.ftc.teamcode.Bot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Old.Setup1;
import org.firstinspires.ftc.teamcode.Bot.Old.Bot1;

//import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
@TeleOp(name = "AAAAAAAAAAAAA", group = "1")
public class DriveTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Setup1 setup;
    Bot1 bot;
    IMUStatic imu;

    @Override
    public void runOpMode(){
        setup = new Setup1(hardwareMap, telemetry, true, this, Setup1.OpModeType.TELEOP, Setup1.Team.Q1);
        bot = new Bot1(Setup1.mechStates, Setup1.sensorStates);
        imu = new IMUStatic();
        if(bot !=null){
//            bot.initDrivetrain(new Pose(0,0,0));
            telemetry.addData("Status", "Initialized");
            telemetry.addLine("BOT IS NOT NULLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
            telemetry.update();

            waitForStart();

            while (opModeIsActive()) {
                drive();
//                driveUsingPeP();
                bot.drivetrain.update(true);
                bot.drivetrain.telemetry();
                if(gamepad1.right_bumper){
                    imu.resetYaw();
                }
            }
        } else {
            while (opModeIsActive()) {
                telemetry.addLine("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                telemetry.update();
            }
        }


    }
    double x, y, spin;
    void drive(){
        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;

        bot.setTeleOpTargets(x, y, spin);
    }
    void driveUsingPeP(){
        bot.setTeleOpTargets(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }

}
