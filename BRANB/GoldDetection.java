package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class GoldDetection extends OpMode {
    VuforiaHardware robot = new VuforiaHardware();


    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void start(){
        robot.targetsRoverRuckus.activate();
    }

    @Override
    public void loop(){
        checkVuforiaControls();
        if (!robot.targetSeen && !robot.locThread.isAlive()) {
            robot.locThread.start();
        }
        telemetry.addData("Target Status: ", robot.targetSeen);
        if (robot.targetSeen){
            telemetry.addData("Which Target? ", robot.location);
        }
        telemetry.update();
    }

    @Override
    public void stop(){
        robot.resetLoc();
    }

    public void checkVuforiaControls(){
        if (gamepad1.a){
            robot.resetLoc();
        }
    }
}
