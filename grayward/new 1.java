package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.TouchSensor;

public class PuchSensorGray {
    public TouchSensor GrayTouch;

    @Override
    public void runOpMode() {
        GrayTouch = hardwareMap.get(TouchSensor.class, "grayTouch");

        waitforstart();

        while (opModeIsActive()) {


            if (grayTouch.isPressed())
                telemetry.addData("Touch", "Is Pressed all hail gray");
            else
                telemetry.addData("Touch", "Is Not Pressed gray is no god");

            telemetry.update();

            idle();
        }
    }
}
