package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;


public class DriveTrainHw {
    DcMotor[] motors = new DcMotor[4];

    double thresh = 0.06;
    double encCountsPerRev = 28 * 19.2 * 84 / 100; // electrical * internal * external
    double wheelRadius = 2.25;
    double wheelCircumference = 2 * Math.PI * wheelRadius;
    double countsPerInch = encCountsPerRev / wheelCircumference;
    double robotLength = 14.5;
    double robotWidth = 17.5;
    double robotDiameter = Math.sqrt(Math.pow(robotLength, 2) + Math.pow(robotWidth, 2));
    double robotCircumference = Math.PI * robotDiameter;

    HardwareMap hwmap;

    DriveThread dt;

    public DriveTrainHw() {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        for (int i = 0; i < motors.length; i++) {
            motors[i] = hwmap.get(DcMotor.class, "motor" + i);
            motors[i].setDirection(DcMotor.Direction.FORWARD);
            motors[i].setPower(0);
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        createDriveThread(0, 0);
    }

    public void tankcontrolsMovent(double gamepad1Ry, double gamepad1Ly, boolean turbo) {
        if (gamepad1Ly > thresh || gamepad1Ly < thresh) {
            if (turbo) {
                motors[0].setPower(gamepad1Ly);
                motors[2].setPower(gamepad1Ly);
            } else {
                motors[0].setPower(gamepad1Ly / 2);
                motors[2].setPower(gamepad1Ly / 2);
            }
        } else {
            motors[0].setPower(0);
            motors[2].setPower(0);
        }
        if (gamepad1Ry > thresh || gamepad1Ry < thresh) {
            if (turbo) {
                motors[1].setPower(-gamepad1Ry);
                motors[3].setPower(-gamepad1Ry);
            } else {
                motors[1].setPower(-gamepad1Ry / 2);
                motors[3].setPower(-gamepad1Ry / 2);
            }

        } else {
            motors[1].setPower(0);
            motors[3].setPower(0);
        }

    }

    public void FPSmovementByControl(double gamepad1SpeedND, double gamepad1T, boolean turbo) {

        if (Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)) {
            if (gamepad1T > thresh) {
                if (turbo) {
                    motors[0].setPower(gamepad1T);
                    motors[1].setPower(-gamepad1T);
                    motors[2].setPower(gamepad1T);
                    motors[3].setPower(-gamepad1T);
                } else {
                    motors[0].setPower(gamepad1T / 2);
                    motors[1].setPower(-gamepad1T / 2);
                    motors[2].setPower(gamepad1T / 2);
                    motors[3].setPower(-gamepad1T / 2);
                }
            } else if (gamepad1T < -thresh) {
                if (turbo) {
                    motors[0].setPower(gamepad1T);
                    motors[1].setPower(-gamepad1T);
                    motors[2].setPower(gamepad1T);
                    motors[3].setPower(-gamepad1T);
                } else {
                    motors[0].setPower(gamepad1T / 2);
                    motors[1].setPower(-gamepad1T / 2);
                    motors[2].setPower(gamepad1T / 2);
                    motors[3].setPower(-gamepad1T / 2);
                }

            } else {
                motors[0].setPower(0);
                motors[1].setPower(0);
                motors[2].setPower(0);
                motors[3].setPower(0);
            }

        } else {
            //saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
            if (gamepad1SpeedND > thresh) {
                if (turbo) {
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(-gamepad1SpeedND);
                    motors[3].setPower(-gamepad1SpeedND);
                } else {
                    motors[0].setPower(-gamepad1SpeedND / 2);
                    motors[1].setPower(-gamepad1SpeedND / 2);
                    motors[2].setPower(-gamepad1SpeedND / 2);
                    motors[3].setPower(-gamepad1SpeedND / 2);
                }
            }
            //saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
            else if (gamepad1SpeedND < -thresh) {
                if (turbo) {
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(-gamepad1SpeedND);
                    motors[3].setPower(-gamepad1SpeedND);
                } else {
                    motors[0].setPower(-gamepad1SpeedND / 2);
                    motors[1].setPower(-gamepad1SpeedND / 2);
                    motors[2].setPower(-gamepad1SpeedND / 2);
                    motors[3].setPower(-gamepad1SpeedND / 2);
                }
            }
            //saying if the Motor[i] is not doing anything than have the power of the motor to 0.
            else {
                motors[0].setPower(0);
                motors[1].setPower(0);
                motors[2].setPower(0);
                motors[3].setPower(0);

            }
        }
    }

    public void createDriveThread(double power, double inches) {
        dt = new DriveThread(power, inches);
    }

    public void createRotateThread(double power, double degrees) {
        dt = new DriveThread(power, degrees, true);
    }

    public class DriveThread extends Thread {
        double power;
        double inchesOrDegress;
        int rotation = -1;

        public DriveThread(double power, double inches) {
            this.power = power;
            this.inchesOrDegress = inches;
            this.rotation = 0;
        }

        public DriveThread(double power, double degress, boolean rotation) {
            this.inchesOrDegress = degress;
            this.power = power;
            this.rotation = 1;
        }

        public void run() {
            if (this.rotation == 0) {
                this.driveInInches(this.power, this.inchesOrDegress, false);
            } else if (this.rotation == 1) {
                this.rotateInDegrees(this.power, this.inchesOrDegress);
            }
        }

        public void driveInInches(double power, double inches, boolean rotation) {
            boolean busy = true;
            double rev = inches / wheelCircumference;
            int counts = (int) (rev * encCountsPerRev);
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
                    motors[i].setPower(Math.abs(power) * sign);
                }
            } else {
                for (int i = 0; i < motors.length; i++) {
                    int even = i % 2;
                    if (even == 0) {
                        if (power > 0) {
                            sign = -1;
                        } else {
                            sign = 1;
                        }
                    } else {
                        if (power > 0) {
                            sign = 1;
                        } else {
                            sign = -1;
                        }
                    }
                    int currentPosition = motors[i].getCurrentPosition();
                    motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motors[i].setTargetPosition(currentPosition + sign * counts);
                    motors[i].setPower(sign * Math.abs(power));
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
        public void rotateInDegrees(double power,double degrees){
            driveInInches(power,degrees/360*robotCircumference,true);
        }
    }
}


