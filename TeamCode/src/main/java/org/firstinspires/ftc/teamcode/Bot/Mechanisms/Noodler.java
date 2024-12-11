package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.CRServoMechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.MotorMechanism;

public class Noodler extends CRServoMechanism {
    public Noodler() {
        super("Noodler"); //TODO
    }
    public static final double FORWARD_MAX = 1;
    public static final double MIN_SPEED = 0;
    public static final double REVERSE_MAX = -1;
}