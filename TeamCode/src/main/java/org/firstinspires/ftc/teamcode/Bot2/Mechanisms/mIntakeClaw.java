package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config
public class mIntakeClaw extends ServoMechanism {
    public mIntakeClaw(){ super("IntakeClaw"); }
    public static double INIT = 0.5;
    public static double REST = 0.5;
    public static double MAX = 1;
    public static double MIN = 0;
    public static double OPEN = 1;
    public static double CLOSE = 0;
    public static double TRANSFER = 0;
    public static double TRANSFER_PREP = 0;
}
