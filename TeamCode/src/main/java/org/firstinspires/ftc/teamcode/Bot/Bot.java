package org.firstinspires.ftc.teamcode.Bot;



import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.*;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.*;

import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;

public class Bot implements Robot {
    public Drivetrain drivetrain;
    public Mechanism intakeClaw, intakeRotation, intakeSlides, intakeWrist, outtakeClaw, outtakeSlides, outtakeV4B;
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
        if(hardwareStates.get("IntakeClaw").isEnabled){
            intakeClaw = new IntakeClaw();
        } else {
            intakeClaw = new Mechanism("IntakeClaw");
        }

        if(hardwareStates.get("IntakeRotation").isEnabled){
            intakeRotation = new IntakeRotation();
        } else {
            intakeRotation = new Mechanism("IntakeRotation");
        }

        if(hardwareStates.get("IntakeSlides").isEnabled){
            intakeSlides = new IntakeSlides();
        } else {
            intakeSlides = new Mechanism("IntakeSlides");
        }
        if(hardwareStates.get("IntakeWrist").isEnabled){
            intakeWrist = new IntakeWrist();
        } else {
            intakeWrist = new Mechanism("IntakeWrist");
        }
        if(hardwareStates.get("OuttakeClaw").isEnabled){
            outtakeClaw = new OuttakeClaw();
        } else {
            outtakeClaw = new Mechanism("OuttakeClaw");
        }
        if(hardwareStates.get("OuttakeSlides").isEnabled){
            outtakeSlides = new OuttakeSlides();
        } else {
            outtakeSlides = new Mechanism("OuttakeSlides");
        }
        if(hardwareStates.get("OuttakeV4B").isEnabled){
            outtakeV4B = new OuttakeV4B();
        } else {
            outtakeV4B = new Mechanism("OuttakeV4B");
        }
//        if(sensorStates.get("IntakeCDSensor").isEnabled){
//            sensors.addSensor(ColorSensor.class, "IntakeCDSensor", 0);
//            sensors.addSensor(DistanceSensor.class, "IntakeCDSensor", 0);
//        }
//        if(sensorStates.get("IntakeTouchSensor").isEnabled){
//            sensors.addSensor(TouchSensor.class, "IntakeTouchSensor", 0);
//        }

        init();
    }

    public void initDrivetrain(Pose2d pose){
        if(drivetrain != null) {
            drivetrain.init(pose);
        } else {
        }
    }

    @Override
    public void init(){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        intakeClaw.init(IntakeClaw.INIT);
        intakeRotation.init(IntakeRotation.INIT);
        intakeSlides.init(IntakeSlides.INIT);
        intakeWrist.init(IntakeWrist.INIT);
        outtakeClaw.init(OuttakeClaw.INIT);
        outtakeSlides.init(OuttakeSlides.INIT);
        outtakeV4B.init(OuttakeV4B.INIT);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        intakeClaw.init(IntakeClaw.INIT);
        intakeRotation.init(IntakeRotation.INIT);
        intakeSlides.init(IntakeSlides.INIT);
        intakeWrist.init(IntakeWrist.INIT);
        outtakeClaw.init(OuttakeClaw.INIT);
        outtakeSlides.init(OuttakeSlides.INIT);
        outtakeV4B.init(OuttakeV4B.INIT);
        drivetrain.init(startPos);
    }

    @Override
    public void update(){
        intakeClaw.update();
        intakeRotation.update();
        intakeSlides.update();
        intakeWrist.update();
        outtakeClaw.update();
        outtakeSlides.update();
        outtakeV4B.update();
        drivetrain.update();
    }

    @Override
    public void telemetry(){
        intakeClaw.telemetry();
        intakeRotation.telemetry();
        intakeSlides.telemetry();
        intakeWrist.telemetry();
        outtakeClaw.telemetry();
        outtakeSlides.telemetry();
        outtakeV4B.telemetry();
        drivetrain.telemetry();
    }

    @Override
    public boolean isBusy(){
        return intakeClaw.isBusy() || intakeRotation.isBusy() || intakeSlides.isBusy() || intakeWrist.isBusy() || drivetrain.isBusy() || outtakeClaw.isBusy() || outtakeSlides.isBusy() || outtakeV4B.isBusy();
    }

    public void setRest(){
        intakeClaw.setTarget(IntakeClaw.REST);
        intakeRotation.setTarget(IntakeRotation.REST);
        intakeSlides.setTarget(IntakeSlides.REST);
        intakeWrist.setTarget(IntakeWrist.REST);
        outtakeClaw.setTarget(OuttakeClaw.REST);
        outtakeSlides.setTarget(OuttakeSlides.REST);
        outtakeV4B.setTarget(OuttakeV4B.REST);
    }

    public void setTransfer(){
        // prepares transfer position without moving claw
        intakeRotation.setTarget(IntakeRotation.TRANSFER);
        intakeSlides.setTarget(IntakeSlides.TRANSFER);
        intakeWrist.setTarget(IntakeWrist.TRANSFER);
        outtakeSlides.setTarget(OuttakeSlides.TRANSFER);
        outtakeV4B.setTarget(OuttakeV4B.TRANSFER);
    }

}
