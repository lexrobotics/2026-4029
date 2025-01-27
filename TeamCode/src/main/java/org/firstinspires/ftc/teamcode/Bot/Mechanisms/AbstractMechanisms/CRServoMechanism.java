package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.teamcode.Bot.Setup;

public abstract class CRServoMechanism extends Mechanism {
    protected CRServo servo;

    public CRServoMechanism(String name) {
        super(name);
    }

    @Override
    public void init(double power) {
        servo = Setup.hardwareMap.get(CRServo.class, name);
        servo.setPower(0);
    }

    @Override
    public void reverse(boolean reverse) {
        if(reverse){
            servo.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    @Override
    public void setVelocity(double v){
        this.velocity = v;
    }

    @Override
    public  void setTarget(double v){this.velocity = v;}

    @Override
    public void update() {
        servo.setPower(velocity);

    }

    @Override
    public double getVelocity(){
        return velocity;
    }
}