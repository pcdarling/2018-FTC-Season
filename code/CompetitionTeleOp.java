package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Manual Mode", group="TeleOp")
public class CompetitionTeleOp extends OpMode{
    CompetitionHardware robot = new CompetitionHardware(false);

    double rtTresh = 0.03;
    boolean tankControls = true ;

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){
        checkDriverControls();
        checkOperatorControls();
        checkVuforiaControls();
        /*if (!robot.targetSeen && !robot.lt.isAlive()) {
            robot.lt.start();
        }
        telemetry.addData("Target Status: ", robot.targetSeen);
        if (robot.targetSeen){
            telemetry.addData("Which Target? ", robot.location);
        }*/
    }

    @Override
    public void stop(){
        robot.cam.resetLoc();
    }

    public void checkDriverControls(){
        double Rx = gamepad1.right_stick_x;
        double Ry = gamepad1.right_stick_y;
        double Ly = gamepad1.left_stick_y;
        boolean rb = gamepad1.right_bumper;
        boolean buttonB = gamepad1.b;
        boolean buttonX = gamepad1.x;

        // Drivetrain Controls
        if (gamepad1.dpad_left){
            if (!tankControls) {
                tankControls = true;
            }
            else{
                tankControls = false;
            }
        }
        if (tankControls) {
            robot.drivetrain.tankcontrolsMovent(Ry, Ly, rb);

        } else {
            robot.drivetrain.FPSmovementByControl(Rx, Ly, rb);
        }


        // Evan Controls
        double evanPower;
        if (rb) { // Turbo
            evanPower = 1;
        } else { // Precise
            evanPower = 0.5;
        }

        if (gamepad1.left_trigger > rtTresh) {
            robot.evan.bicep.setPower(evanPower);
        } else if (gamepad1.right_trigger > rtTresh) {
            robot.evan.bicep.setPower(-evanPower);
        } else {
            robot.evan.bicep.setPower(0);
        }

        if (buttonB) {
            robot.evan.latch(false);
        }
        if  (buttonX) {
            robot.evan.latch(false);
        }

    }

    public void checkOperatorControls(){
        // gamepad2.x, gamepad2.y, gamepad2.b, and gamepad2.a are being used for larry deplore and the servo on the intake system
        double thresh = 0.06;
        double ly = gamepad2.left_stick_y;
        double ry = gamepad2.right_stick_y;
        boolean leftBumper = gamepad2.left_bumper;
        boolean rBumper = gamepad2.right_bumper;
        boolean xButton = gamepad2.x;
        boolean yButton = gamepad2.y;
        boolean bButton = gamepad2.b;
        boolean aButton = gamepad2.a;



        // Intake controls
        robot.intake.intakeSuccc(1, rBumper,leftBumper); // Dat succ
        if(Math.abs(ly) > thresh) { // Strong enough
            if (ly > 0) { // down
                robot.intake.intakeArmRaiseLower(Math.abs(ly*0.25), false, true);
            } else { // up
                robot.intake.intakeArmRaiseLower(Math.abs(ly*0.8),true,false);
            }
        } else { // Not strong enough
            robot.intake.intakeArmRaiseLower(0,false,false);
        }
        if (gamepad2.dpad_up){
            robot.intake.intakeArmRaiseLower(0.15, true, false); // max power going up
        }
        if(Math.abs(ry) > thresh) { // Strong enough
            if (ry > 0) { // up
                robot.intake.moveIntake(Math.abs(ry),true,false);
            } else { // down
                robot.intake.moveIntake(Math.abs(ry),false,true);
            }
        } else { // Not strong enough
            robot.intake.moveIntake(0,false,false);
        }
        //servo on the intake
        if(xButton){
            robot.intake.ITServo.setPosition(robot.intake.right);
        }
        if(bButton){
            robot.intake.ITServo.setPosition(robot.intake.left);
        }
        if(yButton && bButton){
            robot.intake.ITServo.setPosition(robot.intake.halfwayOpenBox);
        }
        if(aButton && xButton){
            robot.intake.ITServo.setPosition(robot.intake.openBox);
        }
        //servo to deploy larry
        /*
        if (gamepad2.y){
            robot.misc.markerMover.setPosition(robot.misc.storePos);
        }
        if (gamepad2.a){
            robot.misc.markerMover.setPosition(robot.misc.ejectPos);
        }*/
        /*if (gamepad2.dpad_down && !robot.mt.isAlive()){
            robot.createMarkerThread();
            robot.mt.start();
        }*/
    }

    public void checkVuforiaControls(){
        if (gamepad1.a){
            robot.cam.resetLoc();
        }
    }
}