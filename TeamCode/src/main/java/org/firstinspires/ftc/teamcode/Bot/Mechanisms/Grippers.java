package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import static org.firstinspires.ftc.teamcode.Bot.Setup.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.CoupledCRServoMechanism;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorColorDistance;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorTouch;
import org.firstinspires.ftc.teamcode.Bot.Setup;

@Config
public class Grippers extends CoupledCRServoMechanism {
    public Grippers() {
        super("Grippers");
    }

    public static double INIT = 0;
    public static final double INTAKE = 1;
    public static final double OUTTAKE = -1;
    public static final double STOP = 0;
    public static final double FORWARD_MAX = 1;
    public static final double MIN_SPEED = 0;
    public static final double REVERSE_MAX = -1;

}