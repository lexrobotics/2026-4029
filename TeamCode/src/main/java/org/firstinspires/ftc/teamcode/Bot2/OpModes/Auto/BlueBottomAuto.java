package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mGate;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mPusher;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
import org.firstinspires.ftc.teamcode.Bot2.Setup;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;

@Autonomous(group = "1")
public class BlueBottomAuto extends LinearOpMode{
    // Start w/ intake facing wall
    private Bot bot;
    private ElapsedTime timer;
    private Setup setup;
    @Override
    public void runOpMode() throws InterruptedException {
        // Do we need to set the zero power behavior of the motor for auto
        timer = new ElapsedTime();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        bot.gate.setTarget(mGate.INIT);
        bot.pusher.setTarget(mPusher.INIT);
        bot.transfer.setTarget(mTransfer.OUTTAKE2);
        bot.intake.setTarget((mIntake.REST));

        waitForStart();
        if (isStopRequested()) return;

        bot.outtakeLeft.setVelocity(-(mOuttake.FAST));
        bot.outtakeRight.setVelocity((mOuttake.FAST));
        bot.update();
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(15, -33, Math.toRadians(0)))
                .build();
        drive.followTrajectory(traj);
        drive.turn(Math.toRadians(-45));

        bot.gate.setTarget(mGate.OPEN);
        bot.pusher.setTarget(mPusher.PUSH);
        bot.update();

        timer.reset();
        while (opModeIsActive() && timer.milliseconds() < 2000) {
            sleep(5);
        }

        bot.gate.setTarget(mGate.REST);
        bot.pusher.setTarget(mPusher.REST);
        bot.transfer.setTarget(mTransfer.OUTTAKE3);
        bot.update();

        timer.reset();
        while (opModeIsActive() && timer.milliseconds() < 2000) {
            sleep(5);
        }
        bot.gate.setTarget(mGate.OPEN);
        bot.pusher.setTarget(mPusher.PUSH);
        bot.update();

        timer.reset();
        while (opModeIsActive() && timer.milliseconds() < 2000) {
            sleep(5);
        }

        bot.gate.setTarget(mGate.REST);
        bot.pusher.setTarget(mPusher.REST);
        bot.transfer.setTarget(mTransfer.OUTTAKE1);
        bot.update();

        timer.reset();
        while (opModeIsActive() && timer.milliseconds() < 2000) {
            sleep(5);
        }
        bot.gate.setTarget(mGate.OPEN);
        bot.pusher.setTarget(mPusher.PUSH);
        bot.update();

        while (opModeIsActive() && timer.milliseconds() < 4500) {
            sleep(5);
        }
        timer.reset();

        bot.gate.setTarget(mGate.REST);
        bot.pusher.setTarget(mPusher.REST);
        bot.update();

        //drive.turn(Math.toRadians(45)); // Necessary? - We already have the bot's rotation set to 0 degrees below
        //drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(24, 0, Math.toRadians(0)))
                .build();
        drive.followTrajectory(traj2);

        while (opModeIsActive() && timer.milliseconds() < 10000) {
            sleep(5);
        }
        timer.reset();
    }
}

