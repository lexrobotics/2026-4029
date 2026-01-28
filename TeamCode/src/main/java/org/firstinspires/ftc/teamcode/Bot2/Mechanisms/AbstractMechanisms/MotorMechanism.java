package org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Bot2.Setup;

public abstract class MotorMechanism extends Mechanism {
    protected DcMotorEx motor;

    public MotorMechanism(String name) {
        super(name);
        velocity = 1;
    }

    @Override
    public void init(double target){
        init(target, DcMotor.ZeroPowerBehavior.BRAKE);
    }
    @Override
    public void init(double target, DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        motor = Setup.hardwareMap.get(DcMotorEx.class, name);
        if(motor != null) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        setTarget(target);
    }
    @Override
    public void reverse(boolean isReversed){
        if(isReversed){
            motor.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }

    public double getVelocity(){
        return motor.getVelocity();
    }

    @Override
    public void reset(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void update(){
        if (motor == null) return; //stops the rest of the update to avoid crash

        double actualPower = motor.getPower();

        Setup.telemetry.addData(name + " actualPower", actualPower);
        Setup.telemetry.addData(name + " motorMode", motor.getMode());

        //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setVelocity(velocity);

        // Telemetry
        Setup.telemetry.addData(name + " motor mode", motor.getMode());

//        targetPower = targetPos;
//        currentPower = motor.getPower();
//        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motor.setPower(targetPower);
//
//        if(motor == null){  // safety check
//            Setup.telemetry.addData("Motor null in update()", name);
//            Setup.telemetry.update();
//            return;
//        }
//        targetPower = targetPos; //<<<OLD
//        currentPower = motor.getPower();
//        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motor.setPower(targetPower);
    }

    @Override
    public boolean isBusy(){
        return velocity != 0;
    }

    @Override
    public void telemetry(){
        Setup.telemetry.addData(name + " targetVelocity", velocity);
        Setup.telemetry.addData(name + " actualVelocity", motor.getVelocity());
        Setup.telemetry.addData(name + " motorMode", motor.getMode());
        Setup.telemetry.addData(name + " isBusy", isBusy());    }
}
