package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class driveTrainOpMode extends OpMode {
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
	// it adds the movement fuction to the start of the robot planes (driver control). 
	public void loop() {
    robot.movement(gamepad1.left_stick_y,gamepad1.right_stick_x);

	
    }
    
}