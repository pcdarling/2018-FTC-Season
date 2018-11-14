package org.firstinspires.ftc.teamcode;
import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class inTankOpMode extends OpMode {
    inTankHardware robot = new inTankHardware();
    Servo[] INTServo = new Servo[1];
    HardwareMap ITHwMap = null;


    @Override
    public void init(){
        robot.init(hardwareMap);
        for (int i = 0; i < INTServo.length; i++) {
            INTServo[i] = ITHwMap.get(Servo.class, "InTServo");

        }
    }
    @Override
    //to start the the robot
    public void start() {
    }
    @Override
    public void loop(){
        boolean a = gamepad1.dpad_left;
        boolean b = gamepad1.dpad_right;

        if (a){
            INTServo[0].setPosition(1);
        }
        if (b){
            INTServo[0].setPosition(-1);
        }
        else {
            INTServo[0].setPosition(0);
        }
    }
//    @Override
//    // it adds the movement fuction to the start of the robot planes (driver control).
//    public void loop () {
//        double power = 0.06;
//       boolean a = gamepad1.a;//used
//        boolean bumper = gamepad1.right_bumper;//used
//
//        boolean x = gamepad1.x;//used
//       boolean y = gamepad1.y;
//       boolean b = gamepad1.b;
//
//        while(a && bumper){
//            // intank header ativaction
//            robot.inTankHeader(power);
//        }
//        while(x && bumper){
//            robot.inTankHeader(-power);
//        }
//        while(x){
//            // lift and lower
//            robot.inTankArmLiftLower(power , x, a);
//        }
//        while(y){
//            // rasied and lower
//            robot.inTankArmRiseLower(power, y , b);
//        }
//    }
}
