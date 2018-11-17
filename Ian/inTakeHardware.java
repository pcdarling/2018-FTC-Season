package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.lang.annotation.Target;

public class inTakeHardware {
    DcMotor[] intakeMotor = new DcMotor[3];
    TouchSensor[] intakeTouch = new TouchSensor[2];
    HardwareMap ITHwMap = null;
    ColorSensor[] intakeColor = new ColorSensor[1];
    Servo[] intakeServo = new Servo[1];
    boolean topPressed = intakeTouch[0].isPressed();
    boolean bottomPressed = intakeTouch[1].isPressed();
    int counts;
    int currentPosition;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;

        for (int i = 0; i < intakeMotor.length; i++) {
            int even = i%3;
            if(even ==  2 || even == 0 ){
                intakeMotor[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
                intakeMotor[i].setDirection(DcMotor.Direction.FORWARD);
                intakeMotor[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                intakeMotor[i].setPower(0);
            }
            else {
                intakeMotor[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
                intakeMotor[i].setDirection(DcMotor.Direction.FORWARD);
                intakeMotor[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                int currentPosition = intakeMotor[i].getCurrentPosition();
                intakeMotor[i].setTargetPosition( counts + currentPosition);
                intakeMotor[i].setPower(0);
            }
        }
        for (int i = 0; i < intakeTouch.length; i++) {
            intakeTouch[i] = ITHwMap.get(TouchSensor.class, "InTTouch" + i);
        }
        // If I need it!!!!!!
        /*for (int i = 0; i < intakeColor.length; i++) {
            intakeColor[i] = ITHwMap.get(ColorSensor.class, "InTColor" + i);

        }
        for (int i = 0; i < intakeServo.length; i++) {
            intakeServo[i] = ITHwMap.get(Servo.class, "InTServo");

        }*/
    }

    public void intakeActivateHeader(double power) {
        intakeMotor[0].setPower(power);
    }

    public void inTakePivot(double power,boolean gameP2DpUp,boolean gameP2Dpdown) {
        // I want to lift and lower the arm
        if (gameP2Dpdown && bottomPressed){
           intakeMotor[2].setPower(0);
       }

       else if (gameP2Dpdown) {
            intakeMotor[2].setPower(-power);
        }

       if (gameP2DpUp && topPressed){
            intakeMotor[2].setPower(0);
       }

       else if(gameP2DpUp) {
            intakeMotor[2].setPower(power);
       }

    }
    public void inTankExtend(double power,double gameP2LSy) {
        //rase and lower arm
        int counts = 10; // going up, I do not know yet what the counts at max range is.
        int inCounts =0; // going down
        while (currentPosition < counts){
            intakeMotor[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeMotor[1].setPower(gameP2LSy);
        }
        while(currentPosition > inCounts){
            intakeMotor[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeMotor[1].setPower(-gameP2LSy);
        }
    }
}