package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.RightGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.SlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Bot2.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism arm, wrist, slides, fingers, leftGripper, rightGripper;
    public Sensors sensors;
    public SensorSwitch slidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        sensors = new Sensors(1,1,0,1,true);


        telemetry.addLine("robot");
        if(hardwareStates.get("drivetrain").isEnabled){
            drivetrain = new Drivetrain();
        } else {
            drivetrain = null;
        }
        if(hardwareStates.get("LeftGripper").isEnabled){
            leftGripper = new LeftGripper();
        } else {
            leftGripper = new Mechanism("LeftGripper");
        }

        if(hardwareStates.get("RightGripper").isEnabled){
            rightGripper = new RightGripper();
        } else {
            rightGripper = new Mechanism("RightGripper");
        }

        if(hardwareStates.get("Wrist").isEnabled){
            wrist = new Wrist();
        } else {
            wrist = new Mechanism("Wrist");
        }
        if(hardwareStates.get("Slides").isEnabled){
            slides = new SlidesSmart();
        } else {
            telemetry.addLine("NO SLIDES, OUT");
            slides = new Mechanism("Slides");
        }
        if(hardwareStates.get("Arm").isEnabled){
            arm = new Arm();
        } else {
            arm = new Mechanism("Arm");
        }
        if(hardwareStates.get("Fingers").isEnabled){
            fingers = new Fingers();
        } else {
            fingers = new Mechanism("Fingers");
        }
        if(sensorStates.get("IntakeCDSensor").isEnabled){
            sensors.addSensor(ColorSensor.class, "IntakeCDSensor", 0);
            sensors.addSensor(DistanceSensor.class, "IntakeCDSensor", 0);
        }
        if(sensorStates.get("IntakeTouchSensor").isEnabled){
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
        wrist.init(StartPositions.wristPos);
        leftGripper.init(StartPositions.grippersPos);
        rightGripper.init(StartPositions.grippersPos);
        arm.init(StartPositions.armPos);
        slides.init(StartPositions.slidesPos);
        drivetrain.init(new Pose2d(0, 0, 0));
        fingers.init(Fingers.INIT);
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        leftGripper.init(StartPositions.grippersPos);
        rightGripper.init(StartPositions.grippersPos);
        wrist.init(StartPositions.wristPos);
        slides.init(StartPositions.slidesPos);
        drivetrain.init(startPos);
        fingers.init(StartPositions.fingersPos);
        arm.init(StartPositions.armPos);
    }

    @Override
    public void update(){
        leftGripper.update();
        rightGripper.update();
        wrist.update();
        slides.update();
        drivetrain.update();
        fingers.update();
        arm.update();
    }

    @Override
    public void telemetry(){
        leftGripper.telemetry();
        rightGripper.telemetry();
        wrist.telemetry();
        slides.telemetry();
        drivetrain.telemetry();
        fingers.telemetry();
        arm.telemetry();
    }

    @Override
    public boolean isBusy(){
        return leftGripper.isBusy() || rightGripper.isBusy() || wrist.isBusy() || slides.isBusy() || drivetrain.isBusy() || fingers.isBusy() || arm.isBusy();
    }

    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
