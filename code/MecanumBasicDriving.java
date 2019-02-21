package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Mecanum Basic Driving", group = "Testing")
public class MecanumBasicDriving extends OpMode {
    MecanumHardware robot = new MecanumHardware();

    double tresh = 0.06;

    public void init(){
        robot.init(hardwareMap);
    }
    public void start(){

    }
    public void loop(){
        // if you're *really* trying to drive forward/backward
        if (Math.abs(gamepad1.left_stick_y) > tresh && Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x) && Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.right_stick_x)){
            robot.powerDriveTrain(gamepad1.left_stick_y, false, false);

            // if you're *really* trying to strafe
        }else if (Math.abs(gamepad1.left_stick_x) > tresh && Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y) && Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.right_stick_x)){
            robot.powerDriveTrain(gamepad1.left_stick_x,true ,false);

            // if you're *really* trying to rotate
        }else if (Math.abs(gamepad1.right_stick_x) > tresh && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_y) && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_x)){
            robot.powerDriveTrain(gamepad1.right_stick_x, false, true);


        }else{ // none of the above is true and the cake is a lie
            robot.powerDriveTrain(0, false, false);
        }
    }
    public void stop(){
        robot.powerDriveTrain(0, false, false);
    }
}