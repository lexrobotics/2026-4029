package org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot2.Setup;

public abstract class CoupledServoMechanism extends Mechanism {
    protected Servo servoLeft, servoRight;
    protected double latestPosition;

    public CoupledServoMechanism(String name) {
        super(name);
    }

    @Override
    public void init(double target) {
        setTarget(target);
        servoLeft = Setup.hardwareMap.get(Servo.class, name + "Left");
        servoRight = Setup.hardwareMap.get(Servo.class, name + "Right");
//        servoLeft.setDirection(Servo.Direction.REVERSE);
        servoLeft.setPosition(target);//+0.0078125
        servoRight.setPosition(target);
        currentPos = target;
        latestPosition = target;
    }

    @Override
    public void setTarget(double target) {
        if(target != targetPos){
            targetPos = Range.clip(target, 0, 1);
            startTimer(Math.abs(targetPos-currentPos)/velocity);
        }
    }

    public void reverse(boolean isLeftReversed, boolean isRightReversed){
        if(isLeftReversed){
            servoLeft.setDirection(Servo.Direction.REVERSE);
        }
        if(isRightReversed){
            servoRight.setDirection(Servo.Direction.REVERSE);
        }
    }

    @Override
    public void startTimer(double time) {
        super.startTimer(time);
        latestPosition = currentPos;
    }

    @Override
    public void update() {
        servoLeft.setPosition(targetPos);
        servoRight.setPosition(targetPos);
        if (timer.seconds() >= timeLimit){
            currentPos = targetPos;
        }else{
            currentPos = latestPosition + timer.seconds()/timeLimit * (targetPos - latestPosition);
        }
    }
}
