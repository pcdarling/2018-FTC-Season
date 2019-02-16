package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class EvanHardware {
    public DcMotor evanDcMoter1;
    public Servo latchServo;

    public double latchingServoOpen = 1;
    public double latchingServoClosed = 0;
    public int evanUp = 0;
    public int evanDown = 404;// the evan encoder count for going down

    HardwareMap hwMap;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        evanDcMoter1 = hwMap.get(DcMotor.class, " MotorForEvan");
        evanDcMoter1.setDirection(DcMotor.Direction.FORWARD);

        evanDcMoter1.setPower(0);

        evanDcMoter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        evanDcMoter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        latchServo = hwMap.get(Servo.class, "servo");

        //evanDcMoter1.setTargetPosition(evanDown);
        //evanDcMoter1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        latchServo.setPosition(latchingServoClosed);


    }
    public void flipRobot(double power,int position){
        evanDcMoter1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        evanDcMoter1.setTargetPosition(position);
        evanDcMoter1.setPower(power);
        while (evanDcMoter1.isBusy()) {
        }
        evanDcMoter1.setPower(0);
        evanDcMoter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void latch(boolean makeOpen){
        if(makeOpen) {
            latchServo.setPosition(latchingServoOpen);
        }
        else {
            latchServo.setPosition(latchingServoClosed);
        }

    }
}

