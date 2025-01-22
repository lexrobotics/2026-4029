package org.firstinspires.ftc.teamcode.Bot;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wheels;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;


public class StartPositions {
    public static double intakeMotorPos;
    public static double winchPos = Winch.INIT;
    public static double wristPos = Wrist.INIT;
    public static double slidesPos = Slides.INIT;
    public static double armPos = Arm.INIT;
    public static double fingersPos = Fingers.INIT;
    public static double wheelsPos = Wheels.INIT;
    public static Pose2d redRightDrivePos = new Pose2d(12, 72-12, Math.toRadians(270));
    public static Pose2d redLeftDrivePos = new Pose2d(-12, 72-12, Math.toRadians(270));
    public static Pose2d blueRightDrivePos = new Pose2d(-12, 72-12, Math.toRadians(90));
    public static Pose2d blueLeftDrivePos = new Pose2d(12, 72-12, Math.toRadians(90));
}
