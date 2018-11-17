package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// I need 3 motor 3 touch senser
//i might need 1 color senser and 1 servo

public class inTankHardware {
    DcMotor[] intakeMotor = new DcMotor[2];
    TouchSensor[] intakeTouch = new TouchSensor[3];
    ColorSensor[] intakeColor = new ColorSensor[1];
    Servo[] intakeServo = new Servo[1];
    DcMotor[] intakeMotor1 = new DcMotor[1];
    HardwareMap ITHwMap = null;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;

        for (int i = 0; i < intakeMotor.length; i++) {
            intakeMotor[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
            intakeMotor[i].setDirection(DcMotor.Direction.FORWARD);
            intakeMotor[i].setPower(0);
        }
        for (int i = 0; i < intakeTouch.length; i++) {
            intakeTouch[i] = ITHwMap.get(TouchSensor.class, "InTTouch" + i);
        }
        // If I need it!!!!!!
        for (int i = 0; i < intakeColor.length; i++) {
            intakeColor[i] = ITHwMap.get(ColorSensor.class, "InTColor" + i);

        }
        for (int i = 0; i < intakeServo.length; i++) {
            intakeServo[i] = ITHwMap.get(Servo.class, "InTServo");

        }
        for(int i =0 ; i < ITMotersTwo.length; i++){
            intakeMotor1[i] = ITHwMap.get(DcMotor.class, "intake motor" + i);
            intakeMotor1[i].setDirection(DcMotor.Direction.FORWARD);
            intakeMotor[i].setPower(0);
            intakeMotor[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeMotor[i].getCurrentPosition();
        }
    }

    public void inTakeMoveHeader(double power) {
        intakeMotor[0].setPower(power);
    }

    public void inTankArmLiftLower(double power,boolean x,boolean a) {
//        // I want to lift and lower the arm
        if(x){
            intakeMotor[1].setPower(power);
            if (a){
                intakeMotor[1].setPower(-power);
            }
            if(intakeTouch[0].isPressed()) {
                intakeMotor[1].setPower(0);
            }
            else if (intakeTOuch[1].isPressed()){
                intakeMotor[1].setPower(0);
            }
        }

    }
public void inTankArmRiseLower (double power,boolean y, boolean b) {
    //rase and lower arm
    if (y) {
        intakeMotor1[0].setPower(power);
        if (b) {
            intakeMotor1[0].setPower(-power);
            }
        }
    }
}
