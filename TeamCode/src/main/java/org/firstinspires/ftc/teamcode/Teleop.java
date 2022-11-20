package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware;

@TeleOp(name="Concept: Robot Hardware Class", group="Robot")
public class Teleop extends LinearOpMode {
    // Create a RobotHardware object to be used to access robot hardware.
    // Prefix any hardware functions with "robot." to access this class.
    org.firstinspires.ftc.teamcode.Hardware robot = new org.firstinspires.ftc.teamcode.Hardware(this);

    @Override
    public void runOpMode() {
        double drive        = 0;
        double turn         = 0;
        double arm          = 0;
        double handOffset   = 0;

        // initialize all the hardware, using the hardware class. See how clean and simple this is?
        robot.init();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
        }
    }
}