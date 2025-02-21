//package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Slides;
//import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.pathgen.BezierCurve;
//import com.pedropathing.pathgen.BezierLine;
//import com.pedropathing.pathgen.Path;
//import com.pedropathing.pathgen.PathChain;
//import com.pedropathing.pathgen.Point;
//import com.pedropathing.localization.Pose;
//
//import org.firstinspires.ftc.teamcode.Bot.Old.StartPositions;
//import com.pedropathing.util.Constants;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
//
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
//import org.firstinspires.ftc.teamcode.Bot.Drivetrain.RoadRunnerPaths;
//import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//import org.firstinspires.ftc.teamcode.Bot.Old.StartPositions;
//import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
//
//@Autonomous(name = "AutoBlueRight", group = "q1")
//public class AutoBlueRight extends LinearOpMode {
//    private Setup setup;
//    private Bot bot;
//    private IMUStatic imu;
//    private ActionSequences actionSequences;
//    private RoadRunnerPaths paths;
//    private ElapsedTime timer;
//    @Override
//    public void runOpMode(){
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        setup.disableMechanism("Winch");
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        imu = new IMUStatic();
//        bot.init(StartPositions.blueRightDrivePos);
//        actionSequences = new ActionSequences(bot);
//        paths = new RoadRunnerPaths();
//        timer = new ElapsedTime();
//
//        waitForStart();
//        timer.reset();
//
////        actionSequences.
//        bot.drivetrain.setTrajectorySequence(bot.drivetrain.backward(24));
//        actionSequences.specimenScorePrep(2);
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//        bot.slides.setTarget(Slides.SPC2 - 100);
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//        bot.init();
//        bot.drivetrain.setTrajectorySequence(bot.drivetrain.leftward(24));
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//        bot.drivetrain.setTrajectorySequence(bot.drivetrain.backward(24));
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
////        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 1));
////        actionSequences.OuttakeRest(true);
////        while(opModeIsActive() && bot.isBusy()){
////            bot.update();
////        }
////
////
////        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 2));
////        actionSequences.OuttakeRest(false);
////        while(opModeIsActive() && bot.isBusy()){
////            bot.update();
////        }
////
////        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 3));
////        actionSequences.OuttakeRest(false);
////        while(opModeIsActive() && bot.isBusy()){
////            bot.update();
////        }
//    }
//}