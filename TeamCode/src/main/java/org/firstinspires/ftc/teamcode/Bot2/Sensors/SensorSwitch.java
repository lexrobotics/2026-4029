package org.firstinspires.ftc.teamcode.Bot2.Sensors;



import static org.firstinspires.ftc.teamcode.Bot2.Setup.hardwareMap;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SensorSwitch {
    private DigitalChannel sensor;
    private boolean inverted;
    public SensorSwitch(String name, boolean invert){
        sensor = hardwareMap.get(DigitalChannel.class, name);
        inverted = invert;
    }
    public SensorSwitch(String name, boolean invert, HardwareMap hwm){
        sensor = hwm.get(DigitalChannel.class, name);
        inverted = invert;
    }

    public void invertSwitch(boolean invertSwitch){
        inverted = invertSwitch;
    }

    public boolean getStatus(){
        return inverted == !sensor.getState();
    }
}
