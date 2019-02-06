package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class VuforiaHardware {

    //software variables
    VuforiaLocalizer vuforia;
    public VuforiaTrackables targetsRoverRuckus;
    public VuforiaTrackable blueRover;
    public VuforiaTrackable redFootprint;
    public VuforiaTrackable frontCraters;
    public VuforiaTrackable backSpace;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();

    //physical objects to detect
    public static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    public static final String LABEL_GOLD_MINERAL = "Gold Mineral"; // these were private
   // public static final String LABEL_SILVER_MINERAL = "Silver Mineral"; took this out to prevent phone from checking silver mineral

    //position variables
    public int leftCount = 0;
    public int rightCount = 0;
    public int centerCount = 0;
    public boolean isLeft = false;
    public boolean isRight = false;
    public boolean isCenter = false;
    public double goldPos = -1;
    public double goldWidth = 0;
    public double goldTop = 0;
    public double imageHeight = 0;
    public int counter = 0;

    public TFObjectDetector tfod;
    //\\
    HardwareMap hwmap = null;

    public VuforiaHardware(){}//constructor

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;
        int cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AYOeonr/////AAABmSirnJnvQUnelw5JOBSA3YZQx9wGb22naoaPA/2nWtFxpJiRrDY3NzvoKMTH6zRjy4eYcbHgabgNIwD7OaOLfQM5ZlLV5rmsHwdUkUN1aC8m2nNPlESStk9Ud1pvewjIfQCx1uBqAnRrBQmGFvxnHa6LNbS+eGIVt2/dmTuwUK+WZ5Yn4e0BDO5YlcOiiGEujAmqO+3O1p8a1YM+QHA/Bk7sCnM1hx8pYDT7Qp93jemP3plVOEC3hsEki1xMMBOpp6yip/XR4zX8nFRAT0sZqI7/s50EcuUcXEbPy1Fdv6r0gJZXzsmYm8qA2SLKpinCAd5EvKs6qlaiEFfuFgBplAGW7f6Yg5C1mdjOImQxJhxC";//license key here
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
        redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
        frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
        backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");
        allTrackables.addAll(targetsRoverRuckus);

        int tfodMonitorViewId = hwmap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwmap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL);
    }
    public void createSampleThread(){
        SampleThread st = new SampleThread();
    }

    public class SampleThread extends Thread{
        public void run(){
            findGoldPos();
        }
        public void findGoldPos(){
            if (tfod != null) {
                tfod.activate();
            }
            while (leftCount + rightCount + centerCount <= 10) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                    goldWidth = recognition.getRight() - recognition.getLeft();
                                    goldTop = recognition.getTop();
                                    imageHeight = recognition.getImageHeight();
                                    if (goldWidth < 250 && goldTop < imageHeight / 2.0) {
                                        goldPos = (int) ((double) recognition.getLeft() + (double) recognition.getRight()) / 2;
                                    }
                                    double goldAngle = (recognition.estimateAngleToObject(AngleUnit.RADIANS)) * 180 / Math.PI; // Brandon personally made this variable in case of some future reference
                                }
                                if (goldPos != -1) {
                                    if (goldPos < 500) {
                                        leftCount++;
                                    } else {
                                        centerCount++;
                                    }
                                } else {
                                    counter++;
                                    if (counter > 10) {
                                        rightCount++;
                                    }
                                }
                            }
                        }
                    }
                }
                if (leftCount > rightCount && leftCount > centerCount){ // left wins
                    isLeft = true;
                }else if (rightCount > leftCount && rightCount > centerCount){// right wins
                    isRight = true;
                }else{// center wins
                    isCenter = true;
                }
            }
        }
    }

    public void findGoldPos(){
        if (tfod != null) {
            tfod.activate();
        }
        while (leftCount + rightCount + centerCount <= 10) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                                goldWidth = recognition.getRight() - recognition.getLeft();
                                goldTop = recognition.getTop();
                                imageHeight = recognition.getImageHeight();
                                if (goldWidth < 250 && goldTop < imageHeight / 2.0) {
                                    goldPos = (int) ((double) recognition.getLeft() + (double) recognition.getRight()) / 2;
                                }
                                double goldAngle = (recognition.estimateAngleToObject(AngleUnit.RADIANS)) * 180 / Math.PI; // Brandon personally made this variable in case of some future reference
                            }
                            if (goldPos != -1) {
                                if (goldPos < 500) {
                                    leftCount++;
                                } else {
                                    centerCount++;
                                }
                            } else {
                                counter++;
                                if (counter > 10) {
                                    rightCount++;
                                }
                            }
                        }
                    }
                }
            }
            if (leftCount > rightCount && leftCount > centerCount){ // left wins
                isLeft = true;
            }else if (rightCount > leftCount && rightCount > centerCount){// right wins
                isRight = true;
            }else{// center wins
                isCenter = true;
            }
        }
    }
}
