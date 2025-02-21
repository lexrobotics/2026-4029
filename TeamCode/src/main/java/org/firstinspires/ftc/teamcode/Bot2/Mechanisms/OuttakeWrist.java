package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config

public class OuttakeWrist extends ServoMechanism {
    public OuttakeWrist() {
        super("OuttakeWrist");
    }
    public static double INIT = 0.514;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double INTAKE = 0.661; //0.532
    public static final double INTAKE_PREP = 0;//0.641

    public static final double VERTICAL = 0.298;
    public static final double SPECIMEN = 0.395;// old: 0.89; 0.271
    public static final double SPECIMEN_SCORE = 0.271; // new pos

    public static final double REST = 0.298;
    public static final double BUCKET = 0.964;
    public static final double BUCKET2 = 0.395;

    public static final double HANG = 1;
}
