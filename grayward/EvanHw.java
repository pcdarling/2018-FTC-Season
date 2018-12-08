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
public class EvanHw {
    public DcMotor theEvan;

    double thresh  = 0.06;
    int theEvanMax = 1000;
    double krabsOpen = 1;
    double krabsClose = 0;
    boolean k_isOpen = false;

    HardwareMap hwmap;

    EvanThread et;

    public EvanHw() {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;
        //theEvan.setDirection(DcMotorSimple.Direction.FORWARD);
        //theEvan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //theEvan.setPower(0);
        //createEvanThread(0);
    }

    public void createEvanThread(double power) {
        et = new EvanThread(power);
    }

    public class EvanThread extends Thread {
        double power;

        public EvanThread(double power) {
            this.power = power;
        }

        public void run() {
            try {
                moveTheEvan(this.power);
            } catch (Exception e) {
                // sumting wong
            }
        }

        public void moveTheEvan(double power) {
            int encoderCount = theEvan.getCurrentPosition();
            if (power < thresh) {
            } else if (power > 0 && encoderCount >= theEvanMax) {
            } else if (power < 0 && encoderCount <= theEvanMax) {
            } else {
                theEvan.setPower(power);
            }
        }
    }
}