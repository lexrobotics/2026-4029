package org.firstinspires.ftc.teamcode.Bot.Sensors;

import static org.firstinspires.ftc.teamcode.Bot.Setup.hardwareMap;

import com.qualcomm.robotcore.hardware.DigitalChannel;


public class SensorSwitch {
    private DigitalChannel sensor;
    private boolean inverted;
    public SensorSwitch(String name, boolean invert){
        sensor = hardwareMap.get(DigitalChannel.class, name);
        inverted = invert;
    }

    public void invertSwitch(boolean invertSwitch){
        inverted = invertSwitch;
    }

    public boolean getStatus(){
        return inverted == !sensor.getState();
    }
}
