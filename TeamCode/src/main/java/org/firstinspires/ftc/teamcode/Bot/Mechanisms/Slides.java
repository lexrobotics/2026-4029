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
    public static final double MAX = 1000;
    public static final double BUC1 = 400;
    public static final double BUC2 = 500;
    public static final double SPC1 = 100;
    public static final double SPC2 = 500;
    public static final double GRA = 10;
    public static final double RST = 0;
    public static final double INTAKE = 0;



}
