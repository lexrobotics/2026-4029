package org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot2.Sensors.Vision.Pipelines.Contour;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {

    public enum basePipelines{
        Contour
    }
    private boolean isBlue;
    private String camName;
    private OpenCvPipeline pipeline;
    private HardwareMap hardwareMap;
    private OpenCvWebcam webcam;
    private int cameraMoniterViewId;
    public Camera(String cameraName, String teamColor, OpenCvPipeline startingPipeline, HardwareMap hardwareMap){
        camName = cameraName;
        if(teamColor.toUpperCase().equals("BLUE")){
            isBlue = true;
        } else {
            isBlue = false;
        }
        pipeline = startingPipeline;
        this.hardwareMap = hardwareMap;
        cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, camName), cameraMoniterViewId);
    }
    public Camera(String cameraName, String teamColor, HardwareMap hardwareMap){
        camName = cameraName;
        if(teamColor.toUpperCase().equals("BLUE")){
            isBlue = true;
        } else {
            isBlue = false;
        }
        pipeline = null;
        this.hardwareMap = hardwareMap;
        cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, camName), cameraMoniterViewId);
    }
    public Camera(String teamColor, HardwareMap hardwareMap){
        camName = "webcam";
        if(teamColor.toUpperCase().equals("BLUE")){
            isBlue = true;
        } else {
            isBlue = false;
        }
        pipeline = null;
        this.hardwareMap = hardwareMap;
        cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, camName), cameraMoniterViewId);
    }
    public void openCamera(){
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }
    public void setPipeline(OpenCvPipeline newPipeline){
        webcam.setPipeline(newPipeline);
        pipeline = newPipeline;
    }
    public OpenCvPipeline setPipeline(OpenCvPipeline newPipeline, boolean returnPipeline) {
        OpenCvPipeline temp = null;
        if (returnPipeline) {
            temp = pipeline;
        }
        webcam.setPipeline(newPipeline);
        pipeline = newPipeline;
        return temp;
    }
    public void setPipeline(basePipelines newPipeline){
        switch(newPipeline){
            case Contour:
                Contour temp = new Contour();
                temp.init(isBlue);
                webcam.setPipeline(temp);
                pipeline = temp;
                break;
        }
    }

    public OpenCvPipeline getPipeline(){
        return pipeline;
    }
    public void close(){
        webcam.closeCameraDeviceAsync(() -> {
            webcam.stopStreaming();
        });
    }
    public boolean isTeamBlue(){
        return isBlue;
    }
    public void setCameraViewId(int newCameraMoniterViewId){
        cameraMoniterViewId = newCameraMoniterViewId;
    }
    public String getName(){
        return camName;
    }


}
