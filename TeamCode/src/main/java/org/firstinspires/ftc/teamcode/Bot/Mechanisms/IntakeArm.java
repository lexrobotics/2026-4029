package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class IntakeArm extends ServoMechanism {
    public IntakeArm() {
        super("IntakeArm");
    }
    public static final double INIT = 0.15;
    public static final double OPEN = 0.238;
    public static final double DROP = 0.164;
    public static final double MAX = 0.238;

}
