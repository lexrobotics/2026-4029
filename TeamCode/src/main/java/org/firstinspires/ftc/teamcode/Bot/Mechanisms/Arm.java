package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class Arm extends ServoMechanism {
    public Arm() {
        super("Arm");
    }
    public static double INIT = 0.84;
    public static double REST = 0.57;
    public static final double MIN = 0;
    public static final double INTAKE = 0;
    public static final double BUCKET = 0.74;
    public static final double SPECIMEN = 0.74;

    public static final double HANG = 0.634;
}
