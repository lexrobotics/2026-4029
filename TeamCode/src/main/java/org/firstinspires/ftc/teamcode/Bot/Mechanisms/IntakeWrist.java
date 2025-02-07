package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class IntakeWrist extends ServoMechanism {
    public IntakeWrist() {
        super("IntakeWrist");
    }
    public static double INIT = 0.514;
    public static double TRANSFER = 0;

    public static final double MAX = 1;
    public static final double MIN = 0;

    public static final double INTAKE = 0.661;
    public static final double INTAKE_PREP = 0;

    public static final double VERTICAL = 0.298;
    public static final double SPECIMEN = 0.395;
    public static final double SPECIMEN_SCORE = 0.271;

    public static final double REST = 0.298;
    public static final double BUCKET = 0.964;
    public static final double BUCKET2 = 0.395;
}
