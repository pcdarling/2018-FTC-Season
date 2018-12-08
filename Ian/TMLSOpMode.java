package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Ian is awesome", group="Iterative Opmode")
public class TMLSOpMode extends OpMode {
    TSMLSHardwareMap robot = new TSMLSHardwareMap();

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop () {
        telemetry.addData("Touch Senor",robot.touchsensor.isPressed());

        telemetry.addData("Magnetic senor",robot.magneticSwitch.getState());
        telemetry.update();
    }
}
