package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeMotor;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.MotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RunToPosMotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.ServoExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism motorMech, servoMech, slideMech, outtakeWrist, outtakeClaw, outtakeSlides, intakeSlides, v4b, winch, intake;
    public Sensors sensors;
    public SensorSwitch outtakeSlidesSwitch, intakeSlidesSwitch;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        telemetry.addLine("BOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        if(hardwareStates.get("IntakeMotor").isEnabled){
            motorMech = new Mechanism("IntakeMotor"); //todo, replace
        } else {
            motorMech = new Mechanism("IntakeMotor");
        }
        if(hardwareStates.get("Winch").isEnabled){
            winch = new Winch();
        } else {
            winch = new Mechanism("Winch");
        }
        if(hardwareStates.get("V4B").isEnabled){
            v4b = new V4B();
        } else {
            v4b = new Mechanism("V4B");
        }
        if(hardwareStates.get("IntakeMotor").isEnabled){
            intake = new IntakeMotor();
        } else {
            intake = new Mechanism("Intake");
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
            outtakeSlides = new OuttakeSlides();
        } else {
            telemetry.addLine("NO SLIDES, OUT");
            outtakeSlides = new Mechanism("OuttakeSlides");
        }
        if(hardwareStates.get("IntakeSlides").isEnabled){
            intakeSlides = new IntakeSlides();
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
    @Override
    public void init(){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        outtakeClaw.init(0);
        outtakeWrist.init(0.5);
        outtakeSlides.init(0);
        intakeSlides.init(0);
        v4b.init(0.5);
        winch.init(0);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        outtakeClaw.init(0);
        outtakeWrist.init(0.5);
        outtakeSlides.init(0);
        intakeSlides.init(0);
        v4b.init(0.5);
        winch.init(0);
        drivetrain.init(startPos);
    }

    @Override
    public void update(){
        drivetrain.update();
        outtakeWrist.update();
        outtakeClaw.update();
        outtakeSlides.update();
        intakeSlides.update();
        v4b.update();
        winch.update();
    }

    @Override
    public void telemetry(){
        motorMech.telemetry();
        servoMech.telemetry();
        slideMech.telemetry();
        drivetrain.telemetry();
        v4b.telemetry();
        winch.telemetry();
    }

    @Override
    public boolean isBusy(){
        return motorMech.isBusy() || servoMech.isBusy() || slideMech.isBusy();
    }
    public void setTargetVectors(double x, double y, double theta){
//        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
