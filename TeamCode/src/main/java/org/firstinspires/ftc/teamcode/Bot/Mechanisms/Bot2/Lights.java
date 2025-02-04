package org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.Bot.Sensors.LightStrip;

public class Lights {
    private static LightStrip[] strips;

    /**
     * Sets the array length for the LED strip container array
     * @param stripCounter is greater than 0
     */
    public static void init(int stripCounter){strips = new LightStrip[stripCounter];}

    /**
     * Adds a strip with the starting pattern being BLACK
     * @param index >= 0
     * @param name is a valid name in the hardwareMap
     */
    public static void addStrip(int index, String name){strips[index] = new LightStrip(name, RevBlinkinLedDriver.BlinkinPattern.BLACK);}

    /**
     * Adds a trip with the corresponding starting pattern
     * @param index >= 0
     * @param name is a valid name in the hardwareMap
     * @param startingPattern is a valid BlinkinPattern
     */
    public static void addStrip(int index, String name, RevBlinkinLedDriver.BlinkinPattern startingPattern){strips[index] = new LightStrip(name, startingPattern);}

    public static void removeStrip(int index){strips[index] = null;}

    /**
     * Sets the pattern of the indicated strip, and returns the previous pattern
     * @param index >= 0
     * @param newPattern is a valid BlinkinPattern
     * @return BlinkinPattern previous
     */
    public static RevBlinkinLedDriver.BlinkinPattern setPattern(int index, RevBlinkinLedDriver.BlinkinPattern newPattern){return strips[index].setPattern(newPattern);}

    public static RevBlinkinLedDriver.BlinkinPattern getPattern(int index){return strips[index].getPattern();}
}
