package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Iterative OpMode", group="Iterative Opmode")
public class AutonomousTeleOp extends OpMode
{
	// Put your robot here
    CompetitionHardware robot = new CompetitionHardware();

    // Put your class variables here
    double distanceToSamples = 4; // inches

    @Override
    public void init() {
		robot.init(hardwareMap);
    }


    @Override
    public void init_loop() {

    }


    @Override
    public void start() {

    }

    @Override
    public void loop() {
        // Deploy from launcher
        robot.moveTheEvan(1);
        //wait 2 seconds
        robot.driveInInches(0.8, 1);
        robot.moveTheEvan(-1);
        //wait 2 seconds
        // arm and robot should be down by now

        // Move until near samples
        robot.driveInInches(0.8, distanceToSamples);

        // Wait until location is determined
        while(robot.location < 0) {
            // Busy waiting
        }

        if (robot.location == 0) {
            // SW
        }
        else if (robot.location == 1) {
            // SE
        }
        else if (robot.location == 2) {
            // NW
            doNorthWest();
        }
        else if (robot.location == 3){
            // NE
        }

        // placing team marker
        robot.toggleMarker();
        // wait 1 second

        // place the placer in it's place
        robot.toggleMarker();

        // Turn towards crater
        robot.rotateInDegrees(-0.8,180);

        // Drive towards crater
        robot.driveInInches(0.8,18);
    }

    @Override
    public void stop() {

    }

    // Custom control functions down here
    public void doNWOrSE() {
        // START: North-West Control
        // avoiding silver
        robot.rotateInDegrees(-0.8, 90);

        // still avoiding silver
        robot.driveInInches(0.8, 6);

        //turing to the depot
        robot.rotateInDegrees(-0.8, 45);

        // going to the depot
        robot.driveInInches(0.8, 14);
        // END: North-West Crater
    }
    public void doSWOrNE(){
        // turn to avoid silver
        robot.rotateInDegrees(-0.8, 90);

        //



    }
}
