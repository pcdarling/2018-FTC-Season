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

    //drivetrain co
    DcMotor[] motors = new DcMotor[4];
    //The Evan Mechanism
    public DcMotor theEvan;
    public Servo mrKrabs;
    //Team Marker Mechanism
    public Servo markerMover;

    //Drivetrain coolios variables
    double thresh  = 0.06;
    double encCountsPerRev = 20 * 20 * 84 / 100; // electrical * internal * external
    double wheelRadius = 2.25;
    double wheelCircumference = 2 * Math.PI * wheelRadius;
    double robotLength = 14.5;
    double robotWidth = 17.5;
    double robotDiameter = Math.sqrt(Math.pow(robotLength,2)+Math.pow(robotWidth,2));
    double robotCircumference = Math.PI*robotDiameter;
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

        for (int i = 0; i < motors.length; i++){
            motors[i] = hwmap.get(DcMotor.class, "motor" + i);
            motors[i].setDirection(DcMotor.Direction.FORWARD);
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

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

    public void driveInInches (double power,double inches,boolean rotation) {
        boolean busy = true;
        double percentOfWheel = inches / wheelCircumference;
        int counts = (int)(percentOfWheel * encCountsPerRev);
        int sign = 1;

        if (rotation) {
            for (int i = 0; i < motors.length; i++) {
                if (power > 0) {
                    sign = -1;
                } else {
                    sign = 1;
                }
                int currentPosition = motors[i].getCurrentPosition();
                motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors[i].setTargetPosition(currentPosition + sign * counts);
                motors[i].setPower(power);
            }
        } else {
            for (int i = 0; i < motors.length; i++) {
                int even = i%2;
                if(even == 0){
                    if (power > 0) {
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                }
                else {
                    if (power > 0) {
                        sign = -1;
                    } else {
                        sign = 1;
                    }
                }

                int currentPosition = motors[i].getCurrentPosition();
                motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors[i].setTargetPosition(currentPosition + sign * counts);
                motors[i].setPower(power);
            }
        }

        while (busy) {
            for (int i = 0; i < motors.length; i++) {
                busy = busy && motors[i].isBusy();
            }
        }

        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void driveInInches (double power,double inches) {
        boolean busy = true;
        double percentOfWheel = inches / wheelCircumference;
        int counts = (int)(percentOfWheel * encCountsPerRev);
        int sign = 1;


        for (int i = 0; i < motors.length; i++) {
            int even = i%2;
            if(even == 0){
                if (power > 0) {
                    sign = 1;
                } else {
                    sign = -1;
                }
            }
            else {
                if (power > 0) {
                    sign = -1;
                } else {
                    sign = 1;
                }
            }

            int currentPosition = motors[i].getCurrentPosition();
            motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[i].setTargetPosition(currentPosition + sign * counts);
            motors[i].setPower(power);
        }


        while (busy) {
            for (int i = 0; i < motors.length; i++) {
                busy = busy && motors[i].isBusy();
            }
        }

        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void rotateInDegrees(double power,double degrees){
        driveInInches(power,degrees/360*robotCircumference,true);
    }

    public void tankcontrolsMovent ( double gamepad1Ry, double gamepad1Ly){
        if (gamepad1Ly > thresh || gamepad1Ly < thresh) {
            motors[0].setPower(gamepad1Ly);
            motors[2].setPower(gamepad1Ly);
        } else {
            motors[0].setPower(0);
            motors[2].setPower(0);
        }
        if (gamepad1Ry > thresh || gamepad1Ry < thresh) {
            motors[1].setPower(-gamepad1Ry);
            motors[3].setPower(-gamepad1Ry);

        } else {
            motors[1].setPower(0);
            motors[3].setPower(0);
        }

    }

    public void FPSmovementByControl ( double gamepad1SpeedND, double gamepad1T){

        if (Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)) {
            if (gamepad1T > thresh) {
                motors[0].setPower(-gamepad1T);
                motors[1].setPower(gamepad1T);
                motors[2].setPower(-gamepad1T);
                motors[3].setPower(gamepad1T);
            } else if (gamepad1T < -thresh) {
                motors[0].setPower(-gamepad1T);
                motors[1].setPower(gamepad1T);
                motors[2].setPower(-gamepad1T);
                motors[3].setPower(gamepad1T);

            } else {
                motors[0].setPower(0);
                motors[1].setPower(0);
                motors[2].setPower(0);
                motors[3].setPower(0);
            }

        } else {
            //saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
            if (gamepad1SpeedND > thresh) {
                motors[0].setPower(-gamepad1SpeedND);
                motors[1].setPower(-gamepad1SpeedND);
                motors[2].setPower(gamepad1SpeedND);
                motors[3].setPower(gamepad1SpeedND);
            }
            //saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
            else if (gamepad1SpeedND < -thresh) {
                motors[0].setPower(-gamepad1SpeedND);
                motors[1].setPower(-gamepad1SpeedND);
                motors[2].setPower(gamepad1SpeedND);
                motors[3].setPower(gamepad1SpeedND);
            }
            //saying if the Motor[i] is not doing anything than have the power of the motor to 0.
            else {
                motors[0].setPower(0);
                motors[1].setPower(0);
                motors[2].setPower(0);
                motors[3].setPower(0);

            }
        }
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

