package org.firstinspires.ftc.teamcode.Bot2.Sensors;

import static org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1.hardwareMap;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class SensorTouch {
    private TouchSensor sensor;
    private boolean inverted;
    public SensorTouch(String name, boolean invert){
        sensor = hardwareMap.get(TouchSensor.class, name);
        inverted = invert;
    }
    public SensorTouch(String name, boolean invert, HardwareMap map){
        sensor = map.get(TouchSensor.class, name);
        inverted = invert;
    }

    public void invertSwitch(boolean invertSwitch){
        inverted = invertSwitch;
    }

    public boolean getStatus(){
        return inverted == !sensor.isPressed();
    }

    @NonNull
    @Override
    public String   toString() {
        return "Name: " + sensor.getDeviceName() + " Status: " + sensor.isPressed() + " Connection status: " + sensor.getConnectionInfo();
    }
}
