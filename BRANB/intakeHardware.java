package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
// I need 3 motor 3 touch senser
//i might need 1 color senser and 1 servo
public class intakeHardware {
    DcMotor[] ITMotors = new DcMotor[3];
    TouchSensor[] InTouchS = new TouchSensor[3];
    ColorSensor[] InTColor = new ColorSensor[1];
    Servo[] INTServo = new Servo[1];
    HardwareMap ITHwMap = null;
    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        ITHwMap = ahwMap;
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
        }
        for (int i = 0; i < InTouchS.length; i++) {
            InTouchS[i] = ITHwMap.get(TouchSensor.class, "InTTouch" + i);
        }
        for (int i = 0; i < InTColor.length; i++) {
            InTColor[i] = ITHwMap.get(ColorSensor.class, "InTColor" + i);
        }
        for (int i = 0; i < INTServo.length; i++) {
            INTServo[i] = ITHwMap.get(Servo.class, "InTServo");
        }
    }
    public void intakeSuccc(double power) {
        ITMotors[0].setPower(power);
    }
    public void moveIntake(double power, boolean x, boolean a) {
        // I want to lift and lower the arm
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
    public void intakeArmRaiseLower(double power, boolean y, boolean b) {
        //rase and lower arm
        if (y) {
            ITMotors[0].setPower(power);
        }
        else if (b) {
            ITMotors[0].setPower(-power);
        }
        else{
            ITMotors[0].setPower(0);
        }
    }
}