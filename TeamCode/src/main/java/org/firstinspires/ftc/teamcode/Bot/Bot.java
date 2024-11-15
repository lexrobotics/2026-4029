package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.MotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RunToPosMotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.ServoExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartClaw;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism motorMech, servoMech, slideMech, outtakeWrist, outtakeClaw, outtakeSlides, intakeSlides;
    public Sensors sensors;
    public SensorSwitch outtakeSlidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        telemetry.addLine("BOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        if(hardwareStates.get("intakeMotor").isEnabled){
            motorMech = new Mechanism("IntakeMotor"); //todo, replace
        } else {
            motorMech = new Mechanism("IntakeMotor");
        }

        if(hardwareStates.get("OuttakeClaw").isEnabled){
            outtakeClaw = new OuttakeClaw();
        } else {
            outtakeClaw = new Mechanism("OuttakeClaw");
        }
        if(hardwareStates.get("OuttakeWrist").isEnabled){
            outtakeWrist = new OuttakeWrist();
        } else {
            outtakeWrist = new Mechanism("OuttakeWrist");
        }
        if(hardwareStates.get("OuttakeSlides").isEnabled){
            outtakeSlides = new OuttakeSlidesSmart();
        } else {
            telemetry.addLine("NO SLIDES, OUT, LOL");
            outtakeSlides = new Mechanism("OuttakeSlides");
        }
        if(hardwareStates.get("IntakeSlides").isEnabled){
            intakeSlides = new IntakeSlidesSmart();
        } else {
            intakeSlides = new Mechanism("IntakeSlides");
        }
        if(hardwareStates.get("drivetrain").isEnabled){
            drivetrain = new Drivetrain();
        } else {
            drivetrain = null;
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
    public void drivetrainUpdate(boolean usePeP){
        drivetrain.update(usePeP);
    }
    @Override
    public void init(){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
//        motorMech.init(0);
        initDrivetrain(new Pose2d());
        outtakeClaw.init(0);
        outtakeWrist.init(0);
        outtakeSlides.init(0);
        intakeSlides.init(0);
//        servoMech.init(0);
//        slideMech.init(0);
//        drivetrain.init();
    }
    public void init(Pose2d startPos){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
//        motorMech.init(0);
        initDrivetrain(startPos);
//        servoMech.init(0);
//        slideMech.init(0);
//        drivetrain.init();
    }

    @Override
    public void update(){
        /*
        Updates mechanisms
        */
//        motorMech.update();
//        servoMech.update();
//        slideMech.update();
//        drivetrain.update();
        outtakeWrist.update();
        outtakeClaw.update();
        outtakeSlides.update();
        intakeSlides.update();
    }

    @Override
    public void telemetry(){
        motorMech.telemetry();
        servoMech.telemetry();
        slideMech.telemetry();
        drivetrain.telemetry();
    }

    @Override
    public boolean isBusy(){
        return motorMech.isBusy() || servoMech.isBusy() || slideMech.isBusy();
    }
    public void setTargetVectors(double x, double y, double theta){
        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
