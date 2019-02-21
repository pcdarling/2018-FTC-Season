package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class EvanHardware {
    public DcMotor bicep;
    public Servo latchServo;
    public DistanceSensor groundSensor;

    double storePos = 0.85; // the store postion for the larry deplore
    public double latchingServoOpen = 1;
    public double latchingServoClosed = 0;
    public int evanUp = 5591; // the avenge numbers for going up
    public double neededDistanceToGround = 14 + 0.3;

    HardwareMap hwMap;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        //TODO " the servo is the servo that stores larry we really do not need it later " - ian
        bicep = hwMap.get(DcMotor.class, "bicep");
        groundSensor = hwMap.get(DistanceSensor.class, "ground sensor");
        bicep.setDirection(DcMotor.Direction.FORWARD);

        bicep.setPower(0);

        bicep.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bicep.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bicep.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        latchServo = hwMap.get(Servo.class, "servo");

        //createEvanThread(0);
    }

    public void flipRobot(double power,boolean flipUp){
        if(flipUp) {
            bicep.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bicep.setTargetPosition(evanUp);
            bicep.setPower(power);
            while (bicep.isBusy()) {
            }
            bicep.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bicep.setPower(0.1);
        }
        else {
            bicep.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bicep.setPower(power);
            while (groundSensor.getDistance(DistanceUnit.CM) > neededDistanceToGround) {


            }
            bicep.setPower(0);
        }
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