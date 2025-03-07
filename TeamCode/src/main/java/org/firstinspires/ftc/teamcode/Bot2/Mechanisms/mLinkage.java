package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config
public class mLinkage extends ServoMechanism {
    public mLinkage(){ super("Linkage"); }
    public static double INIT = 0.05;
    public static double REST = 0;
    public static double TRANSFER = 0.2125;
    public static double TRANSFER_PREP = 0.2125;

    public static double MAX = 0.44;
    public static double MIN = 0;

    public static double EXTEND = MAX;
}
