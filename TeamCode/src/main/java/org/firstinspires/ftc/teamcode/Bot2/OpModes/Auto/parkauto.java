package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "parkauto", group = "1")
public class parkauto extends LinearOpMode {
    DcMotor rightRear, leftRear, rightFront, leftFront;
    double rr = 0.7,lr=-0.7, rf=-0.7,lf=0.7;
    ElapsedTime timer;
    @Override
    public void runOpMode(){
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE); //todo
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        timer = new ElapsedTime();

        waitForStart();
        timer.reset();
        while(opModeIsActive()){
            if(timer.seconds() > 5) {
                rightRear.setPower(rr);
                leftRear.setPower(lr);
                rightFront.setPower(rf);
                leftFront.setPower(lf);
            }

        }

    }
}
