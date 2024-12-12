package org.firstinspires.ftc.teamcode.Bot;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Noodler;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;

public class StartPositions {
    public static double intakeMotorPos;
    public static double winchPos = Winch.INIT;
    public static double outtakeClawPos = OuttakeClaw.INIT;
    public static double outtakeWristPos = 0.5;
    public static double outtakeV4BPos = V4B.INIT;
    public static double outtakeSlidesPos = OuttakeSlides.INIT;
    public static double intakeSlidesPos = IntakeSlides.INIT;
    public static double intakeArmPos = IntakeArm.INIT;
    public static double noodlerPos = Noodler.INIT;

}
