package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class intakeOpMode extends OpMode {
    intakeHardware robot = new intakeHardware();
    // Servo INTServo;
    // HardwareMap Bob;
    boolean encoder = false;
    @Override
    public void init() {
//        robot.init(hardwareMap);
        //        INTServo = Bob.get(Servo.class, "InTServo");

    }
    @Override
    //to start the the robot
    public void start() {
    }
    @Override
    // it adds the movement fuction to the start of the robot planes (driver control).
    public void loop() {
        double power = 0.1;
        boolean a = gamepad2.a;
        boolean x = gamepad2.x;
        boolean y = gamepad2.y;
        boolean b = gamepad2.b;
        boolean A = gamepad2.dpad_left;
        boolean B = gamepad2.dpad_right;
        boolean rBumper = gamepad2.right_bumper;

        robot.intakeSuccc(power, rBumper);
        robot.moveIntake(power , x  , a);
        robot.intakeArmRaiseLower(power ,y ,b);

    }
}