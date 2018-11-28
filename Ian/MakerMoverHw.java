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


public class MakerMoverHw {
    public Servo markerMover;
    double storePos;
    double ejectPos;
    boolean tm_isEjected = false;

    MarkerThread mt;

    HardwareMap hwmap;

    public MakerMoverHw() {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;
        // mrKrabs = hwmap.get(Servo.class, "mr krabs");
        //markerMover.setPosition(storePos);
        // mrKrabs.setPosition(0.5);

        public void createMarkerThread () {
            mt = new MarkerThread();
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
}
