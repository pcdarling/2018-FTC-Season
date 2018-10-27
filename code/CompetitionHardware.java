package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public class CompetitionHardware {

    VuforiaLocalizer vuforia;

    //The Evan Mechanism
    public DcMotor theEvan;
    public Servo mrKrabs;
    //Team Marker Mechanism
    public Servo markerMover;

    //The Evan variables
    int theEvanMax = 1000;
    double krabsOpen = 1;
    double krabsClose = 0;
    boolean k_isOpen = false;
    //Team Marker variables
    double storePos;
    double ejectPos;
    boolean tm_isEjected = false;
    //vuforia variables
    int location = -1;
    boolean targetSeen = false;

    //Rover ruckus VuMarks
    public VuforiaTrackables targetsRoverRuckus;
    public VuforiaTrackable blueRover;
    public VuforiaTrackable redFootprint;
    public VuforiaTrackable frontCraters;
    public VuforiaTrackable backSpace;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();




    HardwareMap hwmap = null;

    tDetermineLoc locThread = new tDetermineLoc();

    public CompetitionHardware(){}//Constructor

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;

        theEvan = hwmap.get(DcMotor.class, "the evan");
        mrKrabs = hwmap.get(Servo.class, "mr krabs");
        markerMover = hwmap.get(Servo.class, "marker");

        // vuforia targets
        int cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AYOeonr/////AAABmSirnJnvQUnelw5JOBSA3YZQx9wGb22naoaPA/2nWtFxpJiRrDY3NzvoKMTH6zRjy4eYcbHgabgNIwD7OaOLfQM5ZlLV5rmsHwdUkUN1aC8m2nNPlESStk9Ud1pvewjIfQCx1uBqAnRrBQmGFvxnHa6LNbS+eGIVt2/dmTuwUK+WZ5Yn4e0BDO5YlcOiiGEujAmqO+3O1p8a1YM+QHA/Bk7sCnM1hx8pYDT7Qp93jemP3plVOEC3hsEki1xMMBOpp6yip/XR4zX8nFRAT0sZqI7/s50EcuUcXEbPy1Fdv6r0gJZXzsmYm8qA2SLKpinCAd5EvKs6qlaiEFfuFgBplAGW7f6Yg5C1mdjOImQxJhxC";//license key here
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
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

        theEvan.setDirection(DcMotorSimple.Direction.FORWARD);
        theEvan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theEvan.setPower(0);

        markerMover.setPosition(storePos);

        mrKrabs.setPosition(0.5);
    }


    public void toggleMarker(){
        if (tm_isEjected){
            markerMover.setPosition(storePos);
            tm_isEjected = false;
        }
        else{
            markerMover.setPosition(ejectPos);
        }
    }

    public void toggleClaw() {
        if (!k_isOpen) {
            mrKrabs.setPosition(krabsOpen);
            k_isOpen = true;
        } else {
            mrKrabs.setPosition(krabsClose);
            k_isOpen = false;
        }
    }

    public class tDetermineLoc extends Thread {

        public void run(){
            try{
                determineLoc();
            }
            catch(Exception e) {
                location = -100;
            }
        }

        public int determineLoc() {
            if (targetSeen) {
                return location;
            }
            targetsRoverRuckus.activate();
            while (!targetSeen) {
                for (VuforiaTrackable trackable : allTrackables) {
                    if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                        if (trackable.getName() == "Blue-Rover") { // South West
                            location = 0;
                        }
                        if (trackable.getName() == "Back-Space") { // South East
                            location = 1;
                        }
                        if (trackable.getName() == "Front-Craters") { // North West
                            location = 2;
                        }
                        if (trackable.getName() == "Red-Footprint") { // North East
                            location = 3;
                        }
                        targetSeen = true;
                    }
                    if (targetSeen) {
                        break;
                    }
                }
            }
            targetsRoverRuckus.deactivate();

            return location;
        }
    }

    public void resetLoc(){
        targetSeen = false;
        location = -1;
    }
}

