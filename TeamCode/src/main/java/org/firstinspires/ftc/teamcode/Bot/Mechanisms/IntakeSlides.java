package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class IntakeSlides extends RunToPosMotorMechanism {
    public IntakeSlides() {
        super("IntakeSlides");
    }
    public static final double INIT = 0;

    public static final double MIN = 0;
    public static final double MAX = 1;
    public static final double POS1 = 0;
    public static final double POS2 = 0;
    public static final double RST = 0;
}
