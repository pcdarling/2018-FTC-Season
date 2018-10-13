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

    }

    @Override
    public void start(){
        robot.targetsRoverRuckus.activate();
    }

    @Override
    public void loop(){
        targetVisible = false;
        for (VuforiaTrackable trackable: robot.allTrackables) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                telemetry.addData("Visible Target", trackable.getName());
                targetVisible = true;
            }
        }
    }

    @Override
    public void stop(){
        robot.gameTrackables.deactivate();
    }
}
