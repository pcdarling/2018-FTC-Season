package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MiscHardware {
    public Servo markerMover;
    public Servo phoneServo;

    // Team Marker variables
    double storePos = 0.85;
    double ejectPos = 0.42;
    boolean tm_isEjected = false;

    // Phone Servo Variables
    double phoneOutPos = 0.15;
    double phoneMidPos = 0.2;
    double phoneInPos = 0.45;
    double hidePhonePos = 0.65;

    // Hardware Map
    HardwareMap hwMap;

    // function vars that can't work within function
    boolean min;

    // Threads
    MarkerThread mt;

    public MiscHardware() {

    }

    public void init(HardwareMap ahwmap) {
        this.hwMap = ahwmap;

        // Marker Deployer init
        markerMover = this.hwMap.get(Servo.class, "marker");
        markerMover.setPosition(storePos);

        // Phone Servo init
        phoneServo = this.hwMap.get(Servo.class, "phone_servo");
        phoneServo.setPosition(phoneMidPos);

        createMarkerThread();
    }

    public void createMarkerThread() {
        mt = new MarkerThread();
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
                tm_isEjected = true;
            }
        }
    }

    // TODO: THIS IS NOT USED. IF YOU WANNA TEST SAMPLING THE MINERALS, THEN PUT THIS SOMEWHERE IN AUTO
    public void scanPhone() {
        if (phoneServo.getPosition() <= phoneOutPos){
            min = true;
        }
        else if (phoneServo.getPosition() >= phoneInPos){
            min = false;
        }
        if (min){
            phoneServo.setPosition(phoneServo.getPosition() +0.002);
        }
        else{
            phoneServo.setPosition(phoneServo.getPosition() -0.001);
        }
    }
}
