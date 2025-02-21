package org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2;

import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.AbstractMechanisms.ServoMechanism;
import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.Vision.Pipelines.Contour;

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
