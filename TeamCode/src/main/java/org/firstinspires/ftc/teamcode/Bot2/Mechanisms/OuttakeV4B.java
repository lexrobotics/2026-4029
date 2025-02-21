package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class OuttakeV4B extends ServoMechanism {
    public OuttakeV4B() {
        super("OuttakeV4B");
    }
    public static double INIT = 0.859;
    public static double REST = 0.721;
    public static final double MIN = 0.05;
    public static final double MAX = 0.95;
    public static final double INTAKE = 0.135;
    public static final double INTAKE_PREP = 0.28;
    public static final double BUCKET = 0.984;
    public static final double BUCKET2 = 0.571;
    public static final double SPECIMEN = 0.571;
    public static final double SPECIMEN_PREP = 0.591;
    public static final double HANG = 0.634;
}
