// arays versoin of the code.
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrainHardwareMapArrays {
    /* Public OpMode members. */
    /*construcking the arrays variable*/
    DcMotor[] motors = new DcMotor[4];

    /* local OpMode members. */
    HardwareMap hwMap   =  null;
    /* Constructor */
    double thresh  = 0.06;
    double encCountsPerRev = 20 * 20 * 84 / 100; // electrical * internal * external
    double wheelRadius = 2.25;
    double wheelCircumference = 2 * Math.PI * wheelRadius;
    double robotLength = 14.5;
    double robotWidth = 17.5;
    double robotDiameter = Math.sqrt(Math.pow(robotLength,2)+Math.pow(robotWidth,2));
    double robotCircumference = Math.PI*robotDiameter;
    /* Initialize standard Hardware interfaces */

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        hwMap = ahwMap;


        /*give the arrays there infomation to work */
        for(int i = 0; i < motors.length; i++){
            motors[i] = hwMap.get(DcMotor.class, "motor"+i);
            motors[i].setDirection(DcMotor.Direction.FORWARD);
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void driveInInches (double power,double inches,boolean rotation) {
        boolean busy = true;
        double percentOfWheel = inches / wheelCircumference;
        int counts = (int)(percentOfWheel * encCountsPerRev);
        int sign = 1;

        if (rotation) {
            for (int i = 0; i < motors.length; i++) {
                if (power > 0) {
                    sign = -1;
                } else {
                    sign = 1;
                }
                int currentPosition = motors[i].getCurrentPosition();
                motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors[i].setTargetPosition(currentPosition + sign * counts);
                motors[i].setPower(power);
            }
        } else {
            for (int i = 0; i < motors.length; i++) {
                int even = i%2;
                if(even == 0){
                    if (power > 0) {
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                }
                else {
                    if (power > 0) {
                        sign = -1;
                    } else {
                        sign = 1;
                    }
                }

                int currentPosition = motors[i].getCurrentPosition();
                motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors[i].setTargetPosition(currentPosition + sign * counts);
                motors[i].setPower(power);
            }
        }

        while (busy) {
            for (int i = 0; i < motors.length; i++) {
                busy = busy && motors[i].isBusy();
            }
        }

        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void driveInInches (double power,double inches) {
        boolean busy = true;
        double percentOfWheel = inches / wheelCircumference;
        int counts = (int)(percentOfWheel * encCountsPerRev);
        int sign = 1;


        for (int i = 0; i < motors.length; i++) {
            int even = i%2;
            if(even == 0){
                if (power > 0) {
                    sign = 1;
                } else {
                    sign = -1;
                }
            }
            else {
                if (power > 0) {
                    sign = -1;
                } else {
                    sign = 1;
                }
            }

            int currentPosition = motors[i].getCurrentPosition();
            motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors[i].setTargetPosition(currentPosition + sign * counts);
            motors[i].setPower(power);
        }


        while (busy) {
            for (int i = 0; i < motors.length; i++) {
                busy = busy && motors[i].isBusy();
            }
        }

        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void rotateInDegrees(double power,double degrees){
        driveInInches(power,degrees/360*robotCircumference,true);
    }
}
