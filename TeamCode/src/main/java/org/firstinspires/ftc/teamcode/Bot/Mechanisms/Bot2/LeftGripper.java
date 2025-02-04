package org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.CRServoMechanism;

@Config
public class LeftGripper extends CRServoMechanism {
    public LeftGripper() {
        super("LeftGripper");
        setVelocity(0);
    }
    public static double INIT = 0;
    public static final double INTAKE = 1;
    public static final double EJECT = -1;
    public static final double OUTTAKE = -0.5;
    public static final double STOP = 0;
}