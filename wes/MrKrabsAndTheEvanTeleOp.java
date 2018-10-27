package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class MrKrabsAndTheEvanTeleOp extends OpMode
{

    MrKrabsAndTheEvanHardware robot = new MrKrabsAndTheEvanHardware();
    
       
        double RY = gamepad2.right_stick_y;
        

    //this may need to be changed later
    double rtThresh = 0.85;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }


    @Override
    public void start() {

    }

    @Override
    public void loop() {
       checkOperatorControls();
        //the bolow is an conditionall operator (it makes logic work)
    }


    @Override
    public void stop() {

    }
    
    public void checkOperatorControls(){
    if (gamepad2.a &&(gamepad2.right_trigger > rtTresh){
    robot.toggleClaw();
    }

}
