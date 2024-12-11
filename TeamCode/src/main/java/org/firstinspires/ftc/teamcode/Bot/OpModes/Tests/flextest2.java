package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Setup;

import java.util.HashMap;

@TeleOp(name="2FlexibleTest", group="teleop")

public class flextest2 extends LinearOpMode {
    Setup setup2;
    Mechanism mechanism;
    //    MechTest mechTest;
//    MechTest outtakeRotation = new OuttakeRotation();
    Mechanism outtakeSlides = new OuttakeSlides(), outtakeWrist = new OuttakeWrist(), v4b = new V4B(), outtakeClaw = new OuttakeClaw();
    boolean mechIsServo;
    double targetPos, futureVelPos, changeInPos, velocity;
    double SERVO_INCREMENT = 0.001, MOTOR_INCREMENT = 5;
    boolean isGetVelocityMode, isMoving;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        setup2=new Setup(hardwareMap, telemetry, false, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        mechanism = new Mechanism("mechanism");
        telemetry.update();
        waitForStart();
        resetRuntime();
        while(opModeIsActive()){
            if (gamepad1.y){
                mechanism = v4b;
                targetPos = 0.5;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }
            if (gamepad1.x){
                mechanism = outtakeClaw;
                targetPos = 0.5;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = true;
            }

            if (gamepad1.a){
                mechanism = outtakeSlides;
                outtakeSlides.reverse(true);
                outtakeSlides.reset();
                targetPos = 0;
                futureVelPos = targetPos;
                mechanism.init(targetPos);
                mechIsServo = false;
            }

            if (gamepad1.b){
                mechanism = outtakeWrist;
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
                if (gamepad2.right_bumper && !isMoving){
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
            telemetry.addLine("gamepad1.a outtakeLatchLeft \ngamepad1.b outtakeLatchRight\ngamepad1.x outtakeV4B\ngamepad1.y outtakeRotation");
            telemetry.addLine("gamepad1.left_stick_y increase/decrease target position");
            telemetry.addLine("gamepad1.right_stick_y > 0 activates target position");
            telemetry.update();
        }
    }

}
