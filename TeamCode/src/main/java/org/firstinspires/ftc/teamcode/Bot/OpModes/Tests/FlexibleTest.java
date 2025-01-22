package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Grippers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;

@TeleOp(name="FlexibleTest", group="teleop")

public class FlexibleTest extends LinearOpMode {
    Setup setup2;
    Mechanism mechanism;
    //    MechTest mechTest;
//    MechTest outtakeRotation = new OuttakeRotation();
    Mechanism slides = new SlidesSmart(), wrist = new Wrist(), arm = new Arm(), fingers = new Fingers(), grippers = new Grippers();
    double targetSlides = 0,targetWrist = 0.5, targetArm = 0.5, targetFingers = 0.5, targetWheels = 1;
    double SERVO_INCREMENT = 0.0002, MOTOR_INCREMENT = 5;
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
            //ARM
            if (gamepad1.a){
                grippers.reverse(true, false);
                grippers.init(targetWheels);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetWheels = 1;
                } else if(Math.abs(gamepad1.left_stick_y) < -0.05){
                    targetWheels = -1;
                }
                grippers.setTarget(targetWheels);
                grippers.update();
            }
            if (gamepad1.x){
                arm.init(targetArm);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetArm += gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetArm > 1){
                        targetArm = 1;
                    }
                    else if(targetArm < 0){
                        targetArm = 0;
                    }
                }else if(Math.abs(gamepad1.left_stick_y) < -0.05){
                    targetArm -= gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetArm > 1){
                        targetArm = 1;
                    }
                    else if(targetArm < 0){
                        targetArm = 0;
                    }
                }
                if(targetArm>1){
                    targetArm=1;
                }
                arm.setTarget(targetArm);
                arm.update();
            }
            if (gamepad1.b){
                wrist.init(targetWrist);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetWrist += gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetWrist > 1){
                        targetWrist = 1;
                    }
                    else if(targetWrist < 0){
                        targetWrist = 0;
                    }
                }else if(Math.abs(gamepad1.left_stick_y) < -0.05){
                    targetWrist -= gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetWrist > 1){
                        targetWrist = 1;
                    }
                    else if(targetWrist < 0){
                        targetWrist = 0;
                    }
                }
                wrist.setTarget(targetWrist);
                wrist.update();
            }

            if (gamepad1.y){
                fingers.init(targetFingers);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetFingers += gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetFingers > 1){
                        targetFingers = 1;
                    }
                    else if(targetFingers < 0){
                        targetFingers = 0;
                    }
                }else if(Math.abs(gamepad1.left_stick_y) < -0.05){
                    targetFingers -= gamepad1.left_stick_y*SERVO_INCREMENT;
                    if(targetFingers > 1){
                        targetFingers = 1;
                    }
                    else if(targetFingers < 0){
                        targetFingers = 0;
                    }
                }
                fingers.setTarget(targetFingers);
                fingers.update();
            }

            if (gamepad2.dpad_down){
                slides.init(targetSlides);
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    targetSlides += gamepad1.left_stick_y*MOTOR_INCREMENT;
                }else if(Math.abs(gamepad1.left_stick_y) < -0.05){
                    targetSlides -= gamepad1.left_stick_y*MOTOR_INCREMENT;
                }
                slides.setTarget(targetSlides);
                slides.update();
            }

            telemetry.addData("wheel pos (A)", targetWheels);
            telemetry.addData("wrist pos (B)", targetWrist);
            telemetry.addData("arm pos (X)", targetArm);
            telemetry.addData("fingers pos (Y)", targetFingers);
            telemetry.addData("slides pos (DPad Down)", targetSlides);
            telemetry.addLine("gamepad1.left_stick_y increase/decrease target position");
            telemetry.update();
        }
    }

}
