package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.CRServoMechanism;

@Config
public class RightGripper extends CRServoMechanism {
    public RightGripper() {
        super("RightGripper");
        setVelocity(0);
    }
    public static double INIT = 0;
    public static final double INTAKE = 1;
    public static final double OUTTAKE = -1;
    public static final double STOP = 0;
}