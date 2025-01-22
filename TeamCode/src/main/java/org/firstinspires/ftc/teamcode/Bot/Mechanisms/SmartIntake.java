package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorColorDistance;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorTouch;
import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;


public class SmartIntake {
    private ActionSequences actions;
    private boolean isBlue;
    private SensorColorDistance colorSensor;
    private SensorTouch touchSensor;
    public enum Color{
        YELLOW,
        RED,
        BLUE
    }
    public SmartIntake(ActionSequences actionSequences, boolean isBlue){
        actions = actionSequences;
        this.isBlue = isBlue;

    }

    /***
     *
     * @param bePicky
     * @return 0 for yellow,
     */
    public Color intake(boolean bePicky){
        Color colore;
        while (true) {
            actions.intake(1);
            colore = assignColor(colorSensor.getColor());
            if(!bePicky && colore == Color.YELLOW){
                break;
            } else if(isBlue && colore == Color.BLUE){
               break;
            } else if(!isBlue && colore == Color.RED){
                break;
            }
            actions.intake(-1);
        }
        return colore;
    }
    public Color assignColor(double[] colors){
        if(1.5*colors[2] > colors[1] && colors[2] > colors[3]) {
            return Color.YELLOW;
        } else if(colors[1] > colors[2] && colors[1] > colors[3]){
            return Color.RED;
        } else{
            return Color.BLUE;
        }
//        else if(colors[3] > colors[1] && colors[3] > colors[2]){
//            return Color.BLUE;
//        }
    }
}
