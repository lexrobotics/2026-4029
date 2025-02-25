package org.firstinspires.ftc.teamcode.Bot2;

import static org.firstinspires.ftc.teamcode.Bot2.Setup.telemetry;

import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.IntakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.OuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot2.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Sensors1;

import java.util.HashMap;

public class Bot implements Robot {
    public Drivetrain drivetrain;
    public Mechanism intakeClaw, intakeRotation, intakeSlides, intakeWrist, outtakeClaw, outtakeSlides, outtakeV4B;
    public Sensors1 sensors;
    public SensorSwitch slidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        sensors = new Sensors1(1,1,0,1,true);

        telemetry.addLine("robot");
        if(hardwareStates.get("drivetrain").isEnabled){
            drivetrain = new Drivetrain();
        } else {
            Log.d("HAI", "DRIVETRAIN NULL");
            drivetrain = null;
        }
        if(hardwareStates.get("IntakeClaw").isEnabled){
            intakeClaw = new IntakeClaw();
        } else {
            intakeClaw = new Mechanism("IntakeClaw");
        }

        if(hardwareStates.get("IntakeRotation").isEnabled){
            intakeRotation = new Turret();
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
            outtakeSlides = new Mechanism("slides");
        }
        if(hardwareStates.get("OuttakeV4B").isEnabled){
            outtakeV4B = new OuttakeV4B();
        } else {
            outtakeV4B = new Mechanism("V4B");
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
        intakeRotation.init(Turret.INIT);
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
        intakeRotation.init(Turret.INIT);
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
        intakeRotation.setTarget(Turret.REST);
        intakeSlides.setTarget(IntakeSlides.REST);
        intakeWrist.setTarget(IntakeWrist.REST);
        outtakeClaw.setTarget(OuttakeClaw.REST);
        outtakeSlides.setTarget(OuttakeSlides.REST);
        outtakeV4B.setTarget(OuttakeV4B.REST);
    }

    public void setTransfer(){
        // prepares transfer position without moving claw
        intakeRotation.setTarget(Turret.TRANSFER);
        intakeSlides.setTarget(IntakeSlides.TRANSFER);
        intakeWrist.setTarget(IntakeWrist.TRANSFER);
        outtakeSlides.setTarget(OuttakeSlides.TRANSFER);
        outtakeV4B.setTarget(OuttakeV4B.TRANSFER);
    }

}
