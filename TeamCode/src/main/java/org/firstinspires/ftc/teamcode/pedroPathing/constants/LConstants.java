package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = .000687;
        ThreeWheelConstants.strafeTicksToInches = 0.000523;
        ThreeWheelConstants.turnTicksToInches = .00087;
        ThreeWheelConstants.leftY = 6;
        ThreeWheelConstants.rightY = -6;
        ThreeWheelConstants.strafeX = -4.875;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "leftRear";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.leftEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}






