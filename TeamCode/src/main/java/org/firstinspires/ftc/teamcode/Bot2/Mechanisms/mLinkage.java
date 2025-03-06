package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config
public class mLinkage extends ServoMechanism {
    public mLinkage(){ super("Linkage"); }
    public static double INIT = 0.1;
    public static double REST = 0;
    public static double TRANSFER = 0;
    public static double TRANSFER_PREP = 0;

    public static double MAX = 1;
    public static double MIN = 0;
    public static double POSITION1 = 0.25;
    public static double POSITION2 = 0.5;
    public static double POSITION3 = 0.75;
    public static double EXTEND = 1;
}
