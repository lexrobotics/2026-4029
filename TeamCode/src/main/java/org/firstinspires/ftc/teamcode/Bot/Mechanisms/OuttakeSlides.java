package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;

public class OuttakeSlides extends RunToPosMotorMechanism {

    public OuttakeSlides() {
        super("OuttakeSlides");
    }
    public static double INIT = 0;
    public static double TRANSFER = 0;

    public static final double MIN = 0;
    public static final double MAX = 2234;
    public static final double LOW_BUCKET = 1250;
    public static final double HIGH_BUCKET = 2234;
    public static final double LOW_SPECIMEN = 500;
    public static final double HIGH_SPECIMEN = 950;
    public static final double REST = 0;
    public static final double INTAKE = 0;
    public static final double OUTTAKE_INCREMENT = 500;
}
