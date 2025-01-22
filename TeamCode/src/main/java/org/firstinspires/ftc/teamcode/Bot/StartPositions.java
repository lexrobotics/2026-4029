package org.firstinspires.ftc.teamcode.Bot;

import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Noodler;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;

public class StartPositions {
    public static double intakeMotorPos;
    public static double winchPos = Winch.INIT;
    public static double outtakeClawPos = OuttakeClaw.INIT;
    public static double outtakeWristPos = Wrist.INIT;
//    public static double outtakeV4BPos = V4B.INIT;
    public static double outtakeSlidesPos = OuttakeSlides.INIT;
    public static double intakeSlidesPos = IntakeSlides.INIT;
    public static double intakeArmPos = Arm.INIT;
    public static double noodlerPos = Noodler.INIT;
    public static Pose redRightDrivePos = new Pose(132, 108, Math.toRadians(270));
    public static Pose redLeftDrivePos = new Pose(132, 36, Math.toRadians(270));
    public static Pose blueRightDrivePos = new Pose(12, 36, Math.toRadians(90));
    public static Pose blueLeftDrivePos = new Pose(12, 108, Math.toRadians(90));
}
