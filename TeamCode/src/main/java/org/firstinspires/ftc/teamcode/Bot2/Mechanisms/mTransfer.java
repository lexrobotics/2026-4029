package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.ServoMechanism;

@Config
public class mTransfer extends ServoMechanism {
    public mTransfer() { super("Transfer");}
    public static double INIT = 0.05;
    public static double REST = 0.05;
    public static final double INTAKE1 = 0.95; //  (blue)
    public static final double INTAKE2 = 0.20; //  (green)
    public static final double INTAKE3 = 0.575; //  (red)
    public static final double OUTTAKE1 = 0.37; // (blue)
    public static final double OUTTAKE2 = 0.75; // (green)
    public static final double OUTTAKE3 = 0; // (red)

}
