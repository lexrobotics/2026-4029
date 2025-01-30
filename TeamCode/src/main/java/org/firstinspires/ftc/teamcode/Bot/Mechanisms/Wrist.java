package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config

public class Wrist extends ServoMechanism {
    public Wrist() {
        super("Wrist");
    }
    public static double INIT = 1;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double INTAKE = 0.298;

    public static final double VERTICAL = 0.298;
    public static final double SPECIMEN = 0.306;
    public static final double SPECIMEN_SCORE = 0.78;

    public static final double REST = 0.298;
    public static final double BUCKET = 1;
    public static final double HANG = 1;
}
