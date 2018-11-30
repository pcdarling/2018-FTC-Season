package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot.MID_SERVO;

public class MarkerHardware {
    // Attributes
    public Servo markerMover;

    public HardwareMap hwMap;

    double storePos = 0;
    double ejectPos = 1;
    boolean isEjected = false;

    public MarkerHardware() {
        // Constructor remains empty
    }

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        markerMover = hwMap.get(Servo.class, "markerMover");
        markerMover.setPosition(storePos);
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