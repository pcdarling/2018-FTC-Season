// arays versoin of the code.
package org.firstinspires.ftc.teamcode;
//working controls
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
	public  power;
	public  rewop;

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

		double[] Motors
	   Motors = new double[4]
	   index = 0;
	  while(index < 4) {
	    Motors[0,1,2,3] = hwMap.get(DcMotor.class);
		Motor[0,1,2,3,].setDirection(DcMotor.Direction.FORWARD0;
		Motor[0,1,2,3].setPower(0);
		index++;  
		check index;
	  
	}  /* fuction for movement@! */
	   public void movement (double gamepad1SpeedND,double gamepad1T) {
		
		 if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND) ||
        Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){
           /* rotating counter clock wise */
            if(gamepad1T > power){
                Motor[0].setPower(gamepad1T);
                Motor[1].setPower(gamepad1T);
                Motor[2].setPower(-gamepad1T);
                Motor[3].setPower(-gamepad1T);
            }
            /* rotating clock wise */
            else if(gamepad1T < rewop){
                Motor[0].setPower(-gamepad1T);
                Motor[1].setPower(-gamepad1T);
                Motor[2].setPower(gamepad1T);
                Motor[3].setPower(gamepad1T);
            }
            else{
                Motor[0].setPower(0);
                Motor[1].setPower(0);
                Motor[2].setPower(0);
                Motor[3].setPower(0);
            }
        } 
			else{
				if(){
                Motor[0].setPower(gamepad1SpeedND);
                Motor[1].setPower(gamepad1SpeedND);
                Motor[2].setPower(gamepad1SpeedND);
                Motor[3].setPower(gamepad1SpeedND);
				} 
				else if (gamepad1SpeedND < rewop) {
                Motor[0].setPower(-gamepad1SpeedND);
                Motor[1].setPower(-gamepad1SpeedND);
                Motor[2].setPower(-gamepad1SpeedND);
                Motor[3].setPower(-gamepad1SpeedND);
				}
				else {
                Motor[0].setPower(0);
                Motor[1].setPower(0);
                Motor[2].setPower(0);
                Motor[3].setPower(0);
				}
			}
		}
}