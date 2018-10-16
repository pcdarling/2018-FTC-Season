package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class driveTrainOpMode extends OpMode {
    driveTrainHardwareMapArrays robot = new driveTrainHardwareMapArrays();
    boolean tankControls = false;

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    //to start the the robot
    public void start(){
        double xA = 0.06;
        double xB =14.13 ;
        boolean xC = false;
        robot.inchesmovement(xA,xB,xC);

    }
    @Override
    // it adds the movement fuction to the start of the robot planes (driver control).
    public void loop() {
        double Rx = gamepad1.right_stick_x;
        double Ry = gamepad1.right_stick_y;
        double Ly = gamepad1.left_stick_y;
        if(tankControls) {
            robot.tankcontrolsMovent(Ry,Ly);

        }
        else {
            robot.movementByControl(Rx,Ly);
        }

    }

}
