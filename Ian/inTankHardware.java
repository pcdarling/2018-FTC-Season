package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// I need 3 motor 3 touch senser
//i might need 1 color senser and 1 servo

public class inTankHardware {
    DcMotor[] ITMotors = new DcMotor[2];
    TouchSensor[] InTouchS = new TouchSensor[3];
    ColorSensor[] InTColor = new ColorSensor[1];
    Servo[] INTServo = new Servo[1];
    DcMotor[] ITMotersTwo = new DcMotor[1];
    HardwareMap ITHwMap = null;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;

        for (int i = 0; i < ITMotors.length; i++) {
            ITMotors[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
            ITMotors[i].setDirection(DcMotor.Direction.FORWARD);
            ITMotors[i].setPower(0);
        }
        for (int i = 0; i < InTouchS.length; i++) {
            InTouchS[i] = ITHwMap.get(TouchSensor.class, "InTTouch" + i);
        }
        // If I need it!!!!!!
        for (int i = 0; i < InTColor.length; i++) {
            InTColor[i] = ITHwMap.get(ColorSensor.class, "InTColor" + i);

        }
        for (int i = 0; i < INTServo.length; i++) {
            INTServo[i] = ITHwMap.get(Servo.class, "InTServo");

        }
        for(int i =0 ; i < ITMotersTwo.length; i++){
            ITMotersTwo[i] = ITHwMap.get(DcMotor.class, "ITMotor2" + i);
            ITMotersTwo[i].setDirection(DcMotor.Direction.FORWARD);
            ITMotersTwo[i].setPower(0);
            ITMotersTwo[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            ITMotersTwo[i].getCurrentPosition();
        }
    }

    public void inTankHeader(double power) {
        ITMotors[0].setPower(power);
    }

    public void inTankArmLiftLower(double power,boolean x,boolean a) {
//        // I want to lift and lower the arm
        if(x){
            ITMotors[1].setPower(power);
            if (a){
                ITMotors[1].setPower(-power);
            }
            if(InTouchS[0].isPressed()) {
                ITMotors[1].setPower(0);
            }
            else if (InTouchS[1].isPressed()){
                ITMotors[1].setPower(0);
            }
        }

    }
public void inTankArmRiseLower (double power,boolean y, boolean b) {
    //rase and lower arm
    if (y) {
        ITMotersTwo[0].setPower(power);
        if (b) {
            ITMotersTwo[0].setPower(-power);
            }
        }
    }
}