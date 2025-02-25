//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeClaw;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeRotation;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeSlides;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeWrist;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.Mechanism;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeV4B;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.SlidesSmart;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeWrist;
//import org.firstinspires.ftc.teamcode.Bot2.OuttakeClaw;
//import org.firstinspires.ftc.teamcode.Bot2.OuttakeSlides;
//import org.firstinspires.ftc.teamcode.Bot2.Setup;
//
//@Disabled
//@TeleOp(name="*FlexibleTest", group="Test")
//
//public class FlexibleTest extends LinearOpMode {
//    Setup setup;
//    Mechanism mechanism;
//    //    MechTest mechTest;
////    MechTest outtakeRotation = new OuttakeRotation();
//    Mechanism
//            outtakeV4B = new OuttakeV4B(),
//            outtakeWrist = new OuttakeWrist(),
//            outtakeClaw = new OuttakeClaw(),
//            outtakeSlides = new OuttakeSlides(),
//            intakeSlides = new IntakeSlides(),
//            intakeRot = new IntakeRotation(),
//            intakeWrist = new IntakeWrist(),
//            intakeClaw = new IntakeClaw();
//    boolean mechIsServo;
//    double targetPos, futureVelPos, changeInPos, velocity;
//    double SERVO_INCREMENT = 0.001, MOTOR_INCREMENT = 5;
//    boolean isGetVelocityMode, isMoving;
//    ElapsedTime timer = new ElapsedTime();
//    @Override
//    public void runOpMode() throws InterruptedException {
//        setup = new Setup(hardwareMap, telemetry, false, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        mechanism = new Mechanism("mechanism");
//        telemetry.update();
//        waitForStart();
//        resetRuntime();
//        while(opModeIsActive()){
//            if (gamepad1.y){
//                mechanism = outtakeV4B;
//                targetPos = 0.5;
//                futureVelPos = targetPos;
//                mechanism.init(targetPos);
//                mechIsServo = true;
//            }
//            //stack 5: 0.2609, stack 4: 0.2899
//            if (gamepad1.x){
//                mechanism = outtakeWrist;
//                targetPos = OuttakeWrist.INIT;
//                futureVelPos = targetPos;
//                mechanism.init(targetPos);
//                mechIsServo = true;
//            }
//
//
//            if (gamepad1.a){
//                outtakeSlides = new SlidesSmart();
//                mechanism = outtakeSlides;
//                targetPos = 0;
//                futureVelPos = targetPos;
//                mechanism.init(targetPos);
//                mechIsServo = false;
//            }
//
//            if (gamepad2.dpad_down){
//                isGetVelocityMode = true;
//            }else if (gamepad2.dpad_up){
//                isGetVelocityMode = false;
//            }
//            double gamepad_input = -gamepad1.left_stick_y;
//            if (isGetVelocityMode && mechIsServo){
//                if (gamepad_input > 0.1) {
//                    futureVelPos = Range.clip(futureVelPos + 0.1*SERVO_INCREMENT, 0, 1);
//                    isMoving = false;
//                } else if (gamepad_input < -0.1) {
//                    futureVelPos = Range.clip(futureVelPos - 0.1*SERVO_INCREMENT, 0, 1);
//                    isMoving = false;
//                }
//                if (gamepad2.a && !isMoving){
//                    changeInPos = Math.abs(targetPos - futureVelPos);
//                    targetPos = futureVelPos;
//                    timer.reset();
//                    isMoving = true;
//                }
//                if (gamepad2.b && isMoving){
//                    velocity = changeInPos/timer.seconds();
//                }
//            }else {
//                if (gamepad_input > 0.1) {
//                    if (mechIsServo) {
//                        targetPos = Range.clip(targetPos + SERVO_INCREMENT, 0, 1);
//                    } else {
//                        targetPos = Range.clip(targetPos + MOTOR_INCREMENT, -10000, 10000);
//                    }
//                } else if (gamepad_input < -0.1) {
//                    if (mechIsServo) {
//                        targetPos = Range.clip(targetPos - SERVO_INCREMENT, 0, 1);
//                    } else {
//                        targetPos = Range.clip(targetPos - MOTOR_INCREMENT, -10000, 10000);
//                    }
//                }
//            }
//
//            mechanism.setTarget(targetPos);
//            mechanism.update();
//            mechanism.telemetry();
//            telemetry.addData("Bot2FlexibleTest targetPos", targetPos);
//            telemetry.addData("Bot2FlexibleTest futureVelPos", futureVelPos);
//            telemetry.addData("Bot2FlexibleTest velocity", velocity);
//            telemetry.addLine("gamepad1.a slides \ngamepad1.b fingers\ngamepad1.x wrist\ngamepad1.y arm");
//            telemetry.addLine("gamepad1.left_stick_y increase/decrease target position");
//            telemetry.addLine("gamepad1.right_stick_y > 0 activates target position");
//            telemetry.update();
//
//        }
//    }
//
//}
