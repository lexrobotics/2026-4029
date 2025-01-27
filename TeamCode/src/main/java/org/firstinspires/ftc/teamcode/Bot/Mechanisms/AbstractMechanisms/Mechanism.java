package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Setup;
public class Mechanism {
    protected String name;
    public double targetPos = 0;
    protected double currentPos = 0;
    protected double velocity = 0;
    protected ElapsedTime timer;
    protected double zero = 0;
    protected double timeLimit;
    protected boolean initialized = false;

    public Mechanism(String name) {
        this.name = name;
        timer = new ElapsedTime();
    }
    public void init(double target){
        setTarget(target);
        initialized = true;
    }
    public String getName(){
        return name;
    }
    public void init(double target, HardwareMap hwm){
        setTarget(target);
        initialized = true;
    }

    public void init(double target, DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        setTarget(target);
    }
    public void setTarget(double target) {targetPos = target + zero;}
    public double getCurrentPosition() {
        return currentPos - zero;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public void startTimer(double time) {
        timeLimit = time;
        timer.reset();
    }
    public void reset(){
        zero = getCurrentPosition();
    }
    public void telemetry(){
        Setup.telemetry.addData(name + " currentPos",currentPos - zero);
        Setup.telemetry.addData(name + " targetPos",targetPos);
        Setup.telemetry.addData(name + " velocity",velocity);
        Setup.telemetry.addData(name + " timeLimit",timeLimit);
        Setup.telemetry.addData(name + " isBusy",isBusy());
    }
    public void update(){
        currentPos = targetPos;
    }

    public boolean isBusy(){
        return !(currentPos==targetPos);
    }

    public void reverse(boolean reverse){

    }
    public void reverse(boolean reverseLeft, boolean reverseRight){

    }

    @NonNull
    public String toString(){
        return name;
    }

    public boolean wasInitialized(){
        return initialized;
    }

    public double getVelocity(){
        return 0;
    }
}
