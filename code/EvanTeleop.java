package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "evanteleop")
public class EvanTeleop extends LinearOpMode {
    EvanHardware robot = new EvanHardware();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            double lefty = gamepad1.left_stick_y;
            boolean buttonA = gamepad1.a;
            boolean buttonB = gamepad1.b;
            boolean buttonR = gamepad1.right_bumper;
            boolean buttonL = gamepad1.left_bumper;
            boolean buttonY = gamepad1.y;

            if(buttonY){
                robot.flipRobot(1,true);
            }
            if (lefty > 0.06 ) {
                robot.bicep.setPower(lefty);
            }
            else if (lefty < -0.06) {
                robot.bicep.setPower(lefty);
            }
            else if(buttonR){
                robot.bicep.setPower(-1);
            }
            else if (buttonL){
                robot.bicep.setPower(0.1);
            }
            else {
                robot.bicep.setPower(0);
            }

            if (buttonA) {
                robot.latch(true);
            }
            if (buttonB) {
                robot.latch(false);
            }

            telemetry.addData("distance to ground",robot.groundSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        robot.latchServo.setPosition(robot.latchingServoOpen);
    }
}