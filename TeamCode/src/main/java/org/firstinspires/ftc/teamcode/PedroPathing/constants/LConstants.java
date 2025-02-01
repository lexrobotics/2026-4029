package org.firstinspires.ftc.teamcode.PedroPathing.constants;


import com.pedropathing.localization.constants.ThreeWheelConstants;

import org.firstinspires.ftc.teamcode.PedroPathing.localization.Encoder;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.ThreeWheelLocalizer;


public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.00052; //lower it is, lower the telemetry value will be
        ThreeWheelConstants.strafeTicksToInches = 0.00052;
        ThreeWheelConstants.turnTicksToInches = 0.00052;
        ThreeWheelConstants.leftY = 6.125;
        ThreeWheelConstants.rightY = -6.125;
        ThreeWheelConstants.strafeX = -4.4;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightFront";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




