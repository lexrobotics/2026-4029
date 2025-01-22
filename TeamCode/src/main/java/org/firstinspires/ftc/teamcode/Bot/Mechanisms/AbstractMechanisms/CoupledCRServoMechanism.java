package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Bot.Setup;

public class CoupledCRServoMechanism extends Mechanism{
    protected CRServo servoLeft, servoRight;

    public CoupledCRServoMechanism(String name) {
        super(name);
        velocity = 1;
    }

    public void init(double power) {
        servoLeft = Setup.hardwareMap.get(CRServo.class, name + "Left");
        servoRight = Setup.hardwareMap.get(CRServo.class, name + "Right");
        servoLeft.setPower(0);
        servoRight.setPower(0);
    }

    public void reverse(boolean isLeftReversed, boolean isRightReversed){
        if(isLeftReversed){
            servoLeft.setDirection(DcMotorEx.Direction.REVERSE);
        }
        if(isRightReversed){
            servoRight.setDirection(DcMotorEx.Direction.REVERSE);
        }
    }

    public void setTarget(double v){
        this.velocity = v;
    }

    public void update() {
        servoLeft.setPower(velocity);
        servoRight.setPower(velocity);
    }

    public double getTarget(){
        return velocity;
    }

}
