package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class OuttakeV4B extends ServoMechanism {
    public OuttakeV4B() {
        super("OuttakeV4B");
    }
    public static double INIT = 0.859;
    public static double REST = 0.721;
    public static final double MIN = 0.05;
    public static final double MAX = 0.95;
    public static double TRANSFER = 0.5;
    public static final double INTAKE = 0.135;
    public static final double INTAKE_PREP = 0.28;

    public static final double LOW_BUCKET = 0.984;
    public static final double HIGH_BUCKET = 0.571;
    public static final double LOW_SPECIMEN = 0.571;
    public static final double HIGH_SPECIMEN = 0.571;

    public static final double HANG = 0.634;
}
