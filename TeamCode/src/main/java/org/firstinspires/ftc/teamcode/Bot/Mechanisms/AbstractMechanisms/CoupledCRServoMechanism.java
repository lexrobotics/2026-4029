package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Bot.Setup;

public class CoupledCRServoMechanism extends Mechanism{

    private CRServo servoLeft;
    private CRServo servoRight;

    public CoupledCRServoMechanism(String name){super(name);}

    public void init(){
        servoLeft = Setup.hardwareMap.get(CRServo.class, name + "Left");
        servoRight = Setup.hardwareMap.get(CRServo.class, name + "Right");
        servoRight.setDirection(DcMotorSimple.Direction.REVERSE);
        servoLeft.setPower(0);
        servoRight.setPower(0);
    }

    public void update(){
        servoLeft.setPower(velocity);
        servoRight.setPower(velocity);
    }

    public void setTarget(double v){
        this.velocity = v;
    }

    public void setVelocity(double v){
        setTarget(v);
    }

    public double getVelocity(){
        return velocity;
    }

    public void reverse(){
        servoLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        servoRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

}
