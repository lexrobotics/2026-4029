package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;

@Config

public class mOuttakeSlides extends RunToPosMotorMechanism {
    public mOuttakeSlides() {
        super("slides");
    }
    public static double INIT = 0;

    public static final double MIN = 0;
    public static final double MAX = 2234;
    public static final double BUC1 = 1250;
    public static final double BUC2 = 2234;
    public static final double SPC1 = 500;
    public static final double SPC2 = 950;
    public static final double REST = 0;
    public static final double INTAKE = 0;
    public static final double OUTTAKE_INCREMENT = 500;
    public static final double TRANSFER = 500;
    public static final double HIGH_BUCKET = 500;
    public static final double HIGH_SPECIMEN = 500;
    public static final double LOW_BUCKET = 500;
    public static final double TRANSFER_PREP = 500;




}
