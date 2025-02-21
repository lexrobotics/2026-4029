package org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1;

public abstract class ServoExMechanism extends Mechanism {
    protected ServoImplEx servo;
    private double latestPosition;

    public ServoExMechanism(String name) {
        super(name);
    }

    @Override
    public void init(double target) {
        setTarget(target);
        servo = Setup1.hardwareMap.get(ServoImplEx.class, name);
        servo.setPwmRange(new PwmControl.PwmRange(553, 2300));
        servo.setPosition(target);
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

    @Override
    public void startTimer(double time) {
        super.startTimer(time);
        latestPosition = currentPos;
    }

    @Override
    public void update() {
        servo.setPosition(targetPos);
        if (timer.seconds() >= timeLimit){
            currentPos = targetPos;
        }else{
            currentPos = latestPosition + timer.seconds()/timeLimit * (targetPos - latestPosition);
        }
    }


}
