package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class OuttakeClaw extends ServoMechanism {
    public  OuttakeClaw(){ super("OuttakeClaw"); }
    public static double INIT = 0.5;
    public static double REST = 0.5;

    public static double MAX = 1;
    public static double MIN = 0;
    public static double OPEN = 1;
    public static double CLOSE = 0;
    public static double TRANSFER = 0;
    public static double TRANSFER_PREP = 0;
}
