package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class Arm extends ServoMechanism {
    public Arm() {
        super("Arm");
    }
    public static double INIT = 0.31;
    public static final double MIN = 0.1;
    public static final double TRANSFER = 0.3;
    public static final double INTAKE = 0.3;
    public static final double BUCKET = 0.3;
    public static final double HANG = 0.3;
}
