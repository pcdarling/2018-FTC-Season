package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Manual Mode", group="TeleOp")
public class CompetitionTeleOp extends OpMode {
    CompetitionHardware robot = new CompetitionHardware(false);

    double rtTresh = 0.03;
    boolean tankControls = false ;
    int start = 0;

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){
        checkDriverControls();
        checkOperatorControls();
        checkVuforiaControls();
        /*if (!robot.targetSeen && !robot.lt.isAlive()) {
            robot.lt.start();
        }
        telemetry.addData("Target Status: ", robot.targetSeen);
        if (robot.targetSeen){
            telemetry.addData("Which Target? ", robot.location);
        }*/
        telemetry.addData("liftPos: ", "%d", robot.liftM.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void stop(){
        robot.resetLoc();
    }

    public void checkDriverControls(){
        double Rx = gamepad1.right_stick_x;
        double Ry = gamepad1.right_stick_y;
        double Ly = gamepad1.left_stick_y;
        boolean rb = gamepad1.right_bumper;
        if (gamepad1.dpad_left){
            if (!tankControls) {
                tankControls = true;
            }
            else{
                tankControls = false;
            }
        }
                if (tankControls) {
                robot.tankcontrolsMovent(Ry, Ly, rb);

        } else {
            robot.FPSmovementByControl(Rx, Ly, rb);
        }
    }

    public void checkOperatorControls(){
        if (gamepad1.right_trigger > rtTresh){
            robot.liftM.setPower(0.5);
        }
        else if (gamepad1.left_trigger > rtTresh){
            robot.liftM.setPower(-0.5);
        }
        else{
            robot.liftM.setPower(0);
        }


        /*
        if (gamepad2.x && !robot.mt.isAlive()){
            robot.createMarkerThread();
            robot.mt.start();
        }

        if (gamepad2.a && (gamepad2.right_trigger > rtTresh) && !robot.ct.isAlive()) {
            robot.createClawThread();
            robot.ct.start();
        }
        */
    }

    public void checkVuforiaControls(){
        if (gamepad1.a){
            robot.resetLoc();
        }
    }
}
