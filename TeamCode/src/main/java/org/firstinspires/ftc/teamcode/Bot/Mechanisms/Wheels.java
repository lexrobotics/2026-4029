package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.CoupledCRServoMechanism;

@Config
public class Wheels extends CoupledCRServoMechanism {
    public Wheels() {
        super("Wheels"); //TODO
        setVelocity(0);
    }
    public static  double INIT = 0;
    public static final double FORWARD_MAX = 1;
    public static final double MIN_SPEED = 0;
    public static final double REVERSE_MAX = -1;
}