package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
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

import java.util.ArrayList;
import java.util.List;


public class MarkerMoverHw {
    public Servo markerMover;

    double storePos = 0;
    double ejectPos = 1;
    boolean isEjected = false;

    HardwareMap hwMap = null;

    MarkerThread mt;

    public MarkerMoverHw() {

    }

    public void init(HardwareMap ahwmap) {
        hwMap = ahwmap;
        //markerMover = hwMap.get(Servo.class, "markerMover");
        //markerMover.setPosition(storePos);
        //createMarkerThread();
    }

    public void createMarkerThread () {
        mt = new MarkerThread();
    }

    public class MarkerThread extends Thread {
        public MarkerThread() {

        }
        public void run() {
            toggleMarker();
        }
        public void toggleMarker() {
            if (isEjected == true){
                markerMover.setPosition(storePos);
                isEjected = false;
            }
            else {
                markerMover.setPosition(ejectPos);
                isEjected = true;
            }
        }
    }
}
