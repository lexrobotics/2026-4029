package org.firstinspires.ftc.teamcode.Bot.Sensors;

import static org.firstinspires.ftc.teamcode.Bot.Old.Setup1.hardwareMap;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public class LightStrip {
    private RevBlinkinLedDriver strip;
    private RevBlinkinLedDriver.BlinkinPattern pattern;
    public
    LightStrip(String name, RevBlinkinLedDriver.BlinkinPattern startingPattern){
        strip = hardwareMap.get(RevBlinkinLedDriver.class, name);
        strip.setPattern(startingPattern);
        pattern = startingPattern;
    }
    public RevBlinkinLedDriver.BlinkinPattern setPattern(RevBlinkinLedDriver.BlinkinPattern newPattern){
        strip.setPattern(newPattern);
        RevBlinkinLedDriver.BlinkinPattern temp;
        temp = pattern;
        pattern = newPattern;
        return temp;
    }
    public RevBlinkinLedDriver.BlinkinPattern getPattern(){
        return pattern;
    }

}
