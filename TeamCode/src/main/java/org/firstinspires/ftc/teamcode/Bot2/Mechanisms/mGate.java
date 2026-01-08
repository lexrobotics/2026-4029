package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

public class mGate extends ServoMechanism {
    public mGate() {super("Transfer");}
    public static double INIT = 0.2;
    public static double REST = 0.2;
    public static double OPEN = 0.4;
}