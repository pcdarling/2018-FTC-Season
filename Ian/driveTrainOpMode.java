package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class driveTrainOpMode extends OpMode
{
    driveTrainHardwareMap robot = new driveTrainHardwareMap();

    @Override
    public void init(){
        robot.init(hardwareMap);
    }
    
    @Override
	//to start the the robot
    public void start(){
    }
   @Override
   public void loop() {
	 // it need the AI control of the robot for the driverTrain.
	 robot.init();
	   
   }
   @Override
	// it adds the movement fuction to the start of the robot planes (driver control). 
	public void loop() {
		robot.init(accelerate);
	
    }
    
}