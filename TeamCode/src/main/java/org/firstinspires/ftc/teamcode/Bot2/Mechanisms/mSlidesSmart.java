package org.firstinspires.ftc.teamcode.Bot2.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Bot2.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot2.Setup;

public class mSlidesSmart extends mOuttakeSlides {
    private SensorSwitch sensorSwitch;
    private boolean lastState = false;
    public mSlidesSmart(){super();}

    @Override
    public void init(double target, HardwareMap hwm){
        super.init(target);
        sensorSwitch = new SensorSwitch("SlidesSwitch", true, hwm);
//        lastState = sensorSwitch.getStatus();
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    @Override
    public void init(double target){
        super.init(target);
        sensorSwitch = new SensorSwitch("SlidesSwitch", true);
//        lastState = sensorSwitch.getStatus();
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void telemetry(){
        super.telemetry();
        Setup.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
        Setup.telemetry.addData(name + " OSlidesPos", motor.getCurrentPosition());
    }

    @Override
    public void update(){
        boolean state = sensorSwitch.getStatus();
        Setup.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
        if (state && !lastState) {
            reset();
        }
        if(state){
            currentPos = 0;
        }
        lastState = state;

        currentPos = motor.getCurrentPosition();
        if (targetPos == 0 && currentPos < margin && !state){
            motor.setTargetPosition((int)(currentPos - margin*1.5));
        }else {
            motor.setTargetPosition((int)targetPos);
        }
//        motor.setTargetPosition((int)targetPos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(velocity);
    }

}
