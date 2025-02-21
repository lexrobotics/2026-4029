package org.firstinspires.ftc.teamcode.DEADBot.Mechanisms.DEADBot2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.DEADBot.DEADSensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.DEADBot.Old.Setup1;

public class SlidesSmart extends Slides {
    private SensorSwitch sensorSwitch;
    private boolean lastState = false;
    public SlidesSmart(){super();}

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
        Setup1.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
        Setup1.telemetry.addData(name + " OSlidesPos", motor.getCurrentPosition());
    }

    @Override
    public void update(){
        boolean state = sensorSwitch.getStatus();
        Setup1.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
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
