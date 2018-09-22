package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class driveTrainOpMode extends OpMode
{
    driveTrainHardwareMap robot = new driveTrainHardwareMap();

    @Override
    public void init(){
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start(){
    }
    @Override
    public void loop() {
        double gamepad1SpeedND = gamepad1.left_stick_y;
        double gamepad1T = gamepad1.left_stick_x;
        double power = 0.06;
        double rewop = -0.06;

        //turning the robot!!
        if(Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND) ||
        Math.abs(gamepad1T) > Math.abs(gamepad1SpeedND)){
           //counter clock wise
            if(gamepad1T > power){
                robot.right1.setPower(gamepad1T);
                robot.right2.setPower(gamepad1T);
                robot.left1.setPower(-gamepad1T);
                robot.left2.setPower(-gamepad1T);
            }
            // clock wise
            else if(gamepad1T < rewop){
                robot.right1.setPower(-gamepad1T);
                robot.right2.setPower(-gamepad1T);
                robot.left1.setPower(gamepad1T);
                robot.left2.setPower(gamepad1T);
            }
            else{
                robot.right1.setPower(0);
                robot.right2.setPower(0);
                robot.left1.setPower(0);
                robot.left2.setPower(0);
            }
        }
        //normal driving!
        // right
       else{
            if (gamepad1SpeedND > power) {
                robot.right1.setPower(gamepad1SpeedND);
                robot.right2.setPower(gamepad1SpeedND);
                robot.left1.setPower(gamepad1SpeedND);
                robot.left2.setPower(gamepad1SpeedND);
            } else if (gamepad1SpeedND < rewop) {
                robot.right1.setPower(gamepad1SpeedND);
                robot.right2.setPower(gamepad1SpeedND);
                robot.left1.setPower(-gamepad1SpeedND);
                robot.left2.setPower(-gamepad1SpeedND);
            } else {
                robot.right1.setPower(0);
                robot.right2.setPower(0);
                robot.left1.setPower(0);
                robot.left2.setPower(0);
            }
        }
    }
}

