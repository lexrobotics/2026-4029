package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config

public class mOuttakeWrist extends ServoMechanism {
    public mOuttakeWrist() {
        super("OuttakeWrist");
    }
    public static double INIT = 0.4055;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double INTAKE = 0.661; //0.532
    public static final double INTAKE_PREP = 0;//0.641
    public static final double INTAKE_SPEC_FRONT = 0.4055;
    public static final double INTAKE_SPEC_BACK = 1;

    public static final double TRANSFER = 0.078;//.641
    public static final double VERTICAL = 0.298;
    public static final double SPECIMEN = 1;// old: 0.89; 0.271
    public static final double SPECIMEN_SCORE = 1; // new pos

    public static final double REST = 0.298;
    public static final double BUCKET = 0.7895;
    public static final double BUCKET2 = 0.395;

    public static final double HANG = 1;
}
