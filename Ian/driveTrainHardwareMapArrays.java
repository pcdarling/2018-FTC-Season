// arays versoin of the code.
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Java.util.Array;
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
		/*construcking the arrays variable*/
    	double[] Motors
	    Motors = new double[4]
		double[] Nmotors
		Fmotors = new double[3]
		double[] Smotors
		Bmotors = new double[3]
		
		/*give the arrays there infomation to work */
		for(double m = 0; j,Nmotors.length; j++){
		 Lmotors[m] = hwMap.get(DcMotor.class);
		 Lmotors[m].setDirection(DcMotor.Direction.FORWARD);
		 Lmotors[m].setPower(0);
		}
		for(double j = 0; j,Nmotors.length; j++){
		 Rmotors[j] = hwMap.get(DcMotor.class);
		 Rmotors[j].setDirection(DcMotor.Direction.FORWARD);
		 Rmotors[j].setPower(0);
		}
		for(double i = 0; i < Motors.length; i++){
		 Motors[i] = hwMap.get(DcMotor.class);
		 Motors[i].setDirection(DcMotor.Direction.FORWARD;
		 Motors[i].setPower(0);
		}  
	}
	
	/* fuction for movement@! */
public void movementByControl (double gamepad1SpeedND,double gamepad1T) {
		
	
		for(i,j,m = 0; m < Smotors.length, i < Motors.length, j < Nmotors.length; m,j,i++){
			
			if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND) ||
			Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){		
				if(gamepa1T > power){
					Lmotors[m].setPower(gamepad1T);
					Rmotors[j].setpower(-gamepad1T);
				}
				else if (gamepa1T < rewop){
					Lmotors[m].setPower(-gamepad1T);
					Rmotors[j].setPower(gamepad1T);
					
				}
				else{
				Lmotor[m].setPower(0);	
				Rmotors[j].setPower(0);
				}
			
			}
			esle{
				//saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
				if(gamepad1SpeedND > power){
					Motors[i].setPower(gamepad1SpeedND);
				}
				//saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
				else if (gamepad1SpeedND < rewop){
					Motors[i].setPower(-gamepad1SpeedND);
				}
				//saying if the Motor[i] is not doing anything than have the power of the motor to 0.
				else{
					Motors[i].setPower(0);
				}
			}
		}
	}

