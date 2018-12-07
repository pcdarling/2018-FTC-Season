package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class intakeOpMode extends OpMode {
   // intakeHardware robot = new intakeHardware();
    Servo INTServo;
    HardwareMap Bob;
    boolean encoder = false;
    @Override
    public void init() {
//        robot.init(hardwareMap);
            INTServo = Bob.get(Servo.class, "InTServo");

    }
    @Override
    //to start the the robot
    public void start() {
    }
    @Override
    // it adds the movement fuction to the start of the robot planes (driver control).
    public void loop() {
        double power = 0.06;
        boolean a = gamepad1.a;
        boolean x = gamepad1.x;
        boolean y = gamepad1.y;
        boolean b = gamepad1.b;
        boolean A = gamepad1.dpad_left;
        boolean B = gamepad1.dpad_right;
        boolean rBumper = gamepad1.right_bumper;
       /* if (encoder) {
        }
        else {
           while (a && rBumper) {
                // intank header ativaction
                robot.intakeSuccc(power);
            }
            while (x && rBumper) {
                robot.intakeSuccc(-power);
            }
            while (x) {
                // lift and lower
                robot.moveIntake(power, x, a);
            }
            while (y) {
                // rasied and lower
                robot.intakeArmRaiseLower(power, y, b);
            }
        }*/

    }
}
