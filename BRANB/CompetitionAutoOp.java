package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "CompetitionAutoOp", group = "CompetitionBot")
public class CompetitionAutoOp extends LinearOpMode{
    CompetitionHardware robot = new CompetitionHardware();
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
        robot.init(hardwareMap);
        runtime.reset();

        waitForStart();
        // OP MODE STARTS
        //robot.moveTheEvan(1);
        //wait 2 seconds
        //robot.driveInInches(0.8, 1);
        //robot.moveTheEvan(-1);
        //wait 2 seconds
        // arm and robot should be down by now

        // Wait until location is determined
        robot.createLocationThread();
        robot.lt.start();
        while(robot.location < 0) {
            // Busy waiting
        }

        // Move until near samples
        robot.createDriveThread(0.2, robot.distanceToSamples);
        robot.dt.start();
        while(robot.dt.isAlive()) {
            // Busy waiting
        }
        runtime.reset();
        while (runtime.seconds() < 2){
            // allow time for things to stop
        }

        if (robot.location == 0) {
            // SW
            allTheWays(-0.2, -0.2);
        }
        else if (robot.location == 1) {
            // SE
            allTheWays(-0.2, 0.2);
        }
        else if (robot.location == 2) {
            // NW
            allTheWays(-0.2, 0.2);
        }
        else if (robot.location == 3){
            // NE
            allTheWays(-0.2, -0.2);
        }

        // placing team marker
        //robot.toggleMarker();

        // place the placer in it's place
        //robot.toggleMarker();

    }
    public void allTheWays(double rotatePower, double linearPower) {
        // turn to avoid silver
        robot.createRotateThread(-0.2, 90);
        robot.dt.start();
        while(robot.dt.isAlive()) {
            // busy waiting
        }
        startTimer();
        while (runtime.seconds() < 2) {
            //idle time
        }
        // driving away from sliver
         robot.createDriveThread(0.2, robot.distanceToAvoidMineral);
         robot.dt.start();
         while(robot.dt.isAlive()) {

         }
         startTimer();
         while (runtime.seconds() < 2){
             //idle time
         }
        //turing to the depot
        robot.createRotateThread(rotatePower, 45);
        robot.dt.start();
        while(robot.dt.isAlive()) {

        }
        startTimer();
        while (runtime.seconds() < 2){
            //idle time
        }

        // going to the depot
        robot.createDriveThread(linearPower, robot.distanceToDepot);
        robot.dt.start();
        while(robot.dt.isAlive()) {

        }
        startTimer();
        while (runtime.seconds() < 2){
            // idle time
        }

        /*// Turn towards crater
        robot.createRotateThread(-0.2,180);
        robot.dt.start();
        while(robot.dt.isAlive()) {

        }*/
        startTimer();
        while (runtime.seconds() < 2){
            //stop...
        }
        // Drive towards crater
        if (linearPower > 0){
            robot.createDriveThread(-0.2,robot.distanceFromDepotToCrater);
            robot.dt.start();
        }else{
            robot.createDriveThread(0.2, robot.distanceFromDepotToCrater);
        }

        while(robot.dt.isAlive()) {
            //hammer time!
        }

    }
    public void startTimer(){
        runtime.reset();
    }

}
