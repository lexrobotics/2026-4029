package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Bot.Old.Bot1;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.RightGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Old.Setup1;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Autonomous(group = "0")
public class BucketAutoQ2 extends LinearOpMode {
    private Setup1 setup;
    private Bot1 bot;
    private ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();
        setup = new Setup1(hardwareMap, telemetry, true, this, Setup1.OpModeType.AUTO, Setup1.Team.Q3);

        bot = new Bot1(Setup1.mechStates, Setup1.sensorStates);
        bot.init();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(6)
                .forward(43)
                .turn(Math.toRadians(50))
                .forward(3)
                .build();

//        TrajectorySequence trajectory2 = drive.trajectorySequenceBuilder(new Pose2d(-55, 55, Math.toRadians(225)))
//                .forward(3)
//                .turn(Math.toRadians(-45))
//                .forward(20)
//                .turn(Math.toRadians(90))
//                .forward(30)
//                .turn(Math.toRadians(90))
//                .build();

        waitForStart();
        timer.reset();

        if (isStopRequested()) return;
        drive.followTrajectorySequence(trajectory);

        timer.reset();
        bot.slides.setTarget(2400);
        while(opModeIsActive() && timer.seconds()<2){
            bot.update();
        }

        timer.reset();
        bot.arm.setTarget(Arm.BUCKET2);
        bot.wrist.setTarget(Wrist.BUCKET2);
        while(opModeIsActive() && timer.seconds()<2){
            bot.update();
        }

        timer.reset();
        bot.leftGripper.setTarget(LeftGripper.EJECT);
        bot.rightGripper.setTarget(RightGripper.EJECT);
        while(opModeIsActive() && timer.seconds()<1){
            bot.update();
        }

        timer.reset();
        bot.arm.setTarget(Arm.REST);
        bot.wrist.setTarget(Arm.MAX);
        while(opModeIsActive() && timer.seconds()<1){
            bot.update();
        }

        timer.reset();
        bot.leftGripper.setTarget(LeftGripper.STOP);
        bot.rightGripper.setTarget(RightGripper.STOP);
        bot.slides.setTarget(Slides.REST);
        bot.wrist.setTarget(Wrist.REST);
        bot.arm.setTarget(Arm.REST);
        while(opModeIsActive() && timer.seconds()<2){
            bot.update();
        }

//        drive.followTrajectorySequence(trajectory2);

//        timer.reset();
//        bot.slides.setTarget(Slides.BUC1);
//        bot.arm.setTarget(Arm.SPECIMEN);
//        bot.wrist.setTarget(Wrist.SPECIMEN_SCORE);
//        while(opModeIsActive() && timer.seconds()<2){
//            bot.update();
//        }

        while (!isStopRequested() && opModeIsActive()) ;
    }
}
