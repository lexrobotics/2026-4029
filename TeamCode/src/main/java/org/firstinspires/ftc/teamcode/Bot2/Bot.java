package org.firstinspires.ftc.teamcode.Bot2;

import static org.firstinspires.ftc.teamcode.Bot2.Setup.telemetry;

import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mIntake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mOuttake;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mTransfer;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot2.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.Sensors1;
import org.firstinspires.ftc.teamcode.Bot2.Mechanisms.mCarousel;

import java.util.HashMap;

public class Bot implements Robot {
    public Drivetrain drivetrain;
    public Mechanism carousel, transfer, intake, outtake;
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

        if(hardwareStates.get("Carousel").isEnabled){
            carousel = new mCarousel();
        } else {
            carousel = new Mechanism("Carousel");
        }

        if(hardwareStates.get("Transfer").isEnabled){
            transfer = new mTransfer();
        } else {
            transfer = new Mechanism("Transfer");
        }

        if(hardwareStates.get("Intake").isEnabled){
            intake = new mIntake();
        } else {
            intake = new Mechanism("Intake");
        }

        if(hardwareStates.get("Outtake").isEnabled){
            outtake = new mOuttake();
        } else {
            outtake = new Mechanism("Outtake");
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
        carousel.init(mCarousel.INIT);
        transfer.init(mTransfer.INIT);
        intake.init(mIntake.INIT);
        outtake.init(mOuttake.INIT);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        carousel.init(mCarousel.INIT);
        transfer.init(mTransfer.INIT);
        intake.init(mIntake.INIT);
        outtake.init(mOuttake.INIT);
        drivetrain.init(startPos);
    }

    @Override
    public void update(){
        carousel.update();
        transfer.update();
        intake.update();
        outtake.update();
        drivetrain.update();
    }

    @Override
    public void telemetry(){
        carousel.telemetry();
        transfer.telemetry();
        intake.telemetry();
        outtake.telemetry();
        drivetrain.telemetry();

    }

    @Override
    public boolean isBusy(){
        return carousel.isBusy() || transfer.isBusy() || intake.isBusy() || outtake.isBusy() || drivetrain.isBusy();
    }

    public void setRest(){
        carousel.setTarget(mCarousel.REST);
        transfer.setTarget(mTransfer.REST);
        intake.setTarget(mIntake.REST);
        outtake.setTarget(mOuttake.REST);

    }

//    public void setTransfer(){
//        // prepares transfer position without moving claw
//        turret.setTarget(mTurret.TRANSFER);
//        intakeWrist.setTarget(mIntakeWrist.TRANSFER);
//        linkage.setTarget(mLinkage.TRANSFER);
//        outtakeSlides.setTarget(mOuttakeSlides.TRANSFER);
//        outtakeV4B.setTarget(mOuttakeV4B.TRANSFER);
//        outtakeWrist.setTarget(mOuttakeWrist.TRANSFER);
//    }

}
