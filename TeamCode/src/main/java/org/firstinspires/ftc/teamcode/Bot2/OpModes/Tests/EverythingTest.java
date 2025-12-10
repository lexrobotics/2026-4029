package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

// com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class EverythingTest extends LinearOpMode{
    // Carousel
    private Servo carousel;

    // Outtake
    DcMotor outtakeRight;
    DcMotor outtakeLeft;
    private int targetPositionOuttake = 50;
    private double velocityOuttake = 0.5;


    // Intake
    DcMotor intake;
    private int targetPositionIntake = 25;
    private double velocityIntake = 0.5;
    boolean intake_running = false;

    // Transfer (Gate)
    private Servo transfer;
    boolean gate_open = false;

    // Other
    private ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        // Carousel
        carousel = hardwareMap.get(Servo.class, "Carousel");

        // Setting the servo // O is for outtake and I is for intake
        //Positions
        double pos_O_1 = 0.7460;
        double pos_O_2 = 0.029;
        double pos_O_3 = 0.381;
        double pos_I_1 = 0.23;
        double pos_I_2 = 0.589;
        double pos_I_3 = 0.969;

        //Color Position // 0 = Green, 1 = Purple, -1 = There is nothing in that slot
        // Predefined extra points combo until I code the color sensor code
        int[] point_colors = {1, 0, 0};
        int[] slot_colors = {0, 0, 1};

        carousel.setPosition(pos_O_1);
        telemetry.addLine("Position set!");
        telemetry.update();

        // Outtake
        outtakeRight = hardwareMap.get(DcMotor.class, "OuttakeRight");
        outtakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        outtakeLeft = hardwareMap.get(DcMotor.class, "OuttakeLeft");
        outtakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeLeft.setDirection(DcMotorSimple.Direction.REVERSE); // Might have to switch it to the other motor


        // Intake
        intake = hardwareMap.get(DcMotor.class, "Intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        // Transfer (Gate)
        transfer = hardwareMap.get(Servo.class, "Transfer");
        transfer.setPosition(0.0); // Set to closed position

        telemetry.addLine("Ready!");
        telemetry.update();

        // Other
        timer = new ElapsedTime();

        waitForStart();

        while(opModeIsActive()) {
            // Carousel
            if(gamepad1.a){ // When a is pressed, do outtake
                //outtake mode 1 (if all the colors needed for the bonus are present)
                if ((slot_colors[0] + slot_colors[1] + slot_colors[2]) == (point_colors[0] + point_colors[1] + point_colors[2])) {
                    for (int i = 0; i < 3; i++) { // i < 3
                        for (int j = 0; j < 3; j++) { // j < 3
                            if (slot_colors[j] == point_colors[i]) {
                                if(j == 0){
                                    carousel.setPosition(pos_O_1);
                                } else if(j == 1){
                                    carousel.setPosition(pos_O_2);
                                } else if(j == 2){
                                    carousel.setPosition(pos_O_3);
                                }
                                slot_colors[j] = -1;
                                // telemetry.addLine("i = " + i);
                                //telemetry.addLine("j = " + j);
                                // Outake here
                                // telemetry.addLine("Outake!");
                                telemetry.addLine("Position " + i + "set!");
                                telemetry.addLine("");
                                sleep(1000);
                                break;
                            }
                        }
                    }
                    telemetry.update();
                } else { //outtake mode 2 (if all the colors needed for the bonus are not present - launch as fast as possible)
                    for(int i = 0; i < 3; i++) {
                        if (i == 0) {
                            carousel.setPosition(pos_O_1);
                        } else if (i == 1) {
                            carousel.setPosition(pos_O_2);
                        } else if (i == 2) {
                            carousel.setPosition(pos_O_3);
                        }
                        slot_colors[i] = -1;
                        //telemetry.addLine("a = " + a);
                        // Outake here
                        //telemetry.addLine("Outake!");
                        //telemetry.addLine("Position " + i + "set!");
                        //telemetry.addLine("");
                        sleep(1000);
                    }
                }
                if(gamepad1.b){
                    for(int i = 0; i < 3; i++) {
                        if (i == 0) {
                            carousel.setPosition(pos_I_1);
                        } else if (i == 1) {
                            carousel.setPosition(pos_I_2);
                        } else if (i == 2) {
                            carousel.setPosition(pos_I_3);
                        }
                        slot_colors[i] = -1;
                        //telemetry.addLine("i = " + i);
                        // Intake here
                        //telemetry.addLine("Intake!");
                        //telemetry.addLine("Position " + i + "set!");
                        //telemetry.addLine("");
                        sleep(1000);
                    }
                    //telemetry.update();
                }
            }

            // Outtake
            if (gamepad1.dpad_up) {
                //velocity = Range.clip(velocity + 0.025, -1, 1);
                velocityOuttake += 0.01;
                sleep(500);
            }
            if (gamepad1.dpad_down) {
                //velocity = velocity - 0.025;
                velocityOuttake -= 0.01;
                sleep(500);
            }

            if(gamepad1.right_bumper) {
                //outtakeRight.setTargetPosition(targetPositionOuttake);
                //outtakeRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtakeRight.setPower(Range.clip(velocityOuttake, -1, 1));
            } else{
                outtakeRight.setPower(0);
            }

            if (gamepad1.right_bumper) {
                //outtakeLeft.setTargetPosition(targetPositionOuttake);
                //outtakeLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtakeLeft.setPower(Range.clip(velocityOuttake, -1, 1));
            } else{
                outtakeLeft.setPower(0);
            }

            telemetry.addLine("");
            telemetry.addData("Target position outtake: ", targetPositionOuttake);
            telemetry.addData("Velocity outtake: ", velocityOuttake);

            // Intake
            if (gamepad1.dpad_right) {
                velocityIntake += 0.01;
                sleep(500);
            }
            if (gamepad1.dpad_left) {
                velocityIntake -= 0.01;
                sleep(500);
            }

            if (gamepad1.x) {
                timer.reset();
                intake_running = true;
            }

            if(intake_running && timer.seconds() < 3){
                //intake.setTargetPosition(targetPositionIntake);
                //intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                intake.setPower(Range.clip(velocityIntake, -1, 1));
            } else {
                intake.setPower(0);
                intake_running = false;
                timer.reset();
            }

            telemetry.addLine("");
            telemetry.addData("Target position intake: ", targetPositionIntake);
            telemetry.addData("Velocity intake: ", velocityIntake);

            // Transfer (Gate)
            // Postions to be found
            if(gamepad1.y) {
                if (gate_open == false) {
                    transfer.setPosition(0.2); // Set to open position
                    gate_open = true;
                } else {
                    transfer.setPosition(0.0); // Set to closed position
                    gate_open = false;
                }
            }

            // Other
            telemetry.update();
        }
    }
}
