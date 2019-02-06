package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import java.util.Locale;

@Autonomous(name = "CompetitionAutoOp", group = "CompetitionBot")
public class CompetitionAutoOp extends LinearOpMode{
    CompetitionHardware robot = new CompetitionHardware(true);
    public GyroAnalysis gyroErrorAvg = new GyroAnalysis(30, 0 );
    private ElapsedTime runtime = new ElapsedTime();
    static final double P_DRIVE_COEFF_1 = 0.01;  // Larger is more responsive, but also less accurate; 0.01 was the original number here
    static final double P_DRIVE_COEFF_2 = 0.013;  // Intenionally large so robot "wiggles" around the target setpoint while driving

    double gyroCoef90 = 0.008;
    double gyroCoef45 = 0.015;
    double gyroCoef135 = 0.005;
    double gyroCoef7 = 0.022;
    double evanPower = 0.5;

    int HEADING_THRESHOLD = 5;

    public void runOpMode() {
        robot.imuInit(hardwareMap);
        robot.init(hardwareMap);
        sleep(50);
        runtime.reset();

        while(!isStarted()) {
            telemetry.addData("Lift Pos:",robot.theEvan.getCurrentPosition());
            telemetry.update();
        }

        /*
        // Lower Robot
        if (opModeIsActive()) {
            robot.createEvanThread(-evanPower);
            robot.et.start();
            while (robot.et.isAlive()) {
                // Busy Waiting
                telemetry.addData("Lift Pos: ",robot.theEvan.getCurrentPosition());
                telemetry.update();
            }
        }

        // Move away from hook
        if (opModeIsActive()) {
            encoderDrive(0.2, -4, 30, true, robot.getHeading(), true, true, 0);
            startTimer();
            while (runtime.seconds() < 2) {
                //idle time
            }
        }

        // Put the Evan back up
        if (opModeIsActive()) {
            robot.createEvanThread(evanPower);
            robot.et.start();
            while (robot.et.isAlive()) {
                // Busy Waiting
                // Could probably do without this busy waiting loop
            }
        }

        // Go back to starting position
        if (opModeIsActive()) {
            encoderDrive(0.2, 4, 30, true, robot.getHeading(), true, true, 0);
            startTimer();
            while (runtime.seconds() < 2) {
                //idle time
            }
        }

        // Rotate to get ready to go forward
        if (opModeIsActive()) {
            gyroTurn(0.5, robot.getHeading() - 90, 0.015);
            startTimer();
            while (runtime.seconds() < 2) {
                //idle time
            }
        }
        */

        // Move until near samples
        if (opModeIsActive()) {
            encoderDrive(0.2, robot.distanceToSamples, 30, true, robot.getHeading(), P_DRIVE_COEFF_2);
            startTimer();
            while (runtime.seconds() < 1) {
                // allow time for things to stop
            }
        }
        // turn to avoid silver
        if (opModeIsActive()) {
            gyroTurn(0.5, robot.getHeading() + 90, gyroCoef90);
            startTimer();
            while (runtime.seconds() < 1) {
                //idle time
            }
        }
        robot.phoneServo.setPosition(robot.phoneInPos);

        // driving away from sliver
        if (opModeIsActive()) {
            robot.createLocationThread();
            robot.lt.start();

//            encoderDrive(0.2, robot.distanceToAvoidMineral * 0.75, 30, true, robot.getHeading(), true, true, 0);
//            startTimer();
//            while (runtime.seconds() < 2 && robot.location != -1) {
//                //idle time
//                robot.scanPhone();
//                sleep(50);
//            }
//            robot.phoneServo.setPosition(robot.phoneInPos);
//            encoderDrive(0.2, robot.distanceToAvoidMineral * 0.25, 30, true, robot.getHeading(), true, true,  0);
            encoderDrive(0.4, robot.distanceToAvoidMineral * 0.60, 30, true, robot.getHeading(), P_DRIVE_COEFF_2);
        }

        if (opModeIsActive() && robot.location == -1) {
            gyroTurn(0.5, robot.getHeading() - 45, gyroCoef45);
            startTimer();
            while (runtime.seconds() < 1) {
                // idle time
            }
            if (opModeIsActive()){
                gyroTurn(0.5, robot.getHeading() + 40, gyroCoef45);
            }
            if (opModeIsActive()){
                //not exactly 100%, but the robot will come up short otherwise
                encoderDrive(0.4, robot.distanceToAvoidMineral * 0.52, 30, true, robot.getHeading(), P_DRIVE_COEFF_2);
            }
        }else{
            if (opModeIsActive()){
                encoderDrive(0.4, robot.distanceToAvoidMineral * 0.45, 30, true, robot.getHeading(), P_DRIVE_COEFF_2);
            }
        }


        //TODO: THE LAST TURN FOR AUTONOMOUS SEEMS TO BE NO LONGER NECESSARY; TAKE IT OUT ASAP.
        if (opModeIsActive()) {
            if (robot.location == 0) {
                // SW
                theAllWays(true);
            } else if (robot.location == 1) {
                // SE
                theAllWays(false);
            } else if (robot.location == 2) {
                // NW
                theAllWays(false);
            } else if (robot.location == 3) {
                // NE
                theAllWays(true);
            }
        }


    }

