package org.firstinspires.ftc.teamcode;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class intankOpmode extends OpMode {


	@Override
	public void init(){
		robot.init(hardwareMap);
	}
	@Override
	//to start the the robot
	public void start() {
	}

	@Override
	// it adds the movement fuction to the start of the robot planes (driver control).
	public void loop () {
		power = 0.06
		a = gamepad1.button_x_pressed;
		A = gamepad1.button_z_pressed
		b = gamepad1.button_y_pressed;
		c = gamepad1.button_b_pressed;
		
		while(a){
			// intank header ativaction
			robot.inTankHeader(double power);
		}
		while(A){
			robot.inTankHeader(double -power);
			//to turn the header in reveres
		}
		while(b){
			// lift and lower 
			robot.inTankArmLift/Lowr();
		}
		while(c){
		// rasied and lower
		robot.inTankArmRise/Lower();
		}
	}
}