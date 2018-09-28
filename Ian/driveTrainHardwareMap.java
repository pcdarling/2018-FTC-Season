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
    public DcMotor right1;
    public DcMotor right2;
    public DcMotor left1;
    public DcMotor left2;
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
        right1  = hwMap.get(DcMotor.class, "right1");
        right2  = hwMap.get(DcMotor.class, "right2");
        left1  = hwMap.get(DcMotor.class, "left1");
        left2  = hwMap.get(DcMotor.class, "left2");
        // Set the direction of the motors
        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);
        left1.setDirection(DcMotor.Direction.FORWARD);
        left2.setDirection(DcMotor.Direction.FORWARD);
        // Set all motors to zero power
       right1.setPower(0);
       right2.setPower(0);
       left1.setPower(0);
       left2.setPower(0);
       // fuction for movement@!
	}
	   public static void accelerate (gamepad1SpeedND = gamepad1.left_stick_y,gamepad1T = gamepad1.left_stick_x) {
		 
		 if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND) ||
        Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){
           // rotating counter clock wise
            if(gamepad1T > power){
                robot.right1.setPower(gamepad1T);
                robot.right2.setPower(gamepad1T);
                robot.left1.setPower(gamepad1T);
                robot.left2.setPower(gamepad1T);
            }
            // rotating clock wise
            else if(gamepad1T < rewop){
                robot.right1.setPower(-gamepad1T);
                robot.right2.setPower(-gamepad1T);
                robot.left1.setPower(-gamepad1T);
                robot.left2.setPower(-gamepad1T);
            }
            else{
                robot.right1.setPower(0);
                robot.right2.setPower(0);
                robot.left1.setPower(0);
                robot.left2.setPower(0);
            }
        }
		 else{  
				if (gamepad1SpeedND > power) {
                robot.right1.setPower(gamepad1SpeedND);
                robot.right2.setPower(gamepad1SpeedND);
                robot.left1.setPower(gamepad1SpeedND);
                robot.left2.setPower(gamepad1SpeedND);
				} 
				else if (gamepad1SpeedND < rewop) {
                robot.right1.setPower(-gamepad1SpeedND);
                robot.right2.setPower(-gamepad1SpeedND);
                robot.left1.setPower(-gamepad1SpeedND);
                robot.left2.setPower(-gamepad1SpeedND);
				}
				else {
                robot.right1.setPower(0);
                robot.right2.setPower(0);
                robot.left1.setPower(0);
                robot.left2.setPower(0);
				}
			}
		}
    }
