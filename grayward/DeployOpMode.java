package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "deployOpMode",group = "testing")
public class DeployOpMode extends OpMode {
    DeployHardware robot = new DeployHardware();
    public void init(){
       robot.init(hardwareMap);
    }
    public void start (){

    }

    public void loop () {
        checkOperatorControls();
        telemetry.addData("HookPos: ", "%d", robot.dMotor.getCurrentPosition());
    }

    public void stop (){

    }
    public void checkOperatorControls(){
        if(gamepad1.left_stick_y > 0.06){
            robot.dMotor.setPower(gamepad1.left_stick_y);
        }
        if(gamepad1.left_stick_y > -0.06){
            robot.dMotor.setPower(gamepad1.left_stick_y);
        }
    }
}

