package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeMotor;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Setup;

@TeleOp(name = "flexible test", group = "tele-op")
public class FlexibleTest extends LinearOpMode {
    Setup setup;

    Mechanism mechanism;
    Mechanism intakeSlides = new IntakeSlides(), intakeMotor = new IntakeMotor(), outtakeClaw = new OuttakeClaw(), outtakeSlides = new OuttakeSlides(), outtakeWrist = new OuttakeWrist(), v4b = new V4B();


    Mechanism[] mechanisms;
    boolean[] isServo;
    double[] initPositions;
    private int mechIndex = 0;
    private boolean wasPressed = false;

    private double targetPos, futureVelPos, changeInPos, velocity;
    private boolean isGetVelocityMode, isMoving;

    private double SERVO_INCREMENT = 0.001, MOTOR_INCREMENT = 5;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        setup = new Setup(hardwareMap, telemetry, false, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        mechanism = new Mechanism("mechanism");
        mechanisms = new Mechanism[]{outtakeSlides, v4b, outtakeWrist, outtakeClaw};
        initPositions = new double[]{0, 0.5, 0.5, 0.5};
        isServo = new boolean[]{false, true, true, true};

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

                if (isGetVelocityMode && isServo[mechIndex%mechanisms.length]){
                    if (gamepad_input > 0.1) {
                        futureVelPos = Range.clip(futureVelPos + 0.1*SERVO_INCREMENT, 0, 1);
                        isMoving = false;
                        mechanism.update();
                    } else if (gamepad_input < -0.1) {
                        futureVelPos = Range.clip(futureVelPos - 0.1*SERVO_INCREMENT, 0, 1);
                        isMoving = false;
                        mechanism.update();
                    }
                    if (gamepad2.a && !isMoving){
                        changeInPos = Math.abs(targetPos - futureVelPos);
                        targetPos = futureVelPos;
                        timer.reset();
                        isMoving = true;
                        mechanism.update();
                    }
                    if (gamepad2.b && isMoving){
                        velocity = changeInPos/timer.seconds();
                        mechanism.update();
                    }
                }else {
                    if (gamepad_input > 0.1) {
                        if (isServo[mechIndex % mechanisms.length]) {
                            targetPos = Range.clip(targetPos + SERVO_INCREMENT, 0, 1);
                        } else {
                            targetPos = Range.clip(targetPos + MOTOR_INCREMENT, -10000, 10000);
                        }
                        mechanism.update();
                    } else if (gamepad_input < -0.1) {
                        if (isServo[mechIndex%mechanisms.length]) {
                            targetPos = Range.clip(targetPos - SERVO_INCREMENT, 0, 1);
                        } else {
                            targetPos = Range.clip(targetPos - MOTOR_INCREMENT, -10000, 10000);
                        }
                        mechanism.update();
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
