package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config

public class OuttakeWrist extends ServoMechanism {
    public OuttakeWrist() {
        super("OuttakeWrist");
    }
    public static double INIT = 0.5;

    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double POS1 = 0.5;
    public static final double POS2 = 0.5;
    public static final double VER = 0.5;
    public static final double SPC1 = 0.5;
    public static final double SPC2 = 0.5;
    public static final double RST = 0.5;
    public static final double TRA = 0;
}
