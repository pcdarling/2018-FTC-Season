package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

public class DeployHardware {
    public DcMotor dMotor;

    public int countsToGround = 1000;
    public int countsToRelease = 100;
    public boolean isMecanum = false;

    public HardwareMap hwMap;

    public void init(HardwareMap aHWmap){
       hwMap = aHWmap;
       dMotor = hwMap.get(DcMotor.class,"dMotor");
       dMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       dMotor.setPower(0);

       //groundSensor = hwMap.get(OpticalDistanceSensor.class,"groundSensor");
    }
    public void deploy(){
//      while not touching ground keep power 0.5;
//      when ir sensor feedback is distance from wheels stop;
//                set dmotor power 0.5 until 100 encoder counts;
//                if mechanon wheels strafe 2 in;
//                if not mec wheels go forward 2 in;
//                lower lift to storage;
        dMotor.setTargetPosition(dMotor.getCurrentPosition()+countsToGround+countsToRelease);
        dMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dMotor.setPower(0.5);
        while (dMotor.isBusy()){
            //busywaiting
        }
        dMotor.setPower(0);
        if (isMecanum){
            //strafe
        }
        else{
            //forward
        }
    }

}