    public void theAllWays(boolean depot) {
        //turing to the depot
        if (depot){
            if (opModeIsActive()) {
                gyroTurn(Math.abs(0.8), robot.getHeading() + 40, gyroCoef45);
                startTimer();
                while (runtime.seconds() < 1) {
                    //idle time
                }
            }

            // going to the depot
            if (opModeIsActive()) {
                encoderDrive(0.35, -robot.distanceToDepot, 30, true, robot.getHeading(), 0.005);
                startTimer();
                while (runtime.seconds() < 1) {
                    // idle time
                }
            }

            //here would be the code for dropping the team marker, but the team marker dropper doesn't exist physically yet, so
            // placing team marker
            if (opModeIsActive()) {
                robot.markerMover.setPosition(robot.ejectPos);
                startTimer();
                while (runtime.seconds() < 2) {
                    //stop...
                }
                //gyroTurn(0.8,robot.getHeading()-(Math.signum(rotatePower)*7),0.03);

                //the potential "we can't get the robot to not hit the other team's samples" turn
                //gyroTurn(0.2,-90,gyroCoeff90)
            }
            //WIGGLES after dropping the marker
            if (opModeIsActive()) {
                gyroTurn(Math.abs(0.8), robot.getHeading() - 7, gyroCoef7);
                startTimer();
                while (runtime.seconds() < 1) {
                    //idle time
                }
            }

            // Drive towards crater (Hammer time!)
            if (opModeIsActive()) {

                encoderDrive(0.6, robot.distanceFromDepotToCrater, 30, true, robot.getHeading(), 0.005);

                robot.markerMover.setPosition(robot.storePos);
            }
        } else {

            if (opModeIsActive()) {
                gyroTurn(Math.abs(-0.8), robot.getHeading() - 135, gyroCoef135);
                startTimer();
                while (runtime.seconds() < 1) {
                    //idle time
                }
            }

            // going to the depot
            if (opModeIsActive()) {
                encoderDrive(0.35, -robot.distanceToDepot*0.7, 30, true, robot.getHeading(), P_DRIVE_COEFF_1);
                startTimer();
                while (runtime.seconds() < 1) {
                    // idle time
                }
            }

            //here would be the code for dropping the team marker, but the team marker dropper doesn't exist physically yet, so
            // placing team marker
            if (opModeIsActive()) {
                robot.markerMover.setPosition(robot.ejectPos);
                startTimer();
                while (runtime.seconds() < 2) {
                    //stop...
                }
                //gyroTurn(0.8,robot.getHeading()-(Math.signum(rotatePower)*7),0.03);

                //the potential "we can't get the robot to not hit the other team's samples" turn
                //gyroTurn(0.2,-90,gyroCoeff90)
            }
            //WIGGLES after dropping the marker
            if (opModeIsActive()) {
                gyroTurn(Math.abs(0.8), robot.getHeading() + 8, gyroCoef7);
                startTimer();
                while (runtime.seconds() < 1) {
                    //idle time
                }
            }

            // Drive towards crater (Hammer time!)
            if (opModeIsActive()) {

                encoderDrive(0.6, robot.distanceFromDepotToCrater+8, 30, true, robot.getHeading(), 0.005);

                robot.markerMover.setPosition(robot.storePos);
            }
        }
    }

    public void startTimer(){
        runtime.reset();
    }

    // Ian attach the code from the GryoStraightener code 12/27/2018

