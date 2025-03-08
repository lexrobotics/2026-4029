package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Setup;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "1")
public class RightAuto extends LinearOpMode {
    private Bot bot;
    private ElapsedTime timer;
    private Setup setup;
    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        waitForStart();

        if (isStopRequested()) return;
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .back(30)
                .build();

        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN);
        bot.outtakeV4B.setTarget(mOuttakeV4B.SPECIMEN);
        bot.outtakeWrist.setTarget(mOuttakeWrist.SPECIMEN);
        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
        drive.followTrajectory(traj);

        timer.reset();
        while(opModeIsActive() && timer.seconds()<3){
            bot.update();
        }

        timer.reset();
        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN+200);
        while(opModeIsActive() && timer.seconds()<3){
            bot.update();
        }

        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
        while(opModeIsActive() && timer.seconds()<3){
            bot.update();
        }

        bot.setRest();
        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
        while(opModeIsActive() && timer.seconds()<3){
            bot.update();
        }

    }
}
