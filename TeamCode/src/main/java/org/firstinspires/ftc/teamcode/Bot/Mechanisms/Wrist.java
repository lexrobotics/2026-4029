package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config

public class Wrist extends ServoMechanism {
    public Wrist() {
        super("Wrist");
    }
    public static double INIT = 1;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double POS1 = 0;
    public static final double POS2 = 0;
    public static final double VER = 0;
    public static final double SPC1 = 0;
    public static final double SPC2 = 0;
    public static final double RST = 1;
    public static final double TRA = 1;
}
