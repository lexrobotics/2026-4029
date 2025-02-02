package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RightGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Autonomous(group = "0")
public class BucketAutoQ2 extends LinearOpMode {
    private Setup setup;
    private Bot bot;
    private ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(7)
                .back(44)
                .turn(Math.toRadians(45))
                .back(3)
                .build();

        TrajectorySequence trajectory2 = drive.trajectorySequenceBuilder(new Pose2d(-55, 55, Math.toRadians(225)))
                .forward(3)
                .turn(Math.toRadians(-45))
                .forward(20)
                .turn(Math.toRadians(-90))
                .forward(30)

                .build();

        waitForStart();
        timer.reset();

        if (isStopRequested()) return;

        drive.followTrajectorySequence(trajectory);

        timer.reset();
        bot.slides.setTarget(Slides.BUC2);
        bot.wrist.setTarget(Wrist.BUCKET);
        while(opModeIsActive() && timer.seconds()<2){
            bot.update();
        }
//
//        timer.reset();
//        bot.arm.setTarget(Arm.BUCKET);
//        while(opModeIsActive() && timer.seconds()<2){
//            bot.update();
//        }
//
//        timer.reset();
//        bot.leftGripper.setTarget(LeftGripper.EJECT);
//        bot.rightGripper.setTarget(RightGripper.EJECT);
//        while(opModeIsActive() && timer.seconds()<2){
//            bot.update();
//        }

        timer.reset();
        bot.leftGripper.setTarget(LeftGripper.STOP);
        bot.rightGripper.setTarget(RightGripper.STOP);
        bot.slides.setTarget(Slides.REST);
        bot.wrist.setTarget(Wrist.REST);
        bot.arm.setTarget(Arm.REST);
        while(opModeIsActive() && timer.seconds()<2){
            bot.update();
        }

        drive.followTrajectorySequence(trajectory2);

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
