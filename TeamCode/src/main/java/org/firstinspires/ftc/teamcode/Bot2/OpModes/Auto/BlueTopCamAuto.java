package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot2.Bot;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mGate;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mPusher;
import org.firstinspires.ftc.teamcode.Bot2.Setup;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous(group = "1")
public class BlueTopCamAuto extends LinearOpMode{
    // Start w/ outtake facing wall
    private Bot bot;
    private ElapsedTime timer;
    private Setup setup;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    private final double[] code1 = {mTransfer.OUTTAKE2, mTransfer.OUTTAKE3, mTransfer.OUTTAKE1};
    private final double[] code2 = {mTransfer.OUTTAKE3, mTransfer.OUTTAKE2, mTransfer.OUTTAKE1};
    private final double[] code3 = {mTransfer.OUTTAKE1, mTransfer.OUTTAKE3, mTransfer.OUTTAKE2};
    private double[] outtakeCode;

    @Override
    public void runOpMode() throws InterruptedException {
        // Do we need to set the zero power behavior of the motor for auto
        timer = new ElapsedTime();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);

        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.init();

        initAprilTag();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        bot.gate.setTarget(mGate.INIT);
        bot.transfer.setTarget(mTransfer.OUTTAKE2);
        bot.intake.setTarget((mIntake.REST));

        waitForStart();
        if (isStopRequested()) return;

        int code = -1;

        timer.reset();

        while(opModeIsActive() && code == -1 && timer.milliseconds() < 1500){
            for(AprilTagDetection d : aprilTag.getDetections()) {
                if(d.metadata != null){
                    code = d.id - 20;
                    break;
                }
            }
        }

        if(code == 1){outtakeCode = code1;}
        else if(code == 2){outtakeCode = code2;}
        else if(code == 3){outtakeCode = code3;}
        else{outtakeCode = code1;} // defaults to green first if cam fails
        telemetry.addLine("The code is: "+code);

        bot.transfer.setTarget(outtakeCode[0]);

        bot.outtakeLeft.setVelocity(-(mOuttake.SLOW));
        bot.outtakeRight.setVelocity((mOuttake.SLOW));
        bot.update();
        drive.setPoseEstimate(new Pose2d(-15, -39, Math.toRadians(0))); //7.5
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(-72, -72, Math.toRadians(0)))
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
        bot.transfer.setTarget(outtakeCode[1]);
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

        bot.transfer.setTarget(outtakeCode[2]);
        bot.gate.setTarget(mGate.REST);
        bot.pusher.setTarget(mPusher.REST);
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

        /*drive.turn(Math.toRadians(45)); // Necessary? - We already have the bot's rotation set to 0 degrees below
        //drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(-110.5, -105, Math.toRadians(0)))
                .build();
        drive.followTrajectory(traj2);*/

        while (opModeIsActive() && timer.milliseconds() < 10000) {
            sleep(5);
        }
        timer.reset();
    }

    private void initAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam"));
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();
    }

    private double alignAngle() {

        for (AprilTagDetection d : aprilTag.getDetections()) {
            if (d.metadata != null && d.id == 20) {
                return Math.toRadians(d.ftcPose.bearing);
            }
        }
        return 0.0;

    }
}

