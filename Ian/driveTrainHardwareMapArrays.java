// arays versoin of the code.
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
/* right tank control */
/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class driveTrainHardwareMapArrays {
    /* Public OpMode members. */
    /*construcking the arrays variable*/
    DcMotor[] motors = new DcMotor[4];

    /* local OpMode members. */
    HardwareMap hwMap   =  null;
    /* Constructor */
    double power  = 0.06;
    double rewop  = -0.06;
    double powerThreshold = 0.06;

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

    /* fuction for movement@! */
    public void tankcontrolsMovent (double gamepad1Ry ,double gamepad1Ly){
        if(gamepad1Ly > power || gamepad1Ly < power){
            motors[0].setPower(gamepad1Ly);
            motors[2].setPower(gamepad1Ly);
        }
        else{
            motors[0].setPower(0);
            motors[2].setPower(0);
        }
        if(gamepad1Ry > power || gamepad1Ry < power){
            motors[1].setPower(-gamepad1Ry);
            motors[3].setPower(-gamepad1Ry);

        }
        else{
            motors[1].setPower(0);
            motors[3].setPower(0);
        }

    }

    public void movementByControl (double gamepad1SpeedND,double gamepad1T) {

            if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){
                if(gamepad1T > power){
                    motors[0].setPower(-gamepad1T);
                    motors[1].setPower(gamepad1T);
                    motors[2].setPower(-gamepad1T);
                    motors[3].setPower(gamepad1T);
                }
                else if (gamepad1T < rewop){
                    motors[0].setPower(-gamepad1T);
                    motors[1].setPower(gamepad1T);
                    motors[2].setPower(-gamepad1T);
                    motors[3].setPower(gamepad1T);

                }
                else{
                    motors[0].setPower(0);
                    motors[1].setPower(0);
                    motors[2].setPower(0);
                    motors[3].setPower(0);
                }

            }
            else{
                //saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
                if(gamepad1SpeedND > power){
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(gamepad1SpeedND);
                    motors[3].setPower(gamepad1SpeedND);
                }
                //saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
                else if (gamepad1SpeedND < rewop){
                    motors[0].setPower(-gamepad1SpeedND);
                    motors[1].setPower(-gamepad1SpeedND);
                    motors[2].setPower(gamepad1SpeedND);
                    motors[3].setPower(gamepad1SpeedND);
                }
                //saying if the Motor[i] is not doing anything than have the power of the motor to 0.
                else{
                    motors[0].setPower(0);
                    motors[1].setPower(0);
                    motors[2].setPower(0);
                    motors[3].setPower(0);

                }
            }
        }
    }
