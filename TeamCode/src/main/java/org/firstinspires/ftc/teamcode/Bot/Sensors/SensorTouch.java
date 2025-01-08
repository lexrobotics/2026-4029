package org.firstinspires.ftc.teamcode.Bot.Sensors;

import static org.firstinspires.ftc.teamcode.Bot.Setup.hardwareMap;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class SensorTouch {
    private TouchSensor sensor;
    private boolean inverted;
    public SensorTouch(String name, boolean invert){
        sensor = hardwareMap.get(TouchSensor.class, name);
        inverted = invert;
    }

    public void invertSwitch(boolean invertSwitch){
        inverted = invertSwitch;
    }

    public boolean getStatus(){
        return inverted == !sensor.isPressed();
    }
}
