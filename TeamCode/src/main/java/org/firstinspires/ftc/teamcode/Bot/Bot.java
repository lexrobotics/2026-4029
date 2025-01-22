package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Grippers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism arm, wrist, outtakeSlides, noodler, fingers, grippers;
    public Sensors sensors;
    public SensorSwitch outtakeSlidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        sensors = new Sensors(1,1,0,1,false);


        telemetry.addLine("robot");
        if(hardwareStates.get("drivetrain").isEnabled){
            drivetrain = new Drivetrain();
        } else {
            drivetrain = null;
        }
        if(hardwareStates.get("Noodler").isEnabled){
            noodler = new Grippers(); //todo, replace
        } else {
            noodler = new Mechanism("Noodler");
        }

        if(hardwareStates.get("Wrist").isEnabled){
            wrist = new Wrist();
        } else {
            wrist = new Mechanism("Wrist");
        }
        if(hardwareStates.get("OuttakeSlides").isEnabled){
            outtakeSlides = new OuttakeSlidesSmart();
        } else {
            telemetry.addLine("NO SLIDES, OUT");
            outtakeSlides = new Mechanism("OuttakeSlides");
        }
        if(hardwareStates.get("Arm").isEnabled){
            arm = new Arm();
        } else {
            arm = new Mechanism("Arm");
        }
        if(hardwareStates.get("Fingers").isEnabled){
            fingers = new Fingers("Fingers");
        } else {
            fingers = new Mechanism("Fingers");
        }
        if(hardwareStates.get("Grippers").isEnabled){
            grippers = new Grippers();
        } else {
            grippers = new Mechanism("Grippers");
        }

        if(hardwareStates.get("IntakeCDSensor").isEnabled){
            sensors.addSensor(ColorSensor.class, "IntakeCDSensor", 0);
            sensors.addSensor(DistanceSensor.class, "IntakeCDSensor", 0);
        }
        if(hardwareStates.get("IntakeTouchSensor").isEnabled){
            sensors.addSensor(TouchSensor.class, "IntakeTouchSensor", 0);
        }

        init();

    }
    public void initDrivetrain(Pose2d pose){
        if(drivetrain != null) {
            drivetrain.init(pose);
        } else {
//            telemetry.addLine("");
        }
    }
    @Override
    public void init(){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        noodler.init(0);
        wrist.init(StartPositions.outtakeWristPos);
        grippers.init(StartPositions.grippersPos);
        wrist.init(StartPositions.wristPos);

        outtakeSlides.init(StartPositions.outtakeSlidesPos);
        drivetrain.init(new Pose2d(0, 0, 0));
        fingers.init(Fingers.INIT);
        grippers.init(Grippers.INIT);
//        grippers.setup();
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        noodler.init(0);
        wrist.init(StartPositions.outtakeWristPos);
        outtakeSlides.init(StartPositions.outtakeSlidesPos);
//        outtakeSlides.reverse(true);
        drivetrain.init(startPos);
        fingers.init(Fingers.INIT);
    }

    @Override
    public void update(){
        noodler.update();
        wrist.update();
        outtakeSlides.update();
        drivetrain.update();
        fingers.update();
    }

    @Override
    public void telemetry(){
        noodler.telemetry();
        wrist.telemetry();
        outtakeSlides.telemetry();
        drivetrain.telemetry();
        fingers.telemetry();
    }

    @Override
    public boolean isBusy(){
        return noodler.isBusy() || wrist.isBusy() || outtakeSlides.isBusy() || drivetrain.isBusy() || fingers.isBusy();
    }
    public void setTargetVectors(double x, double y, double theta){
//        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
