package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CompetitionHardware
{
    // Put your Hardware objects here

    // Hardware Map IS Needed
    HardwareMap hwMap           =  null;

    // Put class variables here
    int location = -1;

    // Don't touch the constructor
    public CompetitionHardware(){

    }

    // Define your connections between your physical and virtual hardware objects
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // EX:
        // motor = hwMap.get(DcMotor.class, "motor");
    }

    // Put your functions down here
    public void driveInInches(double power, double inches) {

    }

    public void rotateInDegrees(double power, double degrees) {

    }

    public void moveTheEvan(double power) {

    }

    public void toggleMarker() {

    }

    public void determineLocation() {
        // Does CV stuff and changes the "location" variable
    }
}