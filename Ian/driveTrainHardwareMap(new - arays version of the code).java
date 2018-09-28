// arays versoin of the code.
package org.firstinspires.ftc.teamcode;
//working controls
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
public class driveTrainHardwareMap{
    /* Public OpMode members. */
    public DcMotor Motor[0];//right1
    public DcMotor Motor[1];//right2
    public DcMotor Motor[2];//left1   public DcMotor Motor[3]//left2
	public  power;
	public  rewop;

    /* local OpMode members. */
    HardwareMap hwMap   =  null;
    /* Constructor */
	double power  = 0.06;
	double rewop  = -0.06;
   double powerThreshold = 0.06;

    public driveTrainHardwareMap(){
	driveTrainHardwareMap robot = new driveTrainHardwareMap();
    }
    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
		double[] Motors
	    Motors = new double[4]
		
	   Motors[0] = hwMap.get(DcMotor.class, "right1");
	   Motors[1] = hwMap.get(DcMotor.class, "right2");
	   Motors[2] = hwMap.get(DcMotor.class, "left1");
	   Motors[3] = hwMap.get(DcMotor.class, "left2");
        // Set the direction of the motors
        Motor[0].setDirection(DcMotor.Direction.FORWARD);
        Motor[1].setDirection(DcMotor.Direction.FORWARD);
        Motor[2].setDirection(DcMotor.Direction.FORWARD);
        Motor[3].setDirection(DcMotor.Direction.FORWARD);
        // Set all motors to zero power
       Motor[0].setPower(0);
       Motor[1].setPower(0);
       Motor[2].setPower(0);
       Motor[3].setPower(0);
       // fuction for movement@!
	}
	   public static void accelerate (gamepad1SpeedND = gamepad1.left_stick_y,gamepad1T = gamepad1.left_stick_x) {
		
		 if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND) ||
        Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){
           // rotating counter clock wise
            if(gamepad1T > power){
                robot.Motor[0].setPower(gamepad1T);
                robot.Motor[1].setPower(gamepad1T);
                robot.Motor[2].setPower(-gamepad1T);
                robot.Motor[3].setPower(-gamepad1T);
            }
            // rotating clock wise
            else if(gamepad1T < rewop){
                robot.Motor[0].setPower(-gamepad1T);
                robot.Motor[1].setPower(-gamepad1T);
                robot.Motor[2].setPower(gamepad1T);
                robot.Motor[3].setPower(gamepad1T);
            }
            else{
                robot.Motor[0].setPower(0);
                robot.Motor[1].setPower(0);
                robot.Motor[2].setPower(0);
                robot.Motor[3].setPower(0);
            }
        } 
                robot.Motor[0].setPower(gamepad1SpeedND);
                robot.Motor[1].setPower(gamepad1SpeedND);
                robot.Motor[2].setPower(gamepad1SpeedND);
                robot.Motor[3].setPower(gamepad1SpeedND);
				} 
				else if (gamepad1SpeedND < rewop) {
                robot.Motor[0].setPower(-gamepad1SpeedND);
                robot.Motor[1].setPower(-gamepad1SpeedND);
                robot.Motor[2].setPower(-gamepad1SpeedND);
                robot.Motor[3].setPower(-gamepad1SpeedND);
				}
				else {
                robot.Motor[0].setPower(0);
                robot.Motor[1].setPower(0);
                robot.Motor[2].setPower(0);
                robot.Motor[3].setPower(0);
				}
			}
		}
    }