    public void encoderDrive(double speed,
                             double distance,
                             double timeout,
                             boolean useGyro,
                             double heading,
                             double gyroCoeff) {

        // Calculated encoder targets
        int newLFTarget;
        int newRFTarget;
        int newLRTarget;
        int newRRTarget;

        // The potentially adjusted current target heading
        double curHeading = heading;

        // Speed ramp on start of move to avoid wheel slip
        final double MINSPEED = 0.30;           // Start at this power
        final double SPEEDINCR = 0.015;         // And increment by this much each cycle
        double curSpeed;                        // Keep track of speed as we ramp

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            RobotLog.i("DM10337- Starting encoderDrive speed:" + speed +
                    "  distance:" + distance + "  timeout:" + timeout +
                    "  useGyro:" + useGyro + " heading:" + heading);

            // Calculate "adjusted" distance  for each side to account for requested turn during run
            // Purpose of code is to have PIDs closer to finishing even on curved moves
            // This prevents jerk to one side at stop
            double leftDistance = distance;
            double rightDistance = distance;
            if (useGyro) {
                // We are gyro steering -- are we requesting a turn while driving?
                double headingChange = getError(curHeading) * Math.signum(distance);
                if (Math.abs(headingChange) > 5.0) {
                    //Heading change is significant enough to account for
                    if (headingChange > 0.0) {
                        // Assume 15.25 inch wheelbase
                        // Add extra distance to the wheel on outside of turn
                        rightDistance += Math.signum(distance) * 2 * 3.1415 * 12 * headingChange / 360.0;
                        RobotLog.i("DM10337 -- Turn adjusted R distance:" + rightDistance);
                    } else {
                        // Assume 15.25 inch wheelbase
                        // Add extra distance from the wheel on inside of turn
                        // headingChange is - so this is increasing the left distance
                        leftDistance -= Math.signum(distance) * 2 * 3.1415 * 12 * headingChange / 360.0;
                        RobotLog.i("DM10337 -- Turn adjusted L distance:" + leftDistance);
                    }
                }
            }

            // Determine new target encoder positions, and pass to motor controller
            newLFTarget = robot.motors[0].getCurrentPosition() + (int) (leftDistance * robot.countsPerInch);
            newLRTarget = robot.motors[2].getCurrentPosition() + (int) (leftDistance * robot.countsPerInch);
            newRFTarget = robot.motors[1].getCurrentPosition() + (int) (rightDistance * robot.countsPerInch);
            newRRTarget = robot.motors[3].getCurrentPosition() + (int) (rightDistance * robot.countsPerInch);

            while (robot.motors[0].getTargetPosition() != newLFTarget) {
                robot.motors[0].setTargetPosition(newLFTarget);
                sleep(1);
            }
            while (robot.motors[1].getTargetPosition() != newRFTarget) {
                robot.motors[1].setTargetPosition(newRFTarget);
                sleep(1);
            }
            while (robot.motors[2].getTargetPosition() != newLRTarget) {
                robot.motors[2].setTargetPosition(newLRTarget);
                sleep(1);
            }
            while (robot.motors[3].getTargetPosition() != newRRTarget) {
                robot.motors[3].setTargetPosition(newRRTarget);
                sleep(1);
            }

            // Turn On motors to RUN_TO_POSITION
            robot.motors[0].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motors[1].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motors[2].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motors[3].setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            robot.runtime.reset();

            speed = Math.abs(speed);    // Make sure its positive
            curSpeed = Math.min(MINSPEED, speed);

            // Set the motors to the starting power
            robot.motors[0].setPower(Math.abs(curSpeed));
            robot.motors[1].setPower(Math.abs(curSpeed));
            robot.motors[2].setPower(Math.abs(curSpeed));
            robot.motors[3].setPower(Math.abs(curSpeed));

            // keep looping while we are still active, and there is time left, until at least 1 motor reaches target
            while (opModeIsActive() &&
                    (robot.runtime.seconds() < timeout) &&
                    robot.motors[0].isBusy() &&
                    robot.motors[2].isBusy() &&
                    robot.motors[1].isBusy() &&
                    robot.motors[3].isBusy()) {

                // Ramp up motor powers as needed
                if (curSpeed < speed) {
                    curSpeed += SPEEDINCR;
                }
                double leftSpeed = curSpeed;
                double rightSpeed = curSpeed;

                // Doing gyro heading correction?
                if (useGyro) {

                    // adjust relative speed based on heading
                    double error = getError(curHeading);

                    updateGyroErrorAvg(error);

                    double steer = getSteer(error, gyroCoeff);

                    // if driving in reverse, the motor correction also needs to be reversed
                    if (distance < 0)
                        steer *= -1.0;

                    // Adjust motor powers for heading correction
                    leftSpeed -= steer;
                    rightSpeed += steer;

                    // Normalize speeds if any one exceeds +/- 1.0;
                    double max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
                    if (max > 1.0) {
                        leftSpeed /= max;
                        rightSpeed /= max;
                    }

                }

                // And rewrite the motor speeds
                robot.motors[0].setPower(Math.abs(leftSpeed));
                robot.motors[1].setPower(Math.abs(rightSpeed));
                robot.motors[2].setPower(Math.abs(leftSpeed));
                robot.motors[3].setPower(Math.abs(rightSpeed));

                // Allow time for other processes to run.
                sleep(1);

            }


            RobotLog.i("DM10337- encoderDrive done" +
                    "  lftarget: " + newLFTarget + "  lfactual:" + robot.motors[0].getCurrentPosition() +
                    "  lrtarget: " + newLFTarget + "  lractual:" + robot.motors[2].getCurrentPosition() +
                    "  rftarget: " + newRFTarget + "  rfactual:" + robot.motors[1].getCurrentPosition() +
                    "  rrtarget: " + newRFTarget + "  rractual:" + robot.motors[3].getCurrentPosition() +
                    "  heading:" + readGyro());

            //  RobotLog.i("DM10337 - Gyro error average: " + gyroErrorAvg.average());

            // Stop all motion;
            robot.motors[0].setPower(0);
            robot.motors[2].setPower(0);
            robot.motors[1].setPower(0);
            robot.motors[3].setPower(0);

            // Turn off RUN_TO_POSITION
            robot.motors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motors[2].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motors[3].setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void initDataAnalysis(){
        for (int i = 0; i < gyroErrorAvg.size; i++){
            gyroErrorAvg.add(robot.getHeading());
        }
    }

    public void updateGyroErrorAvg(double error) {
        //  gyroErrorAvg.add(Math.abs(error));
    }

    public void gyroTurn(double speed, double angle, double coefficient) {

        telemetry.addLine("DM10337- gyroTurn start  speed:" + speed +
                "  heading:" + angle);

        // keep looping while we are still active, and not on heading.
        while (opModeIsActive() && !onHeading(speed, angle, coefficient)) {
            // Allow time for other processes to run.
            // onHeading() does the work of turning us
            sleep(1);

        }

        telemetry.addLine("DM10337- gyroTurn done   heading actual:" + readGyro());
    }


    /**
     * Perform one cycle of closed loop heading control.
     *
     * @param speed  Desired speed of turn.
     * @param angle  Absolute Angle (in Degrees) relative to last gyro reset.
     *               0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
     *               If a relative angle is required, add/subtract from current heading.
     * @param PCoeff Proportional Gain coefficient
     * @return
     */
    boolean onHeading(double speed, double angle, double PCoeff) {
        double error;
        double steer;
        boolean onTarget = false;
        double leftSpeed;
        double rightSpeed;

        // determine turn power based on +/- error
        error = getError(angle);

        if (Math.abs(error) <= HEADING_THRESHOLD) {
            // Close enough so no need to move
            steer = 0.0;
            leftSpeed = 0.0;
            rightSpeed = 0.0;
            onTarget = true;
        } else {
            // Calculate motor powers
            steer = getSteer(error, PCoeff);
            rightSpeed = speed * steer;
            leftSpeed = -rightSpeed;
        }

        // Send desired speeds to motors.
        robot.motors[0].setPower(leftSpeed);
        robot.motors[1].setPower(rightSpeed);
        robot.motors[2].setPower(leftSpeed);
        robot.motors[3].setPower(rightSpeed);

        return onTarget;
    }

    /**
     * getError determines the error between the target angle and the robot's current heading
     *
     * @param targetAngle Desired angle (relative to global reference established at last Gyro Reset).
     * @return error angle: Degrees in the range +/- 180. Centered on the robot's frame of reference
     * +ve error means the robot should turn LEFT (CCW) to reduce error.
     */
    public double getError(double targetAngle) {

        double robotError;

        // calculate error in -179 to +180 range  (
        robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        robotError = targetAngle - robot.getHeading();
        while (robotError > 180) robotError -= 360;
        while (robotError <= -180) robotError += 360;
        return robotError;
    }

    /**
     * returns desired steering force.  +/- 1 range.  +ve = steer left
     *
     * @param error  Error angle in robot relative degrees
     * @param PCoeff Proportional Gain Coefficient
     * @return
     */
    public double getSteer(double error, double PCoeff) {
        return Range.clip(error * PCoeff, -1, 1);
    }


    /**
     * Record the current heading and use that as the 0 heading point for gyro reads
     *
     * @return
     */
    void zeroGyro() {
        double headingBias;
        robot.angles = robot.imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX);
        headingBias = robot.getHeading();
    }

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                robot.angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                robot.gravity = robot.imu.getGravity();
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                        return robot.imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                        return robot.imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(robot.angles.angleUnit, robot.angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(robot.angles.angleUnit, robot.angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(robot.angles.angleUnit, robot.angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override
                    public String value() {
                        return robot.gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override
                    public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(robot.gravity.xAccel * robot.gravity.xAccel
                                        + robot.gravity.yAccel * robot.gravity.yAccel
                                        + robot.gravity.zAccel * robot.gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }


    // /**
    //  * Read the current heading direction.  Use a heading bias if we recorded one at start to account for drift during
    //  * the init phase of match
    //  *
    //  * @return      Current heading (Z axis)
    //  */
    double readGyro() {
        double headingBias = robot.getHeading();
        robot.angles = robot.imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX);
        return robot.getHeading() - headingBias;
    }
}