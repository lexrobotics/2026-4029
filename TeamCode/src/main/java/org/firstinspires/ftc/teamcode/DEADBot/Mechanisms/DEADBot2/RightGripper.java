package org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.AbstractMechanisms.CRServoMechanism;

@Config
public class RightGripper extends CRServoMechanism {
    public RightGripper() {
        super("RightGripper");
        setVelocity(0);
    }
    public static double INIT = 0;
    public static final double INTAKE = -1;
    public static final double EJECT = 1;
    public static final double STOP = 0;
    public static final double OUTTAKE = 0.5;

}