package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MountServoHardware {
    Servo mountServo;
    HardwareMap hwmap = null;

    public MountServoHardware(){}

    public void init(HardwareMap ahwMap){
        hwmap = ahwMap;

        mountServo = hwmap.get(Servo.class, "mount");
        mountServo.setPosition(0.5);
    }
}