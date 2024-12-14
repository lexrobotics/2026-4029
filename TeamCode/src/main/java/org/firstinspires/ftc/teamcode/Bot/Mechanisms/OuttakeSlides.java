package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;
@Config

public class OuttakeSlides extends RunToPosMotorMechanism {
    public OuttakeSlides() {
        super("OuttakeSlides");
    }
    public static double INIT = 0;

    public static final double MIN = 0;
    public static final double MAX = 100;
    public static final double BUC1 = 100;
    public static final double BUC2 = 0;
    public static final double SPC1 = 0;
    public static final double SPC2 = 0;
    public static final double GRA = 0;
    public static final double RST = 0;



}
