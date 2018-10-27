package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public class VuforiaHardware {

    public VuforiaLocalizer vuforia;

    public boolean targetVisible;
    int nTrackable = 0;

    //Rover ruckus VuMarks
    public VuforiaTrackables targetsRoverRuckus;
    public VuforiaTrackable blueRover;
    public VuforiaTrackable redFootprint;
    public VuforiaTrackable frontCraters;
    public VuforiaTrackable backSpace;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();

    HardwareMap hwmap = null;

    public VuforiaHardware(){}// Constructor

    public void init(HardwareMap ahwMap){
        hwmap = ahwMap;
        int cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AYOeonr/////AAABmSirnJnvQUnelw5JOBSA3YZQx9wGb22naoaPA/2nWtFxpJiRrDY3NzvoKMTH6zRjy4eYcbHgabgNIwD7OaOLfQM5ZlLV5rmsHwdUkUN1aC8m2nNPlESStk9Ud1pvewjIfQCx1uBqAnRrBQmGFvxnHa6LNbS+eGIVt2/dmTuwUK+WZ5Yn4e0BDO5YlcOiiGEujAmqO+3O1p8a1YM+QHA/Bk7sCnM1hx8pYDT7Qp93jemP3plVOEC3hsEki1xMMBOpp6yip/XR4zX8nFRAT0sZqI7/s50EcuUcXEbPy1Fdv6r0gJZXzsmYm8qA2SLKpinCAd5EvKs6qlaiEFfuFgBplAGW7f6Yg5C1mdjOImQxJhxC";//license key here
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        //Rover ruckus vuMark init
        targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
         blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
         redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
         frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
         backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");
        allTrackables.addAll(targetsRoverRuckus);


         // object detection init


    }
    public int determineLoc(){
        targetVisible = false;
        targetsRoverRuckus.activate();
        while(!targetVisible){
            for (VuforiaTrackable trackable: allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    if (trackable.getName() == "Blue-Rover"){
                        nTrackable = 1;
                    }else if(trackable.getName() == "Red-Footprint"){
                        nTrackable = 2;
                    }else if(trackable.getName() == "Front-Craters"){
                        nTrackable = 3;
                    }else if(trackable.getName() == "Back-Space"){
                        nTrackable = 4;
                    }else{
                        //do nothing
                    }
                    targetVisible = true;
                }
            }
        }
        return nTrackable;
    }
}
