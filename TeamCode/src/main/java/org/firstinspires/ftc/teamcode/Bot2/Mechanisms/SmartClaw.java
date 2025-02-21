package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision.Pipelines.Contour;

public class SmartClaw extends ServoMechanism {
    public static final double range = Math.toRadians(270);
    public static final double centerLine = 0.5;
    public SmartClaw() {
        super("ClawRot");
    }
    public double rotateTarget(){
        return centerLine - (Contour.theta/range);
    }

}
