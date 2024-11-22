package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class V4B extends ServoMechanism {
    public V4B() {
        super("V4B");
    }
    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double HOR = 0;
    public static final double BUC2 = 0;
    public static final double ANG = 0;
    public static final double RST = 0;

    @Override
    public void setTarget(double target) {
        if(target != targetPos){
            targetPos = Range.clip(target, MIN, MAX);
            startTimer(Math.abs(targetPos-currentPos)/velocity);
        }
    }
}
