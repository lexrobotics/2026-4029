package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Noodler;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlidesSmart;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeWrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.V4B;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism intakeArm, outtakeWrist, outtakeClaw, outtakeSlides, intakeSlides, v4b, winch, intake, noodler;
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
        if(hardwareStates.get("Noodler").isEnabled){
            noodler = new Noodler(); //todo, replace
        } else {
            noodler = new Mechanism("Noodler");
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
            telemetry.addLine("NO SLIDES, OUT");
            outtakeSlides = new Mechanism("OuttakeSlides");
        }
        if(hardwareStates.get("IntakeSlides").isEnabled){
            intakeSlides = new IntakeSlidesSmart();
        } else {
            intakeSlides = new Mechanism("IntakeSlides");
        } if(hardwareStates.get("IntakeArm").isEnabled){
            intakeArm = new IntakeArm();
        } else {
            intakeArm = new Mechanism("IntakeArm");
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
        winch.init(StartPositions.winchPos);
        v4b.init(StartPositions.outtakeV4BPos);
        outtakeClaw.init(StartPositions.outtakeClawPos);
        outtakeWrist.init(StartPositions.outtakeWristPos);
        outtakeSlides.init(StartPositions.outtakeSlidesPos);
        intakeSlides.init(StartPositions.intakeSlidesPos);
        intakeArm.init(StartPositions.intakeArmPos);
        drivetrain.init(new Pose2d(0, 0, 0));
    }
    public void init(Pose2d startPos){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        noodler.init(0);
        winch.init(StartPositions.winchPos);
        v4b.init(StartPositions.outtakeV4BPos);
        outtakeClaw.init(StartPositions.outtakeClawPos);
        outtakeWrist.init(StartPositions.outtakeWristPos);
        outtakeSlides.init(StartPositions.outtakeSlidesPos);
        intakeSlides.init(StartPositions.intakeSlidesPos);
        intakeSlides.reverse(true);
        intakeArm.init(StartPositions.intakeArmPos);
        drivetrain.init(startPos);
    }

    @Override
    public void update(){
        noodler.update();
        winch.update();
        v4b.update();
        outtakeClaw.update();
        outtakeWrist.update();
        outtakeSlides.update();
        intakeSlides.update();
        intakeArm.update();
        drivetrain.update();
    }

    @Override
    public void telemetry(){
        noodler.telemetry();
        winch.telemetry();
        v4b.telemetry();
        outtakeClaw.telemetry();
        outtakeWrist.telemetry();
        outtakeSlides.telemetry();
        intakeSlides.telemetry();
        drivetrain.telemetry();
        intakeArm.telemetry();
    }

    @Override
    public boolean isBusy(){
        return noodler.isBusy() || winch.isBusy() || v4b.isBusy() || outtakeClaw.isBusy() || outtakeWrist.isBusy() || outtakeSlides.isBusy() || intakeSlides.isBusy() || drivetrain.isBusy() || intakeArm.isBusy() ;
    }
    public void setTargetVectors(double x, double y, double theta){
//        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
