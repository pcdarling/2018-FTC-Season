package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "CompetitionAutoOp", group = "CompetitionBot")
public class CompetitionAutoOp extends LinearOpMode{
    CompetitionHardware robot = new CompetitionHardware();

    public void runOpMode(){
        robot.init(robot.hwmap);

        waitForStart();
        // OP MODE STARTS
            robot.moveTheEvan(1);
            //wait 2 seconds
            robot.driveInInches(0.8, 1);
            robot.moveTheEvan(-1);
            //wait 2 seconds
            // arm and robot should be down by now

            // Move until near samples
            robot.driveInInches(0.8, robot.distanceToSamples);
            //
            // Wait until location is determined
            while(robot.location < 0) {
                // Busy waiting
            }

            if (robot.location == 0) {
                // SW
                robot.allTheWays(0.8);
            }
            else if (robot.location == 1) {
                // SE
                robot.allTheWays(-0.8);
            }
            else if (robot.location == 2) {
                // NW
                robot.allTheWays(-0.8);
            }
            else if (robot.location == 3){
                // NE
                robot.allTheWays(0.8);
            }

            // placing team marker
            robot.toggleMarker();
            // wait 1 second

            // place the placer in it's place
            robot.toggleMarker();

            // Turn towards crater
            robot.rotateInDegrees(-0.8,180);

            // Drive towards crater
            robot.driveInInches(0.8,robot.distanceFromDepotToCrater);
    }
}
