package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class mCarousel extends ServoMechanism {
    public mCarousel() { super("Carousel");}
    public static double INIT = 0;
    public static double REST = 0;
    public static final double OUTTAKE1 = 0;
    public static final double OUTTAKE2 = 0;
    public static final double OUTTAKE3 = 0;
    public static final double INTAKE1 = 0;
    public static final double INTAKE2 = 0;
    public static final double INTAKE3 = 0;
}
