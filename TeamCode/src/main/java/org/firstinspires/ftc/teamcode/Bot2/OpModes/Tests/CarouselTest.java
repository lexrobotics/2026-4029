package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class CarouselTest extends LinearOpMode{
    private Servo centralServo; //Doesn't work why?

    @Override
    public void runOpMode() throws InterruptedException {
        //Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        centralServo = hardwareMap.get(Servo.class, "centralServo");


        // Setting the servo // O is for outtake and I is for intake
        //Positions
        double pos_O_1 = 0.8;
        double pos_O_2 = 0.0;
        double pos_O_3 = 0.5;
        double pos_I_1 = 0.25;
        double pos_I_2 = 0.75;
        double pos_I_3 = 0.0;

        //Color Position // 0 = Green, 1 = Purple, -1 = There is nothing in that slot
        // Predefined extra points combo until I code the color sensor code
        int[] point_colors = {1, 0, 0};
        int[] slot_colors = {0, 0, 1};

        centralServo.setPosition(pos_O_1);
        telemetry.addLine("Position set!");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            // Intake and color detection code here
            if(gamepad1.a){ // When a is pressed, do outtake
                //outtake mode 1 (if all the colors needed for the bonus are present)
                if ((slot_colors[0] + slot_colors[1] + slot_colors[2]) == (point_colors[0] + point_colors[1] + point_colors[2])) {
                    for (int i = 0; i < 3; i++) { // i < 3
                        for (int j = 0; j < 3; j++) { // j < 3
                            if (slot_colors[j] == point_colors[i]) {
                                if(j == 0){
                                    centralServo.setPosition(pos_O_1);
                                } else if(j == 1){
                                    centralServo.setPosition(pos_O_2);
                                } else if(j == 2){
                                    centralServo.setPosition(pos_O_3);
                                }
                                slot_colors[j] = -1;
                                telemetry.addLine("i = " + i);
                                telemetry.addLine("j = " + j);
                                // Outake here
                                telemetry.addLine("Outake!");
                                telemetry.addLine("");
                                sleep(1000);
                                break;
                            }
                        }
                    }
                    telemetry.update();
                } else { //outtake mode 2 (if all the colors needed for the bonus are not present - launch as fast as possible)
                    for(int a = 0; a < 3; a++) {
                        if (a == 0) {
                            centralServo.setPosition(pos_O_1);
                        } else if (a == 1) {
                            centralServo.setPosition(pos_O_2);
                        } else if (a == 2) {
                            centralServo.setPosition(pos_O_3);
                        }
                        slot_colors[a] = -1;
                        telemetry.addLine("i = " + a);
                        // Outake here
                        telemetry.addLine("Outake!");
                        telemetry.addLine("");
                        sleep(1000);
                    }
                }
                /*if(gamepad1.b){
                    if (slot_colors[j] == point_colors[i]) {
                        if(j == 0){
                            centralServo.setPosition(pos_O_1);
                        } else if(j == 1){
                            centralServo.setPosition(pos_O_2);
                        } else if(j == 2){
                            centralServo.setPosition(pos_O_3);
                        }
                        slot_colors[j] = -1;
                        telemetry.addLine("i = " + i);
                        telemetry.addLine("j = " + j);
                        // Outake here
                        telemetry.addLine("Outake!");
                        telemetry.addLine("");
                        sleep(1000);
                        break;
                    }
                }*/
            }
        }
    }
}
