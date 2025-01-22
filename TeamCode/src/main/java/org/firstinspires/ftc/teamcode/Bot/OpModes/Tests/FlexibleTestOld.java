package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.StartPositions;

@TeleOp(name = "flexible test", group = "tele-op")
public class FlexibleTestOld extends LinearOpMode {
    Setup setup;

    Mechanism mechanism;
    Mechanism slides = new SlidesSmart();

    Mechanism[] mechanisms;
    boolean[] isServo;
    double[] initPositions;
    private int mechIndex = 0;
    private boolean wasPressed = false;

    private double targetPos, futureVelPos, changeInPos, velocity;
    private boolean isGetVelocityMode, isMoving;

    private double SERVO_INCREMENT = 0.0001, MOTOR_INCREMENT = 3;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, false, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        mechanism = new Mechanism("mechanism");
        mechanisms = new Mechanism[]{
                slides
        };
        initPositions = new double[]{
                0
        };
        isServo = new boolean[]{
                false,

        };

        telemetry.update();

        waitForStart();
        resetRuntime();
        while (opModeIsActive()){
            mechanism = mechanisms[mechIndex % mechanisms.length];

            if(gamepad1.dpad_right && !wasPressed){
                mechIndex++;
                wasPressed = true;
            } else if(!gamepad1.dpad_right){
                wasPressed = false;
            }
            if(gamepad1.a){
                if(mechanism.wasInitialized()) {
                    targetPos = mechanism.getCurrentPosition();
                    futureVelPos = targetPos;
                    mechanism.init(targetPos);
                    mechanism.update();
                }else {
                    targetPos = initPositions[mechIndex % mechanisms.length];
                    futureVelPos = targetPos;
                    mechanism.init(targetPos);
                    mechanism.update();
                }
            }

            if (gamepad2.dpad_down){
                isGetVelocityMode = true;
            }else if (gamepad2.dpad_up){
                isGetVelocityMode = false;
            }

            double gamepad_input = -gamepad1.left_stick_y;
            if(mechIndex==6){
                break;
            }
            else{
                if (isGetVelocityMode && isServo[mechIndex % mechanisms.length]) {
                    if (gamepad_input > 0.1) {
                        futureVelPos = Range.clip(futureVelPos + 0.1 * SERVO_INCREMENT, 0, 1);
                        isMoving = false;
                        mechanism.update();
                    } else if (gamepad_input < -0.1) {
                        futureVelPos = Range.clip(futureVelPos - 0.1 * SERVO_INCREMENT, 0, 1);
                        isMoving = false;
                        mechanism.update();
                    }
                    if (gamepad2.a && !isMoving) {
                        changeInPos = Math.abs(targetPos - futureVelPos);
                        targetPos = futureVelPos;
                        timer.reset();
                        isMoving = true;
                        mechanism.update();
                    }
                    if (gamepad2.b && isMoving) {
                        velocity = changeInPos / timer.seconds();
                        mechanism.update();
                    }
                } else {
                    if (gamepad_input > 0.1) {
                        if (isServo[mechIndex % mechanisms.length]) {
                            targetPos = Range.clip(targetPos + SERVO_INCREMENT, 0, 1);
                        } else {
                            targetPos = Range.clip(targetPos + MOTOR_INCREMENT, -10000, 10000);
                        }
                        mechanism.update();
                    } else if (gamepad_input < -0.1) {
                        if (isServo[mechIndex % mechanisms.length]) {
                            targetPos = Range.clip(targetPos - SERVO_INCREMENT, 0, 1);
                        } else {
                            targetPos = Range.clip(targetPos - MOTOR_INCREMENT, -10000, 10000);
                        }
                        mechanism.update();
                    }
                }
            }

                if(mechanism.wasInitialized()){
                    mechanism.update();
                }
            mechanism.setTarget(targetPos);
            mechanism.telemetry();

            telemetry.addData("instructions", "dpad right to go to next mechanism, a to initialize mechanism, y to move mechanism");
            telemetry.addData("targetPos", targetPos);
            telemetry.addData("futureVelPos", futureVelPos);
            telemetry.addData("velocity", velocity);
            telemetry.addData("currentMechanism", mechanism.toString());
            telemetry.addData("nextMechanism", mechanisms[(mechIndex+1) % mechanisms.length]);
            telemetry.update();
        }
    }
}
