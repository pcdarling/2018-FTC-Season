package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "mountServo", group = "testOps")
public class MountServoOp extends OpMode{

    public MountServoHardware robot =  new MountServoHardware();
    double mountCurPos;

    public void init(){
        robot.init(hardwareMap);
    }
    public void start(){

    }
    public void loop(){
        checkOperatorControls();
        telemetry.addData("pos ", "current pos is:" + mountCurPos);
        telemetry.update();
    }
    public void stop(){
        robot.mountServo.setPosition(0.5);
    }
    public void checkOperatorControls(){
        mountCurPos = robot.mountServo.getPosition();
        if(gamepad1.dpad_left){
            robot.mountServo.setPosition(mountCurPos-0.1);
        }
        else if(gamepad1.dpad_right){
            robot.mountServo.setPosition(mountCurPos+0.1);
        }
        else{
            robot.mountServo.setPosition(mountCurPos);
        }
    }
}