package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumHardware {
    public DcMotor[] motors = new DcMotor[4];

    HardwareMap hwmap = null;

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;

        for (int i = 0; i < motors.length; i++){
            motors[i] = hwmap.get(DcMotor.class, "motor " + i);
            if (i % 2 == 0){//even number
                motors[i].setDirection(DcMotor.Direction.REVERSE);
            }else{
                motors[i].setDirection(DcMotor.Direction.FORWARD);
            }
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motors[i].setPower(0);
        }
    }
    public void powerDriveTrain(double power, boolean strafe, boolean rotate) {
        if (strafe) {
            motors[0].setPower(-power/2);
            motors[1].setPower(power/2);
            motors[2].setPower(power/2);
            motors[3].setPower(-power/2);
        }else if (rotate){
            for (int i = 0; i < motors.length; i++){
                if (i % 2 == 0){ // even number
                    motors[i].setPower(power/2);
                }else{
                    motors[i].setPower(-power/2);
                }
            }
        }
        else{
            for (int i = 0; i < motors.length; i++) {
                motors[i].setPower(power/2);
            }
        }
    }
}