package org.firstinspires.ftc.teamcode.Bot;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeMotor;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;

public class StartPositions {
    public static double intakeMotorPos;
    public static double intakeSlidesPos = IntakeSlides.MIN;
    public static double outtakeClawPos = OuttakeClaw.DROP;
    public static double outtakeWristPos = 0.5;
    public static double outtakeV4BPos = 0.5;
    public static double outtakeSlidesPos = OuttakeSlides.MIN;
}
