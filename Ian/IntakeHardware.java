package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class IntakeHardware {
    DcMotor[] ITMotors = new DcMotor[3];
    //TouchSensor InTouchS = null;
    DigitalChannel magSwitch = null;


    HardwareMap ITHwMap = null;
    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;

        magSwitch = ITHwMap.get(DigitalChannel.class,"magSwitch");
        // InTouchS = ITHwMap.get(TouchSensor.class, "InTTouch");

        for (int i = 0; i < ITMotors.length; i++) {
            int even = i%2;
            if(even == 0){
                ITMotors[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
                ITMotors[i].setDirection(DcMotor.Direction.FORWARD);
                ITMotors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                ITMotors[i].setPower(0);
            }
            else {
                ITMotors[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
                ITMotors[i].setDirection(DcMotor.Direction.FORWARD);
                ITMotors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ITMotors[i].setPower(0);
            }
            ITMotors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
    public void intakeSuccc(double power, boolean rB,boolean leftBumper) {
        if(rB) {
            ITMotors[0].setPower(power);
        }
        else if (leftBumper){
            ITMotors[0].setPower(-power);
        }
        else {
            ITMotors[0].setPower(0);
        }
    }

    // Don't need "x" or "a" but too lazy to rewrite
    public void moveIntake(double power, boolean x, boolean a) {
        //move the arm forward and backward
        if(x){
            ITMotors[2].setPower(power);
        }
        if(a){
            ITMotors[2].setPower(-power);
        }
        else {
            ITMotors[2].setPower(0);
        }

    }

    // Don't need "y" or "b" but too lazy to rewrite
    public void intakeArmRaiseLower(double power, boolean y, boolean b) {
        //rase and lower arm
        if(y){
            ITMotors[1].setPower(power);
        }
        if(b){ ITMotors[1].setPower(-power);
        }
        else{
            ITMotors[1].setPower(0);
        }
    }
}




// ColorSensor[] InTColor = new ColorSensor[1];
// Servo[] INTServo = new Servo[1];
/* for (int i = 0; i < InTColor.length; i++) {
            InTColor[i] = ITHwMap.get(ColorSensor.class, "InTColor" + i);
        }
        for (int i = 0; i < INTServo.length; i++) {
            INTServo[i] = ITHwMap.get(Servo.class, "InTServo");
        }
}*/