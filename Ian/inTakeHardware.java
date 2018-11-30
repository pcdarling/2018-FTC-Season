package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class inTakeHardware {
    DcMotor[] intakeMotor = new DcMotor[3];
    TouchSensor[] intakeTouch = new TouchSensor[2];
    HardwareMap hwMap = null;
    ColorSensor intakeColor =  null;
    Servo intakeServo = null;

    IntakeThread it;

    boolean liftUp = intakeTouch[0].isPressed();
    boolean liftDown = intakeTouch[1].isPressed();
    int liftCounts = 1000;
    double doorClosed = 1;
    double doorOpened = 0;
    double suckPower = 0.8;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        hwMap = ahwMap;

        for (int i = 0; i < intakeMotor.length; i++) {
            intakeMotor[i] = hwMap.get(DcMotor.class, "ITMotor" + i);
            if(i == intakeMotor.length -1) {
                intakeMotor[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else {
                intakeMotor[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            intakeMotor[i].setDirection(DcMotor.Direction.FORWARD);
            intakeMotor[i].setPower(0);
        }
        for (int i = 0; i < intakeTouch.length; i++) {
            intakeTouch[i] = hwMap.get(TouchSensor.class, "ITTouch" + i);
        }
        // If I need it!!!!!!
        /*for (int i = 0; i < intakeColor.length; i++) {
            intakeColor[i] = hwMap.get(ColorSensor.class, "ITColor" + i);

        }
        intakeServo = hwMap.get(Servo.class, "ITServo");
        intakeServo.setPosition(doorClosed);*/
    }
    public void createIntakeThread(double power){
        it = new IntakeThread(power);
    }
    public class IntakeThread extends Thread {
        double power;

        public IntakeThread(double power) {
            this.power = power;

        }

        public void run() {
            intakeExtend(this.power);
        }

        public void intakeExtend(double power) {
            //raise and lower arm
            int currentPosition = intakeMotor[1].getCurrentPosition();
            if (power > 0) {
                intakeMotor[1].setTargetPosition(currentPosition + liftCounts);
            }
            else {
                intakeMotor[1].setTargetPosition(0);
            }
            intakeMotor[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeMotor[1].setPower(power);
            while(intakeMotor[1].isBusy()){
                //busy waiting
            }
            intakeMotor[1].setPower(0);
        }
    }
    public void suck(boolean on) {
        if (on) {
            intakeMotor[0].setPower(suckPower);
        } else {
            intakeMotor[0].setPower(0);
        }
    }
    public void intakePivot(double power) {

        // I want to lift and lower the arm
        if (power < 0 && liftDown){
           intakeMotor[2].setPower(0);
        }
        else if (power > 0 && liftUp){
            intakeMotor[2].setPower(0);
        }
        else {
            intakeMotor[2].setPower(power);
        }

    }

}