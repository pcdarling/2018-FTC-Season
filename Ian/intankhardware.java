package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// I need 3 motor 3 touch senser
//i might need 1 color senser and 1 servo

public class inTankHardware {
    DcMotor[] ITMotors = new DcMotor[3];
    TouchSensor[] InTouchS = new TouchSensor[3];
    ColorSensor[] InTColor = new ColorSensor[1];
    Servo[] INTServo = new Servo[1];

    HardwareMap ITHwMap = null;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;

        for (int i = 0; i < ITMotors.length; i++) {
            ITMotors[i] = ITHwMap.get(DcMotor.class, "ITMotor" + i);
            ITMotors[i].setDirection(DcMotor.Direction.FORWARD);
            ITMotors[i].setPower(0);
        }
        for (int i = 0;i < InTouchS.length; i++){
            InTouchS[i] = ITHwMap.get(TouchSensor.class,"InTTouch" +i);
        }
        // If I need it!!!!!!
//        for(int i = 0 ; i < InTColor.length; i++){
//            InTColor[i] = ITHwMap.get(ColorSensor.class,"InTColor" + i);
//
//        }
//        for(int i = 0 ; i < INTServo.length; i++){
//            INTServo[i] = ITHwMap.get(Servo.class, "InTServo");
//
//		}
	}
	public void  inTankHeader(double power,){
		ITMotors[0].setPower(power)
		}
	}
	public void  inTankArmLift/Lowr(){
		// I want to lift and lower the arm
		
	}
	public void inTankArmRise/Lower (){
		//rase and lower arm
		
	}