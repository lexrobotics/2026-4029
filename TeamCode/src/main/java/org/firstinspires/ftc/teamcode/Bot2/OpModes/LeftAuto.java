//package org.firstinspires.ftc.teamcode.Bot2.OpModes;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
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
//public class LeftAuto extends LinearOpMode {
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
//        //set outtake
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_BUCKET);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.BUCKET);
//        bot.outtakeWrist.setTarget(mOuttakeWrist.BUCKET);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<1.5){
//            bot.update();
//        }
//
//        //drive to bucket
//        drive.setPoseEstimate(new Pose2d(-32, -(72-6), Math.toRadians(0)));
//        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-57, -61, Math.toRadians(45)))
//                .build();
//        drive.followTrajectory(traj);
//
//        outtakeAndRest();
//        //go to sample
//        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-45, -53.5, Math.toRadians(90)))
//                .build();
//        drive.followTrajectory(traj2);
//
//        intake();
//
//        //back to bucket
//        Trajectory traj1029 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-57, -61, Math.toRadians(45)))
//                .build();
//        drive.followTrajectory(traj1029);
//
//        outtakeAndRest();
//
//        //second sample
//        Trajectory traj0 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-57, -53, Math.toRadians(90)))
//                .build();
//        drive.followTrajectory(traj0);
//
//        intake();
//
//        //bucket
//
//        Trajectory traj3 = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .lineToLinearHeading(new Pose2d(-57, -61, Math.toRadians(45)))
//                .build();
//        drive.followTrajectory(traj3);
//
//        outtakeAndRest();
//
//        Trajectory trajp = drive.trajectoryBuilder(drive.getPoseEstimate())
//                .splineToLinearHeading(new Pose2d(-17, -25, Math.toRadians(90)), Math.toRadians(-90))
//                .build();
//        drive.followTrajectory(trajp);
//
//
//
//    }
//
//    private void intake(){
//
//        bot.turret.setTarget(mTurret.INIT);
//        //extend intake and set
//        timer.reset();
//        bot.linkage.setTarget(mLinkage.EXTEND);
//        bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
//        bot.intakeWrist.setTarget(mIntakeWrist.INTAKE);
//        while(opModeIsActive() && timer.seconds()<0.8){
//            bot.update();
//        }
//        //close intake claw
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//        bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<0.4){
//            bot.update();
//        }
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//        bot.intakeWrist.setTarget(mIntakeWrist.TRANSFER);
//        while(opModeIsActive() && timer.seconds()<0.4){
//            bot.update();
//        }
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//        bot.linkage.setTarget(mLinkage.TRANSFER);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER_PREP);
//        bot.outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
//        bot.intakeClaw.setTarget(mIntakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<0.8){
//            bot.update();
//        }
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
//        while(opModeIsActive() && timer.seconds()<0.8){
//            bot.update();
//        }
//
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        while(opModeIsActive() && timer.seconds()<1.8){
//            bot.update();
//        }
//
//
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//
//        bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<0.8){
//            bot.update();
//        }
//
//        timer.reset();
//        bot.outtakeSlides.setTarget(mOuttakeSlides.HIGH_BUCKET);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.BUCKET);
//        bot.outtakeWrist.setTarget(mOuttakeWrist.BUCKET);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.CLOSE);
//        bot.turret.setTarget(mTurret.INIT);
//
//        while(opModeIsActive() && timer.seconds()<1.2){
//            bot.update();
//        }
//
//    }
//    private void outtakeAndRest(){
//        //outtake
//
//        timer.reset();
//        bot.turret.setTarget(mTurret.INIT);
//
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        while(opModeIsActive() && timer.seconds()<0.5){
//            bot.update();
//        }
////rest
//        timer.reset();
//        bot.setRest();
//        bot.turret.setTarget(mTurret.INIT);
//
//        bot.intakeClaw.setTarget(mIntakeClaw.OPEN);
//        bot.outtakeClaw.setTarget(mOuttakeClaw.OPEN);
//        bot.outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
//        while(opModeIsActive() && timer.seconds()<1.5){
//            bot.update();
//        }
//    }
//
//}
