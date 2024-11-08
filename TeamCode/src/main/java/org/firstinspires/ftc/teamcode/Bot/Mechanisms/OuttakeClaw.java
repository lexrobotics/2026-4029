package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class OuttakeClaw extends ServoMechanism {
    public OuttakeClaw() {
        super("OuttakeClaw");
    }
    public static final double MAX = 1;
    public static final double MIN = 0;
    public static final double GRIP = .5;
    public static final double DROP = .7;

}
