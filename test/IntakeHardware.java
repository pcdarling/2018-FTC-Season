package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class IntakeHardware {
    DcMotor[] ITMotors = new DcMotor[3];
    //TouchSensor InTouchS = null;
    //DigitalChannel magSwitch = null;
    Servo ITServo = null;

    HardwareMap ITHwMap = null;
    // IntakeServo
    double openBox = 1;
    double closeBox = 0;
    double halfwayOpenBox = 0.5;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;
        ITServo =ITHwMap.get(Servo.class,"ITServo");
        //magSwitch = ITHwMap.get(DigitalChannel.class, "magSwitch");
        //InTouchS = ITHwMap.get(TouchSensor.class, "InTTouch");

        for (int i = 0; i < ITMotors.length; i++) {
            ITMotors[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
            ITMotors[i].setDirection(DcMotor.Direction.FORWARD);
            ITMotors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            ITMotors[i].setPower(0);
            ITMotors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }

    public void intakeSuccc(double power, boolean rB, boolean leftBumper) {
        if (rB) {
            ITMotors[0].setPower(power);
        } else if (leftBumper) {
            ITMotors[0].setPower(-power);
        } else {
            ITMotors[0].setPower(0);
        }
    }

    // Don't need "x" or "a" but too lazy to rewrite
    public void moveIntake(double power, boolean x, boolean a) {
        //move the arm forward and backward
        if (x) {
            ITMotors[2].setPower(power);
        }
        else if (a) {
            ITMotors[2].setPower(-power);
        } else {
            ITMotors[2].setPower(0);
        }

    }

    // Don't need "y" or "b" but too lazy to rewrite
    public void intakeArmRaiseLower(double power, boolean y, boolean b) {
        //rase and lower arm
        if (y) {
            ITMotors[1].setPower(power);
        }
        else if (b) {
            ITMotors[1].setPower(-power);
        }

        else {
            ITMotors[1].setPower(0);
        }
    }
}