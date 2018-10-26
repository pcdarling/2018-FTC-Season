package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

@TeleOp
public class GoldDetection extends OpMode {
    VuforiaHardware robot = new VuforiaHardware();
    boolean targetVisible;

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
        robot.determineLoc();
        telemetry.addData("Target Status: ", robot.targetVisible);
        if (robot.targetVisible){
            telemetry.addData("Which Target? ", robot.nTrackable);
        }
        telemetry.update();
    }

    @Override
    public void stop(){
    }
}
