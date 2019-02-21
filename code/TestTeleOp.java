package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//TODO: This is for testing purposes only, if the code being tested here is working and needs to stay, place in another file, otherwise it *will* be deleted
@TeleOp(name = "Test Tele", group = "Testing")
public class TestTeleOp extends OpMode {

    CompetitionHardware robot = new CompetitionHardware(true);
    Rev2mDistanceSensor ds;

    public void init(){
        robot.init(hardwareMap);

    }
    public void init_loop(){}
    public void start(){}
    public void loop(){
        telemetry.addData("Distance to Ground",robot.evan.groundSensor.getDistance(DistanceUnit.CM));
        robot.cam.findGold();
        telemetry.addData("Gold Confidence: ", robot.cam.goldLooks);

        telemetry.addData("Gamepad 1 L-x: ", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 R-x:" , gamepad1.right_stick_x);
        telemetry.addData("Gamepad 1 a", gamepad1.a);
        telemetry.addData("Gamepad 1 B", gamepad1.b);
        telemetry.addData("Gamepad 1 X", gamepad1.x);
        telemetry.addData("Gamepad 1 Y", gamepad1.y);

        telemetry.update();
    }
    public void stop(){}

    //TODO: Make sure functions you need go with the component code being tested

}