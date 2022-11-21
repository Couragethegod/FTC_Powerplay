package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware;

@Autonomous(name="Concept: Hardware Class", group="Robot")
public class Auton extends LinearOpMode {
    // Create a RobotHardware object to be used to access robot hardware.
    // Prefix any hardware functions with "robot." to access this class.
    org.firstinspires.ftc.teamcode.Hardware hardware = new Hardware(this);
    org.firstinspires.ftc.teamcode.AprilTag apriltag = new AprilTag(this);
    org.firstinspires.ftc.teamcode.FieldNav fieldnav = new FieldNav(this);
    @Override
    public void runOpMode() {
        double turn = 0;
        double arm = 0;
        double handOffset = 0;
        // initialize all the hardware, using the hardware class. See how clean and simple this is?
        hardware.init();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        /* April Tag and Vuforia
        if (fieldnav.image == 0 || apriltag.tagOfInterest.id == apriltag.LEFT){
        hardware.arm_position = 800;
        } else if (fieldnav.image == 0 || apriltag.tagOfInterest.id == apriltag.MIDDLE){
            hardware.arm_position = 600;
        } else if (fieldnav.image == 0 || apriltag.tagOfInterest.id == apriltag.RIGHT){
            hardware.arm_position = 400;
        }
        if (fieldnav.image == 1 || apriltag.tagOfInterest.id == apriltag.LEFT){
            hardware.arm_position = 1000;
        } else if (fieldnav.image == 1 || apriltag.tagOfInterest.id == apriltag.MIDDLE){
            hardware.arm_position = 1200;
        } else if (fieldnav.image == 1 || apriltag.tagOfInterest.id == apriltag.RIGHT){
            hardware.arm_position = 1400;
        }
        if (fieldnav.image == 2 || apriltag.tagOfInterest.id == apriltag.LEFT){
            hardware.arm_position = 200;
        } else if (fieldnav.image == 2 || apriltag.tagOfInterest.id == apriltag.MIDDLE){
            hardware.arm_position = 100;
        } else if (fieldnav.image == 2 || apriltag.tagOfInterest.id == apriltag.RIGHT){
            hardware.arm_position = 50;
        }
        if (fieldnav.image == 3 || apriltag.tagOfInterest.id == apriltag.LEFT){
            hardware.arm_position = 0;
        } else if (fieldnav.image == 3 || apriltag.tagOfInterest.id == apriltag.MIDDLE){

        } else if (fieldnav.image == 3 || apriltag.tagOfInterest.id == apriltag.RIGHT){

        } */
        // April Tag
        if (apriltag.tagOfInterest.id == apriltag.LEFT) {

        } else if (apriltag.tagOfInterest.id == apriltag.MIDDLE) {

        } else if (apriltag.tagOfInterest.id == apriltag.RIGHT) {

        }
    }
}