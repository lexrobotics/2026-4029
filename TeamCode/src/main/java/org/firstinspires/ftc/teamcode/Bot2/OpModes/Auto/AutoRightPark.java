//package org.firstinspires.ftc.teamcode.Bot2.OpModes.Auto;//package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Bot.AutoSequences;
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Drivetrain.RoadRunnerPaths;
//import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//import org.firstinspires.ftc.teamcode.Bot.Old.StartPositions;
//import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
//
//@Autonomous(name = "AutoRightPark", group = "q1")
//public class AutoRightPark extends LinearOpMode {
//    private Setup setup;
//    private Bot bot;
//    private IMUStatic imu;
//    private ActionSequences actionSequences;
//    private RoadRunnerPaths paths;
//    private ElapsedTime timer;
//    @Override
//    public void runOpMode(){
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        imu = new IMUStatic();
//        bot.init(StartPositions.blueRightDrivePos);
//        actionSequences = new ActionSequences(bot);
//        paths = new RoadRunnerPaths();
//
//        waitForStart();
//        timer.reset();
//
////        actionSequences.
//        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 0));
//        actionSequences.Specimen2();
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 1));
//        actionSequences.OuttakeRest(true);
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//
//        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 2));
//        actionSequences.OuttakeRest(false);
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//
//        bot.drivetrain.setTrajectorySequence(paths.buildBlueRightTrajectory(bot.drivetrain, 3));
//        actionSequences.OuttakeRest(false);
//        while(opModeIsActive() && bot.isBusy()){
//            bot.update();
//        }
//    }
//}