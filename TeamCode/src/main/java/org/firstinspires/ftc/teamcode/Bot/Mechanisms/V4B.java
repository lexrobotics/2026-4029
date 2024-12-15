package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config
public class V4B extends ServoMechanism {
    public V4B() {
        super("V4B");
    }
    public static double INIT = 0.198;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double HOR = 0.9142;
    public static final double ANG = 0.7302;
//    public static final double ANG = 0;
    public static final double RST = 0;
    public static final double WALL = 0.846599999999;
    public static final double TRANSFER = 0.198;


    @Override
    public void setTarget(double target) {
        if(target != targetPos){
            targetPos = Range.clip(target, MIN, MAX);
            startTimer(Math.abs(targetPos-currentPos)/velocity);
        }
    }
}
