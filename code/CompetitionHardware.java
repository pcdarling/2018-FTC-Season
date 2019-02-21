package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;


public class CompetitionHardware {
    // Hardware Variables
    DrivetrainHardware drivetrain = new DrivetrainHardware();
    IntakeHardware intake = new IntakeHardware();
    EvanHardware evan = new EvanHardware();
    MiscHardware misc = new MiscHardware();
    VuforiaHardware cam = new VuforiaHardware();

    //Gyro Variables
    public BNO055IMU imu;
    public Orientation angles = new Orientation();
    public Acceleration gravity = new Acceleration();
    public int correctHeading = 1; // 1: First Angle; 2: Second Angle; 3: Third Angle

    // Measured variables in inches
    double distanceToSamples = 15; // was 16.35
    double distanceFromDepotToCrater = 78;
    double distanceToDepot = 49;
    double distanceToAvoidMineral = 50;

    // Vuforia Variables
    boolean cameraStatus;

    public ElapsedTime runtime = new ElapsedTime();

    // Hardware Map Variables
    HardwareMap hwmap = null;

    public CompetitionHardware(boolean cameraStatus){
        this.cameraStatus = cameraStatus;
    }//Constructor

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;
        intake.init(hwmap);
        evan.init(hwmap);
        drivetrain.init(hwmap);
        misc.init(hwmap);

        if (this.cameraStatus) {
            // vuforia targets
            cam.init(hwmap);
        }
    }

    public void imuInit(HardwareMap ahwmap) {
        hwmap = ahwmap;
        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();

        imu = hwmap.get(BNO055IMU.class, "imu");
        imuParameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        imuParameters.loggingEnabled      = true;
        imuParameters.loggingTag          = "IMU";
        imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu.initialize(imuParameters);
    }

    public float getHeading() {
        if (correctHeading == 1) {
            return angles.firstAngle;
        } else if (correctHeading == 2) {
            return angles.secondAngle;
        } else { // Using third angle by default
            return angles.thirdAngle;
        }
    }

}