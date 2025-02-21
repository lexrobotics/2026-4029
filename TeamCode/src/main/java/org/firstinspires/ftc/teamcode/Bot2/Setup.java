package org.firstinspires.ftc.teamcode.Bot2;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Bot2.InitStates.HardwareStates;

import java.util.HashMap;
import java.util.List;

/*
class to set up:
- hardware
- telemetry
- dashboard
- team
- type of opmode
 */

public class Setup {
    public enum OpModeType {
        AUTO,
        TELEOP
    }

    public enum Team{ //TODO: Set Team (i.e. Red Right) here
        Q1,
        Q2,
        Q3,
        Q4
    }
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static LinearOpMode opMode;
    public static OpModeType opModeType;
    public static FtcDashboard dashboard;

    public static HashMap<String, HardwareStates> mechStates = new HashMap<>();
    public static HashMap<String, HardwareStates> sensorStates = new HashMap<>();

    public Setup(HardwareMap hmap, Telemetry tel, boolean enableDash, LinearOpMode op, OpModeType opType, Team team){
        hardwareMap = hmap;
        telemetry = tel;
        opMode = op;
        opModeType = opType;
        if (enableDash){
            configureDashboard();
        }else{
            dashboard = null;
        }
        configureTelemetry();
        configureBulkReads();
        addMechanisms();
        addSensors();
    }

    private void configureDashboard(){
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        dashboard.updateConfig();
    }
    private void configureTelemetry(){
        telemetry.setMsTransmissionInterval(50);
    }
    private void configureBulkReads() {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    private void addMechanisms(){
        mechStates.put("IntakeClaw", new HardwareStates(true));
        mechStates.put("IntakeRotation", new HardwareStates(true));
        mechStates.put("IntakeSlides", new HardwareStates(true));
        mechStates.put("IntakeWrist", new HardwareStates(true));
        mechStates.put("OuttakeClaw", new HardwareStates(true));
        mechStates.put("OuttakeSlides", new HardwareStates(true));
        mechStates.put("OuttakeV4B", new HardwareStates(true));
        mechStates.put("drivetrain", new HardwareStates(true));

    }

    private void addSensors(){
        sensorStates.put("webcam", new HardwareStates(false));
        sensorStates.put("SlidesSwitch", new HardwareStates(true));
        sensorStates.put("IntakeCDSensor", new HardwareStates(true));
        sensorStates.put("IntakeTouchSensor", new HardwareStates(true));
    }

    public void disableMechanism(String mechanismName){
        mechStates.put(mechanismName, new HardwareStates(false));
    }

    public void disableSensor(String sensorName){
        sensorStates.put(sensorName, new HardwareStates(false));
    }
}
