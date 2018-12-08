package org.firstinspires.ftc.teamcode;
import android.hardware.Sensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class TSMLSHardwareMap {
    TouchSensor touchsensor;
    DigitalChannel magneticSwitch;
    boolean touchIsPress;
    boolean magneticIsPress;

    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap) {
        /* Save reference to Hardware map */
        hwMap = ahwMap;
        touchsensor = hwMap.get(TouchSensor.class, "ITTouch");
        magneticSwitch = hwMap.get(DigitalChannel.class, "mag");

        magneticIsPress = magneticSwitch.getState();
        touchIsPress = touchsensor.isPressed();
    }
}
