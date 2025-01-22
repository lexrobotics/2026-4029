//package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Bot.AutoSequences;
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//
//public class AutoParkingYellow extends LinearOpMode {
//    private Setup setup;
//    private Bot bot;
//    private IMUStatic imu;
//    private AutoSequences autoSequences;
//    @Override
//    public void runOpMode(){
//        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
//        bot = new Bot(Setup.mechStates, Setup.sensorStates);
//        imu = new IMUStatic();
//        autoSequences = new AutoSequences(bot);
//        waitForStart();
//        autoSequences.parkFromStart();
//
//    }
//}
