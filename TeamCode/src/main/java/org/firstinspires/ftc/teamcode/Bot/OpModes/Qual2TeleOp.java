package org.firstinspires.ftc.teamcode.Bot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;

@TeleOp(name="qual2tele", group = "tele-op")
public class Qual2TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        Bot bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        double imuOffset = 0;
        waitForStart();

        while(opModeIsActive()){
            driverOne(bot, imuOffset);
            driverTwo(bot);
        }
    }
    private void driverOne(Bot bot, double imuOffset){

        if (gamepad1.a){
            imuOffset = bot.drivetrain.getHeadingIMU();
        }

        double x = Math.abs(gamepad1.left_stick_x) > 0.04 ? gamepad1.left_stick_x : 0;
        double y = Math.abs(gamepad1.left_stick_y) > 0.04 ? -gamepad1.left_stick_y : 0;
        double spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;
        double translateMag = Math.sqrt(x * x + y * y);
        double angle = Math.atan2(y, x);

        angle += (-bot.drivetrain.getHeadingIMU() + imuOffset);


        x = Math.cos(angle) * translateMag;
        y = Math.sin(angle) * translateMag;

        if (gamepad1.left_bumper) {
            x *= 0.25;
            y *= 0.25;
            spin *= 0.25;
        } else if(bot.slides.getCurrentPosition()>100){
            x *= 0.7;
            y *= 0.7;
            spin *= 0.4;
        } else {
            x *= .95;
            y *= 1;
            spin *= .7;
        }

        bot.drivetrain.setTeleOpTargets(x, y, spin);
        telemetry.addData("translateMag", translateMag);
        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("spin", spin);
        telemetry.addData("angle", angle);
    }
    private void driverTwo(Bot bot){
        if(gamepad2.b){
            if(gamepad2.a){
                bot.slides.setTarget(Slides.RST);
            } else if(gamepad2.dpad_down){
                bot.slides.setTarget(Slides.RST);
            } else if(gamepad2.x){
                bot.slides.setTarget(Slides.SPC1);
            } else if(gamepad2.y){
                bot.slides.setTarget(Slides.SPC2);
            } else if(gamepad2.dpad_left){
                bot.slides.setTarget(Slides.BUC1);
            } else if(gamepad2.dpad_up){
                bot.slides.setTarget(Slides.BUC2);
            }
        } else {
            if(gamepad2.a){
                bot.slides.setTarget(Slides.RST);
                bot.arm.setTarget(Arm.TRANSFER);
            } else if(gamepad2.dpad_down){
                bot.slides.setTarget(Slides.RST);
                bot.arm.setTarget(Arm.TRANSFER);
            } else if(gamepad2.x){
                bot.slides.setTarget(Slides.SPC1);
                bot.arm.setTarget(Arm.OUT);
            } else if(gamepad2.y){
                bot.slides.setTarget(Slides.SPC2);
                bot.arm.setTarget(Arm.OUT);
            } else if(gamepad2.dpad_left){
                bot.slides.setTarget(Slides.BUC1);
                bot.arm.setTarget(Arm.OUT);
            } else if(gamepad2.dpad_up){
                bot.slides.setTarget(Slides.BUC2);
                bot.arm.setTarget(Arm.OUT);
            }
        }
        driverTwoManual(bot);
    }
    private void driverTwoManual(Bot bot){
        double joystickL = gamepad2.left_stick_y;
        double joystickR = gamepad2.right_stick_y;
        bot.slides.setVelocity(0.5);
        bot.slides.setTarget(Range.clip(bot.slides.getCurrentPosition() + (100/2)*(Math.signum(-joystickR)*(Math.pow(2,Math.abs(-joystickR) * 2)) - 1), 0, Slides.MAX));

        bot.wrist.setTarget(Range.clip(bot.wrist.getCurrentPosition() + (0.01)*(Math.signum(-joystickL)*(Math.pow(2,Math.abs(-joystickL) * 2)) - 1), 0, Wrist.MAX));

    }
}
