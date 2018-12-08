package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Distance_Sensor extends LinearOpMode {
    public DistanceSensor ds;

    @Override

    public void runOpMode() {
        ds = hardwareMap.get(DistanceSensor.class, "Distance_sonsor");

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) Distance_Sensor;

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("range", String.format("%.01f cm", Distance_Sensor.getDistance(DistanceUnit.CM)));

            telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
            telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

            telemetry.update();
        }
    }
}
