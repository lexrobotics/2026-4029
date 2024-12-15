package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
@Config

public class OuttakeClaw extends ServoMechanism {
    public OuttakeClaw() {
        super("OuttakeClaw");
    }
    public static double INIT = 0;
    public static final double DROP = 0.15;
    public static final double MAX = 0.238;

}
