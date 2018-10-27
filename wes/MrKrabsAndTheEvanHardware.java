

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class MrKrabsAndTheEvanHardware
{
    /* Public OpMode members. */
    public DcMotor theEvan = null;
    public Servo mrKrabs = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;

    //these peramiters will have to be changed
    double krabsOpenPos = 1;
    double krabsClosedPos = 0;
    boolean krabsOpen = false;

    int theEvanMax = 1000;

    /* Constructor */
    public MrKrabsAndTheEvanHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        theEvan = hwMap.get(DcMotor.class, "the_evan");
        theEvan.setDirection(DcMotor.Direction.FORWARD);
        theEvan.setPower(0);
        theEvan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mrKrabs = hwMap.get(Servo.class,"mrkrabs");
        mrKrabs.setPosition(0.5);

    }

    public void toggleClaw() {
       if (!krabsOpen){
           mrKrabs.setPosition(krabsOpenPos);
           krabsOpen = true;
       }
       else{
           mrKrabs.setPosition(krabsClosedPos);
           krabsOpen = false;
       }

    }

    public void moveTheEvan(double power) {


    }
   


 }

