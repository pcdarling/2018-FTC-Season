
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TheEvanHardware
{
    /* Public OpMode members. */
    public DcMotor theEvan = null;
    // public Servo mrKrabs = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;

    //these peramiters will have to be changed
    //double krabsOpenPos = 1;
    //double krabsClosedPos = 0;
    //boolean krabsOpen = false;

    int theEvanMax = 1000;
    double powerThresh = 0.06;

    /* Constructor */
    public TheEvanHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        theEvan = hwMap.get(DcMotor.class, "the_evan");
        theEvan.setDirection(DcMotor.Direction.FORWARD);
        theEvan.setPower(0);
        theEvan.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        mrKrabs = hwMap.get(Servo.class,"mrkrabs");
//        mrKrabs.setPosition(0.5);

    }

//    public void toggleClaw() {
//       if (!krabsOpen){
//           mrKrabs.setPosition(krabsOpenPos);
//           krabsOpen = true;
//       }
//       else{
//           mrKrabs.setPosition(krabsClosedPos);
//           krabsOpen = false;
//       }
//
//    }


    public class EvanThread extends Thread{
        double power;
        public EvanThread(double power){
            this.power = power;
        }
        public void run(){
            try{
            moveTheEvan(this.power);
            }
            catch (Exception e){
            // sumting wong
            }
        }
        public void moveTheEvan(double power) {
            int encoderCount = theEvan.getCurrentPosition();
            if (power < powerThresh){
            }
            else if (power > 0 && encoderCount >= theEvanMax){
            }
            else if (power < 0 && encoderCount <= theEvanMax){
            }
            else {
                theEvan.setPower(power);
            }
        }


    }

}
