package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;
@Config

public class Slides extends RunToPosMotorMechanism {
    public Slides() {
        super("Slides");
    }
    public static double INIT = 0;

    public static final double MIN = 0;
    public static final double MAX = 2100;
    public static final double BUC1 = 1250;
    public static final double BUC2 = 2100;
    public static final double SPC1 = 500;
    public static final double SPC2 = 950;
    public static final double REST = 0;
    public static final double INTAKE = 0;
    public static final double OUTTAKE_INCREMENT = 500;




}
