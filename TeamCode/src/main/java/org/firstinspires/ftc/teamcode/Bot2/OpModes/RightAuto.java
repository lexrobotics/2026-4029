//package org.firstinspires.ftc.teamcode.Bot2.OpModes;
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
//import org.firstinspires.ftc.teamcode.Bot2.Setup;
//import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
//
///*
// * This is an example of a more complex path to really test the tuning.
// */
//@Autonomous(group = "1")
//public class RightAuto extends LinearOpMode {
//    private Bot bot;
//    private ElapsedTime timer;
//    private Setup setup;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        timer = new ElapsedTime();
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q3);
//
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        bot.init();
//
//        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//
//        drive.setPoseEstimate(new Pose2d(6, -(72-6), Math.toRadians(-90)));
//        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(4, -37, Math.toRadians(-90)))
//                .build();
//        drive.followTrajectory(traj);
//
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.SPECIMEN_AUTO);
//        bot.outtakeWrist.setTarget(mOuttakeWrist.SPECIMEN);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<1){
//            bot.update();
//        }
//
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN+830);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.SPECIMEN_AUTO);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.setRest();
//        bot.outtakeV4B.setTarget(mOuttakeV4B.INTAKE_SPEC_FRONT);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .splineToConstantHeading(new Vector2d(32, -35), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(41, -20), Math.toRadians(-90))
//                .build();
//        drive.followTrajectory(traj2);
//
//        Trajectory traj0 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(41, -62, Math.toRadians(-90)))
//                .build();
//        drive.followTrajectory(traj0);
//
//        Trajectory traj3 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(41, -49, Math.toRadians(-90)))
//                .build();
//        drive.followTrajectory(traj3);
//
//        sleep(3500);
//
//        Trajectory traj4 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(41, -64, Math.toRadians(-90)))
//                .build();
//        drive.followTrajectory(traj4);
//        timer.reset();
//        bot.outtakeWrist.setTarget(mOuttakeWrist.INTAKE_SPEC_FRONT);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.INTAKE_SPEC_FRONT);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<1){
//            bot.update();
//        }
//        timer.reset();
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<1){
//            bot.update();
//        }
//
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.SPECIMEN_AUTO);
//        bot.outtakeWrist.setTarget(mOuttakeWrist.SPECIMEN);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//
//        timer.reset();
//        while(opModeIsActive() && timer.seconds()<2){
//            bot.update();
//        }
//
//        Trajectory traj5 = drive.trajectoryBuilder(drive.getPoseEstimate(), true)
//                .splineToLinearHeading(new Pose2d(1, -33, Math.toRadians(-90)), Math.toRadians(90))
//                .build();
//        drive.followTrajectory(traj5);
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN+800);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_SPECIMEN);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
//        timer.reset();
//        bot.setRest();
//        bot.outtakeV4B.setTarget(mOuttakeV4B.INTAKE_SPEC_FRONT);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<1){
//            bot.update();
//        }
//        Trajectory traj6 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .splineToLinearHeading(new Pose2d(41, -61, Math.toRadians(90)), Math.toRadians(-90))
//                .build();
//        drive.followTrajectory(traj6);
//    }
//}
