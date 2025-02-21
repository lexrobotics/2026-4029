package org.firstinspires.ftc.teamcode.DEADBot.Old;

import com.pedropathing.localization.Pose;

import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.Fingers;
import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.LeftGripper;
import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.Slides;

import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.Arm;
import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.Wrist;
import org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2.Winch;

public class StartPositions {
    public static double winchPos = Winch.INIT;
    public static double wristPos = Wrist.INIT;
    public static double fingersPos = Fingers.INIT;
    public static double grippersPos = LeftGripper.INIT;
    public static double slidesPos = Slides.INIT;
    public static double armPos = Arm.INIT;
    public static Pose redRightDrivePos = new Pose(132, 108, Math.toRadians(270));
    public static Pose redLeftDrivePos = new Pose(132, 36, Math.toRadians(270));
    public static Pose blueRightDrivePos = new Pose(12, 36, Math.toRadians(90));
    public static Pose blueLeftDrivePos = new Pose(12, 108, Math.toRadians(90));
}
