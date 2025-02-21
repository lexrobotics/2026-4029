package org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1;

public abstract class MotorMechanism extends Mechanism{
    protected DcMotorEx motor;
    protected double targetPower;
    protected double currentPower;

    public MotorMechanism(String name) {
        super(name);
        velocity = 1;
    }
    @Override
    public void init(double target, DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        motor = Setup1.hardwareMap.get(DcMotorEx.class, name);
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }
    @Override
    public void reverse(boolean isReversed){
        if(isReversed){
            motor.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }

    @Override
    public void reset(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void update(){
        targetPower = targetPos;
        currentPower = motor.getPower();
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(targetPower);
    }

    @Override
    public boolean isBusy(){
        return targetPower != 0;
    }

    @Override
    public void telemetry(){
        Setup1.telemetry.addData(name + "currentPower", currentPower);
        Setup1.telemetry.addData(name + "targetPower", targetPower);
        Setup1.telemetry.addData(name + " isBusy", isBusy());
    }
}
