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

public class ClawHW {
    public Servo mrKrabs;
    double krabsOpen = 1;
    double krabsClose = 0;
    boolean k_isOpen = false;

    // mrKrabs = hwmap.get(Servo.class, "mr krabs");
    //ClawThread ct;

    HardwareMap hwmap;

    public ClawHW() {
    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;
        // mrKrabs = hwmap.get(Servo.class, "mr krabs");
        mrKrabs.setPosition(0.5);
        //  createClawThread();
    }

    public class ClawThread extends Thread {
        public ClawThread() {

        }
    }


    public void run() {
        toggleClaw();
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
}
