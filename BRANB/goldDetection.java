package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class goldDetection extends OpMode {
    vuforiaHardware robot = new vuforiaHardware();

    @Override
    public void init(){

    }
    public void start(){
        robot.gameTrackables.activate();
    }
    public void loop(){
        if (){

        }
    }
    public void stop(){
        robot.gameTrackables.deactivate();
    }
}
