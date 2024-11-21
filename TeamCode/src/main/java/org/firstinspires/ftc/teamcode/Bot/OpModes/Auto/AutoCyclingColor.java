package org.firstinspires.ftc.teamcode.Bot.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.AutoSequences;
import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Setup;

public class AutoCyclingColor extends LinearOpMode {
    private Setup setup;
    private Bot bot;
    private IMUStatic imu;
    private AutoSequences autoSequences;
    private ElapsedTime timer;
    @Override
    public void runOpMode(){
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        imu = new IMUStatic();
        autoSequences = new AutoSequences(bot);

        waitForStart();
        timer.reset();
        autoSequences.scoreSpecimen();
        while(autoSequences.getCyclenum() < 3 && timer.seconds()<25){
            autoSequences.approachColorSample();
            autoSequences.pushToHuman();

        }
        autoSequences.parkFromHuman();
    }
}