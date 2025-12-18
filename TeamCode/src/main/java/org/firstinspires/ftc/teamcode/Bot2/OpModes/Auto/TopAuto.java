package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;

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
import org.firstinspires.ftc.teamcode.Bot2.Setup;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous(group = "1")
public class TopAuto extends LinearOpMode{
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

        waitForStart();
        if (isStopRequested()) return;

        /*while(opModeIsActive() && timer.seconds()<0.5){
            bot.update();
        }*/
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0))); // Facing +x / https://ftc-docs.firstinspires.org/en/latest/game_specific_resources/field_coordinate_system/field-coordinate-system.html
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(-57, 24, Math.toRadians(0))) // Robot (on the transparent plate sides) is 15.037 inches, 12 inches in a foot, the feild is 12 by 12 feet, each tile is 2 by 2 feet
                .build();
        drive.followTrajectory(traj);
        timer.reset();
    }
}
