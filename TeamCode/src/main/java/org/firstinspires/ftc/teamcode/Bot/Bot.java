package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wheels;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism arm, wrist, slides, wheels, fingers, winch;
    public Sensors sensors;
    public SensorSwitch outtakeSlidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        telemetry.addLine("robot");
        if(hardwareStates.get("drivetrain").isEnabled){
            drivetrain = new Drivetrain();
        } else {
            drivetrain = null;
        }
        if(hardwareStates.get("Wheels").isEnabled){
            wheels = new Wheels(); //todo, replace
        } else {
            wheels = new Mechanism("Wheels");
        }

        if(hardwareStates.get("Wrist").isEnabled){
            wrist = new Wrist();
        } else {
            wrist = new Mechanism("Wrist");
        }
        if(hardwareStates.get("Slides").isEnabled){
            slides = new SlidesSmart();
        } else {
            telemetry.addLine("NO SLIDES");
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
        if(hardwareStates.get("Winch").isEnabled){
            winch = new Winch();
        } else {
            winch = new Mechanism("Winch");
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
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        wheels.init(StartPositions.wheelsPos);
        wrist.init(StartPositions.wristPos);
        arm.init(StartPositions.armPos);
        fingers.init(StartPositions.fingersPos);
        slides.init(StartPositions.slidesPos);
        winch.init(StartPositions.winchPos);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        wheels.init(StartPositions.wheelsPos);
        wrist.init(StartPositions.wristPos);
        arm.init(StartPositions.armPos);
        fingers.init(StartPositions.fingersPos);
        slides.init(StartPositions.slidesPos);
        winch.init(StartPositions.winchPos);
    }

    @Override
    public void update(){
        wheels.update();
        wrist.update();
        arm.update();
        fingers.update();
        slides.update();
        winch.update();
        drivetrain.update();
    }

    @Override
    public void telemetry(){
        wheels.telemetry();
        wrist.telemetry();
        arm.telemetry();
        fingers.telemetry();
        slides.telemetry();
        winch.telemetry();
        drivetrain.telemetry();
    }

    @Override
    public boolean isBusy(){
        return wheels.isBusy() ||
                wrist.isBusy() ||
                arm.isBusy() ||
                fingers.isBusy()||
                slides.isBusy() ||
                winch.isBusy() ||
                drivetrain.isBusy();
    }
    public void setTargetVectors(double x, double y, double theta){
//        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
