package org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorColor;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorDistance;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorTouch;

public class Sensors {
    private static SensorColor[] colors;
    private static SensorDistance[] distances;
    private static SensorSwitch[] switches;
    private static SensorTouch[] touchSensors;
    private static boolean standardInvertSwitches = true;

    /**
     * sets the length of each sensor array
     *  @param colorSensorLength is >= 0
     *  @param distanceSensorLength is >= 0
     *  @param switchSensorLength is >= 0
     */
    public Sensors(int colorSensorLength, int distanceSensorLength, int switchSensorLength){
        colors = new SensorColor[colorSensorLength];
        distances = new SensorDistance[distanceSensorLength];
        switches = new SensorSwitch[switchSensorLength];
    }

    public Sensors(int colorSensorLength, int distanceSensorLength, int switchSensorLength, int touchSensorLength, boolean invertSwitches){
        colors = new SensorColor[colorSensorLength];
        distances = new SensorDistance[distanceSensorLength];
        touchSensors = new SensorTouch[touchSensorLength];
        switches = new SensorSwitch[switchSensorLength];
        standardInvertSwitches = invertSwitches;
    }

    /**
     * Adds a sensor to an array sorted by class, grabs it from hardwareMap and places it in the given array position
     * @param type == ColorSensor.class || DistanceSensor.class || DigitalChannel.class
     * @param position is >= 0 && <= to the respective array length - 1
     * @param name
     */
    public void addSensor(Class type, String name, int position){
        if(type == ColorSensor.class){
            colors[position] = new SensorColor(name);
        } else if(type == DistanceSensor.class){
            distances[position] = new SensorDistance(name);
        } else if(type == DigitalChannel.class) {
            switches[position] = new SensorSwitch(name, standardInvertSwitches);
        } else if(type == TouchSensor.class){
            touchSensors[position] = new SensorTouch(name, standardInvertSwitches);
        }
    }

    /**
     * Sets the inverted status of the switch at array position position
     * @param invertSwitch
     * @param position
     */
    public void invertSwitch(boolean invertSwitch, int position){switches[position].invertSwitch(invertSwitch);}

    /**
     * Returns the color seen as a double array containing ARGB
     * @param position
     * @return Double[3], Color
     */
    public double[] getColor(int position){
        return colors[position].getColor();
    }

    /**
     * Returns the distance that the distance sensor at position position reads, in the values supplied
     * @param unit
     * @param position
     * @return double distance
     */
    public double getDistance(DistanceUnit unit, int position){return distances[position].getDistance(unit);}

    /**
     * Returns the limit switch status for the switch at array position position
     * @param position
     * @return boolean value
     */
    public boolean getSwitchStatus(int position){
        return switches[position].getStatus();
    }

    public boolean getTouchStatus(int position){
        return touchSensors[position].getStatus();
    }

    /**
     * Changes the I2c address of the color sensor at array position position to the new address provided
     * @param newAddress
     * @param position
     */
    public void setNewI2cAddressColor(I2cAddr newAddress, int position){colors[position].setI2cAddress(newAddress);}

    /**
     * Toggles the LED for the color sensor at array position array to the value of the boolean isOn
     * @param isOn
     * @param position
     */
    public void toggleColorLight(boolean isOn, int position){
        colors[position].enableLed(isOn);
    }

}
