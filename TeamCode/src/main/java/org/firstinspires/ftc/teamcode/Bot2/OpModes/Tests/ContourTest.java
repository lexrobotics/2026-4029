package org.firstinspires.ftc.teamcode.Bot2.OpModes.Tests;//package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
//import org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Pipelines.Contour;
//import org.firstinspires.ftc.teamcode.Bot.Setup;
//import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
//import org.opencv.core.Point;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvWebcam;
//@TeleOp(name = "Contour vision test (XY)", group = "1")
//public class ContourTest extends LinearOpMode {
//    private Point centerPoint;
//
//    private final double turnSpeed = 0.15;
//    private final double moveSpeed = 0.5;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Contour contour = new Contour();
//        contour.init(false);
//        int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMoniterViewId);
////        webcam.setMillisecondsPermissionTimeout(2500);
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//
//            }
//        });
//        webcam.setPipeline(contour);
//        Drivetrain drivetrain = new Drivetrain();
//        Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
//        drivetrain.init(new Pose());
//        while(opModeInInit()){
//            moveBot(contour, drivetrain);
//        }
//        webcam.closeCameraDeviceAsync(() -> {
//            webcam.stopStreaming();
//        });
//    }
//
//    private void moveBot(Contour contour, Drivetrain drivetrain){
//        centerPoint = contour.getCenterPoint();
//        int distanceRot = (int)centerPoint.x - 1280/2;
//        int distance = (int)centerPoint.y - 720;
//        if(centerPoint.x == -1 && centerPoint.y == -1){
//            drivetrain.setTeleOpTargets(0,0,0);
//        } else { // if(Math.abs(distanceRot) > 20)
//            drivetrain.localMovement(0, (distance/720.0) * moveSpeed, -turnSpeed * Math.signum(distanceRot) - (Math.abs(distanceRot/640.0) * turnSpeed/2)); //-distanceRot / 640.0 * turnSpeed
//            telemetry.addLine("Center Point: " + -distanceRot / 640.0);
//            telemetry.update();
//        }
////        else if(Math.abs(distance) > 10){
////            drivetrain.localMovement(0, -distance / 360.0 * moveSpeed);
////            telemetry.addLine("FORWARD/BACK MOVEMENT");
////        }
//        drivetrain.update(false);
//
//
//    }
//}
