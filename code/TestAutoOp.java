package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//TODO: This is for testing purposes only, if the code being tested here is working and needs to stay, place in another file, otherwise it *will* be deleted
@Autonomous(name = "Test Auto", group = "Testing")
public class TestAutoOp extends LinearOpMode {
CompetitionHardware robot = new CompetitionHardware(false);
   public void runOpMode(){
       robot.init(hardwareMap);
       robot.evan.flipRobot(1, true);
       telemetry.addData("Bicep Power: ",robot.evan.bicep.getPowerFloat());
       telemetry.addData("Status: ", "Ready to Play!");
       waitForStart();

       sleep(1000);
       robot.evan.flipRobot(-1, false);
   }

    //TODO: Make sure functions you need go with the component code being tested

}
