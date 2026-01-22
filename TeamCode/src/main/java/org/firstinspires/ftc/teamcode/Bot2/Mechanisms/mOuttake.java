package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.MotorMechanism;

public class mOuttake extends MotorMechanism {
    public mOuttake (String name) {super(name);}
    public static double INIT = 0;
    public static double REST = 0;
    public static final double FAST = 0.65;
    public static final double SLOW = 0.48;
    public static final double SLOWAUTO = 0.53;
}
