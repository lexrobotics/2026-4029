package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorDistance;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;

public class SmartIntake {
    private SensorDistance distance;
    private Bot bot;
    private ActionSequences actionSequences;
    private ElapsedTime timer;
    private boolean hasDetected = false;
    public double MARGIN = 10;
    public void SmartIntake(Bot bot){
        distance = new SensorDistance("IntakeSensor");
        this.bot = bot;
        actionSequences = new ActionSequences(bot);
    }
    public boolean update(int direction){
        if(direction < 0){
            bot.noodler.setTarget(-1);
        } else if(direction > 0){
            if(distance.getDistance(DistanceUnit.CM) > MARGIN){
                bot.noodler.setTarget(1);
                hasDetected = false;
            } else {
                if(!hasDetected){
                    timer = new ElapsedTime();
                    hasDetected = true;
                }
                if(timer.seconds() < 0.5) {
                    bot.noodler.setTarget(1);
                } else if(timer.seconds() < 1){
                    actionSequences.IntakeRest();
                } else if(timer.seconds() < 1.25){
                    bot.noodler.setTarget(0);
                    return true;
                }
            }
        } else {
            bot.noodler.setTarget(0);
        }
        return false;
    }
}
