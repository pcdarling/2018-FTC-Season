package org.firstinspires.ftc.teamcode;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class driveTrainOpMode extends OpMode {
    driveTrainHardwareMapArrays robot = new driveTrainHardwareMapArrays();
    boolean tankControls = false;
    int start = 0;

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
        double Rx = gamepad1.right_stick_x;
        double Ry = gamepad1.right_stick_y;
        double Ly = gamepad1.left_stick_y;
        if (tankControls) {
            tankcontrolsMovent(Ry, Ly);

        } else {
            FPSmovementByControl(Rx, Ly);
        }

    }

    public void tankcontrolsMovent ( double gamepad1Ry, double gamepad1Ly){
        if (gamepad1Ly > robot.thresh || gamepad1Ly < robot.thresh) {
            robot.motors[0].setPower(gamepad1Ly);
            robot.motors[2].setPower(gamepad1Ly);
        } else {
            robot.motors[0].setPower(0);
            robot.motors[2].setPower(0);
        }
        if (gamepad1Ry > robot.thresh || gamepad1Ry < robot.thresh) {
            robot.motors[1].setPower(-gamepad1Ry);
            robot.motors[3].setPower(-gamepad1Ry);

        } else {
            robot.motors[1].setPower(0);
            robot.motors[3].setPower(0);
        }

    }

    public void FPSmovementByControl ( double gamepad1SpeedND, double gamepad1T){

        if (Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)) {
            if (gamepad1T > robot.thresh) {
                robot.motors[0].setPower(-gamepad1T);
                robot.motors[1].setPower(gamepad1T);
                robot.motors[2].setPower(-gamepad1T);
                robot.motors[3].setPower(gamepad1T);
            } else if (gamepad1T < -robot.thresh) {
                robot.motors[0].setPower(-gamepad1T);
                robot.motors[1].setPower(gamepad1T);
                robot.motors[2].setPower(-gamepad1T);
                robot.motors[3].setPower(gamepad1T);

            } else {
                robot.motors[0].setPower(0);
                robot.motors[1].setPower(0);
                robot.motors[2].setPower(0);
                robot.motors[3].setPower(0);
            }

        } else {
            //saying if gamepad1SpeedND is greater than power that is 0.06 than move Motors[i] in a postive way.
            if (gamepad1SpeedND > robot.thresh) {
                robot.motors[0].setPower(-gamepad1SpeedND);
                robot.motors[1].setPower(-gamepad1SpeedND);
                robot.motors[2].setPower(gamepad1SpeedND);
                robot.motors[3].setPower(gamepad1SpeedND);
            }
            //saying if gamepad1SpeedND is lessthan than rewop that is -0.06 than move Motors[i] in a negative way.
            else if (gamepad1SpeedND < -robot.thresh) {
                robot.motors[0].setPower(-gamepad1SpeedND);
                robot.motors[1].setPower(-gamepad1SpeedND);
                robot.motors[2].setPower(gamepad1SpeedND);
                robot.motors[3].setPower(gamepad1SpeedND);
            }
            //saying if the Motor[i] is not doing anything than have the power of the motor to 0.
            else {
                robot.motors[0].setPower(0);
                robot.motors[1].setPower(0);
                robot.motors[2].setPower(0);
                robot.motors[3].setPower(0);

            }
        }
    }
}
