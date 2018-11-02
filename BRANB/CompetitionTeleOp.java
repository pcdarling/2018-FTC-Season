package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Manual Mode", group="TeleOp")
public class CompetitionTeleOp extends OpMode {
    CompetitionHardware robot = new CompetitionHardware();

    double rtTresh = 0.06;
    boolean tankControls = true;
    int start = 0;

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
        checkDriverControls();
        checkOperatorControls();
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

    public void checkDriverControls(){
    // all that fun stuff yee TODO: put that fun stuff in Brandon.
        double Rx = gamepad1.right_stick_x;
        double Ry = gamepad1.right_stick_y;
        double Ly = gamepad1.left_stick_y;
        if (tankControls) {
            robot.tankcontrolsMovent(Ry, Ly);

        } else {
            robot.FPSmovementByControl(Rx, Ly);
        }
    }

    public void checkOperatorControls(){
        if (gamepad2.x){
            robot.toggleMarker();
        }

        if (gamepad2.a && (gamepad2.right_trigger > rtTresh)){
            robot.toggleClaw();
        }
    }

    public void checkVuforiaControls(){
        if (gamepad1.a){
            robot.resetLoc();
        }
    }
}
