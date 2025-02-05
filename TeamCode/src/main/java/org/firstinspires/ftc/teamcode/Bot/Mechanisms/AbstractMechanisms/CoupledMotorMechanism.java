package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Bot.Old.Setup1;

public abstract class CoupledMotorMechanism extends Mechanism {
    protected DcMotorEx motorLeft, motorRight;
    protected double latestPosition;
    protected double margin = 50;
    public CoupledMotorMechanism(String name) {
        super(name);
        velocity = 1;
    }
    public void init(double target, DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        setTarget(target);
        motorLeft = Setup1.hardwareMap.get(DcMotorEx.class, name + "Left");
        motorRight = Setup1.hardwareMap.get(DcMotorEx.class, name + "Right");
//        motorRight.setDirection(DcMotorEx.Direction.REVERSE);
        motorLeft.setZeroPowerBehavior(zeroPowerBehavior);
        motorRight.setZeroPowerBehavior(zeroPowerBehavior);

        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void reverse(boolean isLeftReversed, boolean isRightReversed){
        if(isLeftReversed){
            motorLeft.setDirection(DcMotorEx.Direction.REVERSE);
        }
        if(isRightReversed){
            motorRight.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }
    @Override
    public void reset(){
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void update() {
//        currentPos = motorRight.getCurrentPosition();
        motorLeft.setTargetPosition((int) targetPos);
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeft.setPower(velocity);
        motorRight.setTargetPosition((int) targetPos);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setPower(velocity);
    }

    public double getCurrent(){
        return motorLeft.getCurrent(CurrentUnit.AMPS) + motorRight.getCurrent(CurrentUnit.AMPS);
    }
}
