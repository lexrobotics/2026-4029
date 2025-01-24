package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Grippers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;


import java.util.HashMap;

@TeleOp(name="*FlexibleTest", group="Test")

public class FlexibleTest extends LinearOpMode {
    Setup setup;
    Mechanism mechanism;
    //    MechTest mechTest;
//    MechTest outtakeRotation = new OuttakeRotation();
    Mechanism arm = new Arm(), wrist = new Wrist(),
            slides = new SlidesSmart(), fingers = new Fingers(),
            grippers = new Grippers();
    boolean mechIsServo;
    double targetPos, futureVelPos, changeInPos, velocity;
    double SERVO_INCREMENT = 0.001, MOTOR_INCREMENT = 5;
    boolean isGetVelocityMode, isMoving;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        setup =new Setup(hardwareMap, telemetry, false, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        mechanism = new Mechanism("mechanism");
        telemetry.update();
        waitForStart();
        resetRuntime();
        while(opModeIsActive()){

            if (gamepad1.y){
                mechanism = arm;
                targetPos = 0.5;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }
            //stack 5: 0.2609, stack 4: 0.2899
            if (gamepad1.x){
                mechanism = wrist;
                targetPos = 0.5;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }
            if (gamepad1.b){
                mechanism = fingers;
                targetPos = 0.5;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }
            if (gamepad2.dpad_up){
                mechanism = grippers;
                targetPos = 0;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }


            if (gamepad1.a){
                slides = new SlidesSmart();
                mechanism = slides;
                targetPos = 0;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = false;
            }

            if (gamepad2.dpad_down){
                isGetVelocityMode = true;
            }else if (gamepad2.dpad_up){
                isGetVelocityMode = false;
            }
            double gamepad_input = -gamepad1.left_stick_y;
            if (isGetVelocityMode && mechIsServo){
                if (gamepad_input > 0.1) {
                    futureVelPos = Range.clip(futureVelPos + 0.1*SERVO_INCREMENT, 0, 1);
                    isMoving = false;
                } else if (gamepad_input < -0.1) {
                    futureVelPos = Range.clip(futureVelPos - 0.1*SERVO_INCREMENT, 0, 1);
                    isMoving = false;
                }
                if (gamepad2.a && !isMoving){
                    changeInPos = Math.abs(targetPos - futureVelPos);
                    targetPos = futureVelPos;
                    timer.reset();
                    isMoving = true;
                }
                if (gamepad2.b && isMoving){
                    velocity = changeInPos/timer.seconds();
                }
            }else {
                if (gamepad_input > 0.1) {
                    if (mechIsServo) {
                        targetPos = Range.clip(targetPos + SERVO_INCREMENT, 0, 1);
                    } else {
                        targetPos = Range.clip(targetPos + MOTOR_INCREMENT, -10000, 10000);
                    }
                } else if (gamepad_input < -0.1) {
                    if (mechIsServo) {
                        targetPos = Range.clip(targetPos - SERVO_INCREMENT, 0, 1);
                    } else {
                        targetPos = Range.clip(targetPos - MOTOR_INCREMENT, -10000, 10000);
                    }
                }
            }

            mechanism.setTarget(targetPos);
            mechanism.update();
            mechanism.telemetry();
            telemetry.addData("Bot2FlexibleTest targetPos", targetPos);
            telemetry.addData("Bot2FlexibleTest futureVelPos", futureVelPos);
            telemetry.addData("Bot2FlexibleTest velocity", velocity);
            telemetry.addLine("gamepad1.a slides \ngamepad1.b fingers\ngamepad1.x wrist\ngamepad1.y arm");
            telemetry.addLine("gamepad1.left_stick_y increase/decrease target position");
            telemetry.addLine("gamepad1.right_stick_y > 0 activates target position");
            telemetry.update();

        }
    }

}
