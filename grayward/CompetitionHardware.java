package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class CompetitionHardware {
    // Put any global robot variables here
    boolean cameraStatus = false;

    // Put cool object declarations here
    VuforiaHardware vuHW;
    DriveTrainHw dtHW;
    EvanHw EvenHW;
    MarkerHardware MarkerHW;

    HardwareMap hwMap = null;

    public CompetitionHardware(boolean cameraStatus) {
        this.cameraStatus = cameraStatus;

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;


        // Put cool object instantiations here
        vuHW = new VuforiaHardware(this.cameraStatus);
        dtHW = new DriveTrainHw();
        EvenHW = new EvanHw();
        MarkerHW = new MarkerHardware();



        // Also, put cool object init function calls here
        vuHW.init(hwMap);
        dtHW.init(hwMap);
        EvenHW.init(hwMap);
        MarkerHW.init(hwMap);

    }
}
