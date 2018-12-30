package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.util.ArrayList;
import java.util.List;

public class CompetitionHardware {

    // Hardware Variables
    DcMotor[] motors = new DcMotor[4];
    public DcMotor liftM;
    public DcMotor theEvan;
    public Servo mrKrabs;
    public Servo markerMover;

    //Gyro Variables
    public BNO055IMU imu;
    public Orientation angles = new Orientation();
    public Acceleration gravity = new Acceleration();
    public double curHeading = angles.firstAngle;


    // Drivetrain coolios variables
    double thresh  = 0.06;
    double encCountsPerRev = 28 * 19.2 * 84 / 100; // electrical * internal * external
    double wheelRadius = 2.25;
    double wheelCircumference = 2 * Math.PI * wheelRadius;
    double countsPerInch = encCountsPerRev / wheelCircumference;
    double robotLength = 14.5;
    double robotWidth = 17.5;
    double robotDiameter = Math.sqrt(Math.pow(robotLength,2)+Math.pow(robotWidth,2));
    double robotCircumference = Math.PI*robotDiameter;

    // Measured variables in inches
    double distanceToSamples = 26.5;
    double distanceFromDepotToCrater = 93;
    double distanceToDepot = 59;
    double distanceToAvoidMineral = 41;

    // The Evan variables
    int theEvanMax = 1000;
    double krabsOpen = 1;
    double krabsClose = 0;
    boolean k_isOpen = false;

    // Team Marker variables
    double storePos;
    double ejectPos;
    boolean tm_isEjected = false;

    // Vuforia Variables
    int location = -1;
    boolean targetSeen = false;
    boolean cameraStatus = false;

    // Vuforia Objects
    VuforiaLocalizer vuforia;
    public VuforiaTrackables targetsRoverRuckus;
    public VuforiaTrackable blueRover;
    public VuforiaTrackable redFootprint;
    public VuforiaTrackable frontCraters;
    public VuforiaTrackable backSpace;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();

    public ElapsedTime runtime = new ElapsedTime();

    // Hardware Map Variables
    HardwareMap hwmap = null;

    // Thread Objects
    LocationThread lt;
    DriveThread dt;
    /*
    ClawThread ct;
    EvanThread et;
    MarkerThread mt;
    */


    public CompetitionHardware(boolean cameraStatus){
        this.cameraStatus = cameraStatus;
    }//Constructor

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;
        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();

        for (int i = 0; i < motors.length; i++){
            motors[i] = hwmap.get(DcMotor.class, "motor" + i);
            motors[i].setDirection(DcMotor.Direction.FORWARD);
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        liftM = hwmap.get(DcMotor.class, "lift");
        liftM.setPower(0);
        liftM.setDirection(DcMotor.Direction.FORWARD);
        liftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        imu = hwmap.get(BNO055IMU.class, "imu");
        imu.initialize(imuParameters);
        imuParameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        imuParameters.loggingEnabled      = true;
        imuParameters.loggingTag          = "IMU";
        imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

       // theEvan = hwmap.get(DcMotor.class, "the evan");
       // mrKrabs = hwmap.get(Servo.class, "mr krabs");
       // markerMover = hwmap.get(Servo.class, "marker");

        if (cameraStatus) {
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
        }
        /* TODO: do the thing again you will need this
        theEvan.setDirection(DcMotorSimple.Direction.FORWARD);
        theEvan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        theEvan.setPower(0);

        markerMover.setPosition(storePos);

        mrKrabs.setPosition(0.5);
        */

        // Initialize threads just in case
        createLocationThread();
        createDriveThread(0,0);
        /*
        createClawThread();
        createMarkerThread();
        createEvanThread(0);
        */
    }

    public void tankcontrolsMovent ( double gamepad1Ry, double gamepad1Ly, boolean turbo){
        if (gamepad1Ly > thresh || gamepad1Ly < thresh) {
            if (turbo) {
                motors[0].setPower(gamepad1Ly);
                motors[2].setPower(gamepad1Ly);
            }
            else{
                motors[0].setPower(gamepad1Ly/2);
                motors[2].setPower(gamepad1Ly/2);
            }
        } else {
            motors[0].setPower(0);
            motors[2].setPower(0);
        }
        if (gamepad1Ry > thresh || gamepad1Ry < thresh) {
            if (turbo) {
                motors[1].setPower(-gamepad1Ry);
                motors[3].setPower(-gamepad1Ry);
            }
            else{
                motors[1].setPower(-gamepad1Ry/2);
                motors[3].setPower(-gamepad1Ry/2);
            }

        } else {
            motors[1].setPower(0);
            motors[3].setPower(0);
        }

    }

