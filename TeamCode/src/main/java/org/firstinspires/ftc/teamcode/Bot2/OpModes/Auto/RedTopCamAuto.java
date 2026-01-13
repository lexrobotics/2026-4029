//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.acmerobotics.roadrunner.trajectory.Trajectory;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Bot2.Bot;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mGate;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
//import org.firstinspires.ftc.teamcode.Bot2.Setup;
//import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
//
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
//import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;
//
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//
//@Autonomous(group = "1")
//public class RedTopCamAuto extends LinearOpMode{
//    // Start w/ outtake facing wall
//    private Bot bot;
//    private ElapsedTime timer;
//    private Setup setup;
//    private AprilTagProcessor aprilTag;
//    private VisionPortal visionPortal;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        // Do we need to set the zero power behavior of the motor for auto
//        timer = new ElapsedTime();
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);
//
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        bot.init();
//
//        initAprilTag();
//
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        bot.transfer.setTarget(mTransfer.INIT);
//        bot.carousel.setTarget(mCarousel.OUTTAKE2);
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        int code = -1;
//
//        timer.reset();
//
//        while(opModeIsActive() && code == -1 && timer.milliseconds() < 1500){
//            for(AprilTagDetection d : aprilTag.getDetections()) {
//                if(d.metadata != null){
//                    code = d.id - 20;
//                    break;
//                }
//            }
//        }
//
//        bot.outtakeLeft.setVelocity(-(mOuttake.SLOW));
//        bot.outtakeRight.setVelocity((mOuttake.SLOW));
//        bot.update();
//        drive.setPoseEstimate(new Pose2d(7.75, 30.38, Math.toRadians(0)));
//        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-72, 72, Math.toRadians(0)))
//                .build();
//        drive.followTrajectory(traj);
//        drive.turn(Math.toRadians(45));
//
//        bot.gate.setTarget(mGate.OPEN);
//
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 2000) {
//            sleep(5);
//        }
//
//        bot.transfer.setTarget(mTransfer.TRANSFER);
//        bot.update();
//
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 2000) {
//            sleep(5);
//        }
//
//        bot.transfer.setTarget(mTransfer.INIT);
//        bot.carousel.setTarget(mCarousel.OUTTAKE3);
//        bot.update();
//
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 2000) {
//            sleep(5);
//        }
//        bot.transfer.setTarget(mTransfer.TRANSFER);
//        bot.update();
//
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 2000) {
//            sleep(5);
//        }
//
//        bot.carousel.setTarget(mCarousel.OUTTAKE1);
//        bot.transfer.setTarget(mTransfer.INIT);
//        bot.update();
//
//        timer.reset();
//        while (opModeIsActive() && timer.milliseconds() < 2000) {
//            sleep(5);
//        }
//        bot.transfer.setTarget(mTransfer.TRANSFER);
//        bot.update();
//
//        while (opModeIsActive() && timer.milliseconds() < 4500) {
//            sleep(5);
//        }
//        timer.reset();
//
//        drive.turn(Math.toRadians(-45)); // Necessary? - We already have the bot's rotation set to 0 degrees below
//        //drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
//        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-110.5, 105, Math.toRadians(0)))
//                .build();
//        drive.followTrajectory(traj2);
//
//        while (opModeIsActive() && timer.milliseconds() < 10000) {
//            sleep(5);
//        }
//        timer.reset();
//    }
//
//    private void initAprilTag() {
//        aprilTag = new AprilTagProcessor.Builder().build();
//
//        VisionPortal.Builder builder = new VisionPortal.Builder();
//        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam"));
//        builder.addProcessor(aprilTag);
//
//        visionPortal = builder.build();
//    }
//}