package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class intakeOpMode extends OpMode {
    inTakeHardware robot = new inTakeHardware();
    Servo[] INTServo = new Servo[1];
    boolean encoder = false;


    @Override
    public void init() {
    }

    @Override
    //to start the the robot
    public void start() {
    }

    @Override
    // it adds the movement fuction to the start of the robot planes (driver control).
    public void loop() {
        double power = 0.06;
        double  gameP2LSy = gamepad2.left_stick_y;
        boolean gameP2a = gamepad2.a;
        boolean gameP2x = gamepad2.x;
        boolean gameP2y = gamepad2.y;
        boolean gameP2b = gamepad2.b;
        boolean gameP2DpUp = gamepad2.dpad_up;
        boolean gameP2Dpdown = gamepad2.dpad_down;

        if(gameP2a) {
            robot.intakeActivateHeader(power);
        }
        if(gameP2x){
            robot.inTakePivot( power, gameP2DpUp,gameP2Dpdown);
        }
        if (gameP2y){
            robot.inTankExtend(power,gameP2LSy);
        }
        telemetry.addData("Drawer pos: ", "%d", robot.intakeMotor[1].getCurrentPosition());
        telemetry.update();
    }

    public void checkOperatorControls(){

    }
}

