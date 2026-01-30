package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.MotorMechanism;

public class mIntake extends MotorMechanism {
    public mIntake() {super("Intake");}
    public static double INIT = 0;
    public static double REST = 0;
    public final static double FAST = -6000;
    public final static double SLOW = -3000;
    public final static double EJECT = 3000;
}
