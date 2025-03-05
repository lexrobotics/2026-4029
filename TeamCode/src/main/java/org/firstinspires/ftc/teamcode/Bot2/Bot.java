package org.firstinspires.ftc.teamcode.Bot2;

import static org.firstinspires.ftc.teamcode.Bot2.Setup.telemetry;

import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTurret;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mLinkage;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntakeWrist;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttakeV4B;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot2.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Sensors1;

import java.util.HashMap;

public class Bot implements Robot {
    public Drivetrain drivetrain;
    public Mechanism intakeClaw, intakeWrist, turret, linkage, outtakeClaw, outtakeSlides, outtakeV4B, outtakeWrist;
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
            intakeClaw = new mIntakeClaw();
        } else {
            intakeClaw = new Mechanism("IntakeClaw");
        }

        if(hardwareStates.get("IntakeWrist").isEnabled){
            intakeWrist = new mIntakeWrist();
        } else {
            intakeWrist = new Mechanism("IntakeWrist");
        }
        if(hardwareStates.get("OuttakeClaw").isEnabled){
            outtakeClaw = new mOuttakeClaw();
        } else {
            outtakeClaw = new Mechanism("OuttakeClaw");
        }
        if(hardwareStates.get("OuttakeSlides").isEnabled){
            outtakeSlides = new mOuttakeSlides();
        } else {
            outtakeSlides = new Mechanism("slides");
        }
        if(hardwareStates.get("OuttakeV4B").isEnabled){
            outtakeV4B = new mOuttakeV4B();
        } else {
            outtakeV4B = new Mechanism("V4B");
        }

        if(hardwareStates.get("Turret").isEnabled){
            turret = new mTurret();
        } else {
            turret = new Mechanism("Turret");
        }

        if(hardwareStates.get("Linkage").isEnabled){
            linkage = new mLinkage();
        } else {
            linkage = new Mechanism("Linkage");
        }

        if(hardwareStates.get("OuttakeWrist").isEnabled){
            outtakeWrist = new mOuttakeWrist();
        } else {
            outtakeWrist = new Mechanism("OuttakeWrist");
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
        intakeClaw.init(mIntakeClaw.INIT);
        linkage.init(mLinkage.INIT);
        turret.init(mTurret.INIT);
        intakeWrist.init(mIntakeWrist.INIT);
        outtakeClaw.init(mOuttakeClaw.INIT);
        outtakeSlides.init(mOuttakeSlides.INIT);
        outtakeV4B.init(mOuttakeV4B.INIT);
        outtakeWrist.init(mOuttakeWrist.INIT);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        intakeClaw.init(mIntakeClaw.INIT);
        linkage.init(mLinkage.INIT);
        turret.init(mTurret.INIT);
        intakeWrist.init(mIntakeWrist.INIT);
        outtakeClaw.init(mOuttakeClaw.INIT);
        outtakeSlides.init(mOuttakeSlides.INIT);
        outtakeV4B.init(mOuttakeV4B.INIT);
        outtakeWrist.init(mOuttakeWrist.INIT);
        drivetrain.init(startPos);
    }

    @Override
    public void update(){
        intakeClaw.update();
        linkage.update();
        turret.update();
        intakeWrist.update();
        outtakeClaw.update();
        outtakeSlides.update();
        outtakeV4B.update();
        outtakeWrist.update();
    }

    @Override
    public void telemetry(){
        intakeClaw.telemetry();
        linkage.telemetry();
        turret.telemetry();
        intakeWrist.telemetry();
        outtakeClaw.telemetry();
        outtakeSlides.telemetry();
        outtakeV4B.telemetry();
        outtakeWrist.telemetry();
    }

    @Override
    public boolean isBusy(){
        return intakeClaw.isBusy() || turret.isBusy() ||  intakeWrist.isBusy() || linkage.isBusy() || drivetrain.isBusy() || outtakeClaw.isBusy() || outtakeSlides.isBusy() || outtakeV4B.isBusy() || outtakeWrist.isBusy();
    }

    public void setRest(){
        intakeClaw.setTarget(mIntakeClaw.REST);
        turret.setTarget(mTurret.REST);
        intakeWrist.setTarget(mIntakeWrist.REST);
        linkage.setTarget(mLinkage.REST);
        outtakeClaw.setTarget(mOuttakeClaw.REST);
        outtakeSlides.setTarget(mOuttakeSlides.REST);
        outtakeV4B.setTarget(mOuttakeV4B.REST);
        outtakeWrist.setTarget(mOuttakeWrist.REST);
    }

    public void setTransfer(){
        // prepares transfer position without moving claw
        turret.setTarget(mTurret.TRANSFER);
        intakeWrist.setTarget(mIntakeWrist.TRANSFER);
        linkage.setTarget(mLinkage.TRANSFER);
        outtakeSlides.setTarget(mOuttakeSlides.TRANSFER);
        outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
        outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
    }

}
