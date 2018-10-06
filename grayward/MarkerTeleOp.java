package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Iterative OpMode", group="Iterative Opmode")
public class MarkerTeleOp extends OpMode
{
	// Put your robot here
    MarkerHardware robot = new MarkerHardware();

    // Put your class variables here

    @Override
    public void init() {
		robot.init();
    }


    @Override
    public void init_loop() {

    }


    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad2.x){
            robot.toggleMarker();
        }

        
    }


    @Override
    public void stop() {

    }

}
