package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class IntakeOpMode extends OpMode {
    IntakeHardware robot = new IntakeHardware();
    boolean encoder = false;
    @Override
    public void init() { robot.init(hardwareMap);
    }
    @Override
    //to start the the robot
    public void start() {
    }
    @Override
    // it adds the movement fuction to the start of the robot planes (driver control).
    public void loop() {
        double power = 1;
        boolean a = gamepad2.a;
        boolean x = gamepad2.x;
        boolean y = gamepad2.y;
        boolean b = gamepad2.b;
        boolean rBumper = gamepad2.right_bumper;
        boolean leftBumper = gamepad2.left_bumper;
        robot.intakeSuccc(power, rBumper,leftBumper);
        robot.moveIntake(power, x, a);
        robot.intakeArmRaiseLower(power, y, b);
    }
}