    public void FPSmovementByControl ( double gamepad1SpeedND, double gamepad1T, boolean turbo){

        if (Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)) {
            if (gamepad1T > thresh) {
                if(turbo) {
                    motors[0].setPower(gamepad1T);
                    motors[1].setPower(-gamepad1T);
                    motors[2].setPower(gamepad1T);
                    motors[3].setPower(-gamepad1T);
                }
                else {
                    motors[0].setPower(gamepad1T/2);
                    motors[1].setPower(-gamepad1T/2);
                    motors[2].setPower(gamepad1T/2);
                    motors[3].setPower(-gamepad1T/2);
                }
            }
            else if (gamepad1T < -thresh) {
                if(turbo) {
                    motors[0].setPower(gamepad1T);
                    motors[1].setPower(-gamepad1T);
                    motors[2].setPower(gamepad1T);
                    motors[3].setPower(-gamepad1T);
                }
                else {
                    motors[0].setPower(gamepad1T/2);
                    motors[1].setPower(-gamepad1T/2);
                    motors[2].setPower(gamepad1T/2);
                    motors[3].setPower(-gamepad1T/2);
                }

            } else {
                motors[0].setPower(0);
                motors[1].setPower(0);
                motors[2].setPower(0);
                motors[3].setPower(0);
            }

        } else {
            //saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
            if (gamepad1SpeedND > thresh) {
                if(turbo) {
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(-gamepad1SpeedND);
                    motors[3].setPower(-gamepad1SpeedND);
                }
                else {
                    motors[0].setPower(-gamepad1SpeedND/2);
                    motors[1].setPower(-gamepad1SpeedND/2);
                    motors[2].setPower(-gamepad1SpeedND/2);
                    motors[3].setPower(-gamepad1SpeedND/2);
                }
            }
            //saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
            else if (gamepad1SpeedND < -thresh) {
                if(turbo) {
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(-gamepad1SpeedND);
                    motors[3].setPower(-gamepad1SpeedND);
                }
                else{
                    motors[0].setPower(-gamepad1SpeedND/2);
                    motors[1].setPower(-gamepad1SpeedND/2);
                    motors[2].setPower(-gamepad1SpeedND/2);
                    motors[3].setPower(-gamepad1SpeedND/2);
                }
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

    public void createDriveThread(double power ,double inches){
        dt = new DriveThread(power,inches);
    }
    public void createRotateThread(double power, double degrees){
        dt = new DriveThread(power,degrees,true);
    }
    public void createLocationThread() {
        lt = new LocationThread();
    }

    /*
    public void createClawThread() {
        ct = new ClawThread();
    }
    public void createMarkerThread() {
        mt = new MarkerThread();
    }
    */
    /*
    public void createEvanThread(double power) {
       EvanThread et = new EvanThread(power);
       et.start();
    }
    */

    public class DriveThread extends Thread {
        double power;
        double inchesOrDegress;
        int rotation = -1;

        public DriveThread(double power, double inches) {
            this.power = power;
            this.inchesOrDegress = inches;
            this.rotation = 0;
        }

        public DriveThread(double power, double degress, boolean rotation) {
            this.inchesOrDegress = degress;
            this.power = power;
            this.rotation = 1;
        }

        public void run() {
            if (this.rotation == 0) {
                this.driveInInches(this.power, this.inchesOrDegress, false);
            } else if (this.rotation == 1) {
                this.rotateInDegrees(this.power, this.inchesOrDegress);


            }
        }

        public void driveInInches (double power,double inches,boolean rotation) {
            boolean busy = true;
            double rev = inches / wheelCircumference;
            int counts = (int)(rev * encCountsPerRev);
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
                    motors[i].setPower(Math.abs(power)*sign);
                }
            } else {
                for (int i = 0; i < motors.length; i++) {
                    int even = i%2;
                    if (even == 0){
                        if (power > 0) {
                            sign = -1;
                        } else {
                            sign = 1;
                        }
                    }
                    else {
                        if (power > 0) {
                            sign = 1;
                        } else {
                            sign = -1;
                        }
                    }
                    int currentPosition = motors[i].getCurrentPosition();
                    motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motors[i].setTargetPosition(currentPosition + sign * counts);
                    motors[i].setPower(sign*Math.abs(power));
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

        public void rotateInDegrees(double power,double degrees){
            driveInInches(power,degrees/360*robotCircumference,true);
        }
    }

    public class LocationThread extends Thread {

        public LocationThread() {

        }

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


    public class EvanThread extends Thread{
        double power;
        public EvanThread(double power){
            this.power = power;
        }
        public void run(){
            try{
                moveTheEvan(this.power);
            }
            catch (Exception e){
                // sumting wong
            }
        }
        public void moveTheEvan(double power) {
            int encoderCount = theEvan.getCurrentPosition();
            if (power < thresh){
            }
            else if (power > 0 && encoderCount >= theEvanMax){
            }
            else if (power < 0 && encoderCount <= theEvanMax){
            }
            else {
                theEvan.setPower(power);
            }
        }
    }

    public class ClawThread extends Thread {
        public ClawThread() {

        }

        public void run(){
            toggleClaw();
        }
        public void toggleClaw(){
            if (!k_isOpen) {
                mrKrabs.setPosition(krabsOpen);
                k_isOpen = true;
            } else {
                mrKrabs.setPosition(krabsClose);
                k_isOpen = false;
            }
        }
    }

    public class MarkerThread extends Thread {
        public MarkerThread() {

        }
        public void run() {
            toggleMarker();
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
    }


    public void resetLoc(){
        targetSeen = false;
        location = -1;
    }
}

