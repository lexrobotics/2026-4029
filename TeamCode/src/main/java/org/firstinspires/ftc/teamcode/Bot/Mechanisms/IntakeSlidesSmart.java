package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Bot.Sensors.SensorSwitch;
import org.firstinspires.ftc.teamcode.Bot.Setup;

public class IntakeSlidesSmart extends IntakeSlides{
    private SensorSwitch sensorSwitch;
    private boolean lastState = false;
    public IntakeSlidesSmart(){super();}

    @Override
    public void init(double target){
        sensorSwitch = new SensorSwitch("IntakeSlidesSwitch", true);
//        lastState = sensorSwitch.getStatus();
        super.init(target);
    }

    @Override
    public void telemetry(){
        super.telemetry();
        Setup.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
    }

    @Override
    public void update(){
        boolean state = sensorSwitch.getStatus();
        Setup.telemetry.addData(name + " SwitchState", sensorSwitch.getStatus());
        if (state && !lastState) {
            reset();
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
