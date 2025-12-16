package org.firstinspires.ftc.teamcode.Bot2.OpModes;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
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
import org.firstinspires.ftc.vision.VisionPortal;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "1")
public class RightAuto extends LinearOpMode {
    private Bot bot;
    private ElapsedTime timer;

    private Setup setup;

    private VisionPortal visionPortal;

    private AprilTagProcessor aprilTag;


    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        aprilTag = new AprilTagProcessor.Builder()
                .build();
        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();

        waitForStart();
        if (isStopRequested()) return;



        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(24, 0, Math.toRadians(0)))
                .build();
        drive.followTrajectory(traj);

    }
}
