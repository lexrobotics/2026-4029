package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class mOuttakeV4B extends ServoMechanism {
    public mOuttakeV4B() {
        super("V4B");
    }
    public static double INIT = 0.9;//0.9745
    public static double REST = 0.721;
    public static final double MIN = 0.05;
    public static final double MAX = 0.95;
    public static final double INTAKE_SPEC_BACK = 0.3765 ;
    public static final double INTAKE_SPEC_FRONT = 0.9335;
    public static final double BUCKET = 0.5551;
    public static final double SPECIMEN = 0.5002;//0.6001
    public static final double SPECIMEN_AUTO = 0.55;
    public static final double TRANSFER = 0.9375;//943
    public static final double HIGH_BUCKET = 0.5551;
    public static final double TRANSFER_PREP = 0.8;
}
