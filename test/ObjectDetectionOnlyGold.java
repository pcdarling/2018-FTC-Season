package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@TeleOp(name = "sampling only gold", group = "testing")
public class ObjectDetectionOnlyGold extends LinearOpMode {

    public VuforiaHardware cam = new VuforiaHardware();
    double goldConfidence = 0;


    public void runOpMode() {
        cam.init(hardwareMap);
        sleep(50);
        waitForStart();
        while (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (cam.tfod != null) {
                cam.tfod.activate();
            }
            while (cam.leftCount + cam.rightCount + cam.centerCount <= 10) {
                if (cam.tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = cam.tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() > 0) {
                            telemetry.addData("Status: ", "I have entered this loop");
                            int goldMineralX = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(cam.LABEL_GOLD_MINERAL)) {
                                    telemetry.addLine("Gold: "+  "I have found gold");
                                    goldConfidence = recognition.getConfidence();
                                    goldMineralX = (int) recognition.getLeft();
                                    telemetry.addData("imageWidth", recognition.getImageWidth());
                                    telemetry.addData("Top", recognition.getTop());
                                    telemetry.addData("Left", recognition.getLeft());
                                    telemetry.addData("Right", recognition.getRight());
                                    cam.goldWidth = recognition.getRight() - recognition.getLeft();
                                    cam.goldTop = recognition.getTop();
                                    cam.imageHeight = recognition.getImageHeight();
                                    if (cam.goldWidth < 250 && cam.goldTop < cam.imageHeight / 2.0) {
                                        telemetry.addLine("Positioning: "+ "I have estimated postioning of gold");
                                        cam.goldPos = (int) ((double) recognition.getLeft() + (double) recognition.getRight()) / 2;
                                    }else{
                                        cam.goldPos = (int) ((double) recognition.getLeft() + (double) recognition.getRight()) / 2;
                                    }
                                    telemetry.addData("GoldX", cam.goldPos);
                                    telemetry.addData("GoldTop", cam.goldTop);
                                    telemetry.addData("Angle", (recognition.estimateAngleToObject(AngleUnit.RADIANS)) * 180 / Math.PI);
                                }
                                if (cam.goldPos != -1) {
                                    if (cam.goldPos < 500) {
                                        telemetry.addData("location","looks like left");
                                        telemetry.addData("GoldX", goldMineralX);
                                        cam.leftCount++;
                                    } else {
                                        telemetry.addData("location"," looks like center");
                                        telemetry.addData("GoldX", goldMineralX);
                                        cam.centerCount++;
                                    }
                                } else {
                                    telemetry.addData("location","looks like right");
                                    telemetry.addData("GoldX", goldMineralX);
                                    cam.rightCount++;
                                    if (cam.counter > 10) {
                                        cam.counter++;
                                    }else{
                                        telemetry.addLine("counter: " +" i have finished counting");
                                    }
                                }
                            }
                        }
                    }
                }
                if (cam.leftCount > cam.rightCount && cam.leftCount > cam.centerCount) {
                    telemetry.addData("Gold Mineral Position: ", "Left");
                    cam.isLeft = true;
                } else if (cam.rightCount > cam.leftCount && cam.rightCount > cam.centerCount) {
                    telemetry.addData("Gold Mineral Postion: ", "Right");
                    cam.isRight = true;
                } else {
                    telemetry.addData("Gold Mineral Postion: ", "Center");
                    cam.isCenter = true;
                }
                telemetry.addData("Status: ", "I have reached the end of this loop");
                telemetry.addData("Gold Confidence", goldConfidence);
                telemetry.addData("Width", cam.goldWidth);
                telemetry.addData("image height", cam.imageHeight);
                telemetry.addData("GoldX", cam.goldPos);
                telemetry.addData("LeftCount", cam.leftCount);
                telemetry.addData("CenterCount", cam.centerCount);
                telemetry.addData("RightCount", cam.rightCount);
                telemetry.update();
                telemetry.update();
            }
        }
    }
}
