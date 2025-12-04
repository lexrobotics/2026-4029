package org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Bot2.Setup;

public abstract class MotorMechanism extends Mechanism {
    protected DcMotorEx motor;
    protected double targetPower;
    protected double currentPower;

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
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        setTarget(target);
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
        if (motor == null) return; //stops the rest of the update to avoid crash

        double actualPower = motor.getPower();
        double actualVel = 0;
        try {
            actualVel = ((DcMotorEx)motor).getVelocity();
        } catch (Exception ignored) {}

        Setup.telemetry.addData(name + " requestedPower", targetPower);
        Setup.telemetry.addData(name + " actualPower", actualPower);
        Setup.telemetry.addData(name + " actualVelocityTicksPerSec", actualVel);
        Setup.telemetry.addData(name + " motorMode", motor.getMode());




        targetPower = velocity;
        currentPower = motor.getPower();

        //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(targetPower);

        // Telemetry
        Setup.telemetry.addData(name + " targetPower", targetPower);
        Setup.telemetry.addData(name + " currentPower", currentPower);
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
        return targetPower != 0;
    }

    @Override
    public void telemetry(){
        Setup.telemetry.addData(name + "currentPower", currentPower);
        Setup.telemetry.addData(name + "targetPower", targetPower);
        Setup.telemetry.addData(name + " isBusy", isBusy());
    }
}
