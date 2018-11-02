package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
// TODO: not put @Disabled here
public class TheEvanTeleOp extends OpMode
{

    theEvanHardware robot = new theEvanHardware();

    //this may need to be changed later
    double rtThresh = 0.85;
    double stickThresh = 0.06;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }


    @Override
    public void init_loop() {

    }


    @Override
    public void start() {

    }

    @Override
    public void loop() {
        double RT = gamepad2.right_trigger;
        double RY = gamepad2.right_stick_y;
//        boolean A = gamepad2.a;

        //the bolow is an conditionall operator (it makes logic work)
//        if(A && (RT > rtThresh){
//            robot.toggleClaw();
//        }
        if((Math.abs(RY) > stickThresh) && (RT > rtThresh)){
            robot.moveTheEvan(RY);
        }
    }

    @Override
    public void stop() {

    }

}
