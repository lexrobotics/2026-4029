package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config
public class mIntakeWrist extends ServoMechanism {
    public mIntakeWrist() {
        super("IntakeWrist");
    }
    public static double INIT = 1;
    public static double TRANSFER = 0.9;

    public static final double MAX = 1;
    public static final double MIN = 0;

    public static final double INTAKE = 0.12;//0.089
    public static final double INTAKE_PREP = 0.661;

//    public static final double SPECIMEN_GRAB = 0.395;
//    public static final double SPECIMEN_GRAB_FRONT = 1;
//    public static final double SPECIMEN_SCORE = 0.271;

    public static final double REST = 1;
//    public static final double BUCKET = 0.964;
//    public static final double BUCKET2 = 0.395;
}
