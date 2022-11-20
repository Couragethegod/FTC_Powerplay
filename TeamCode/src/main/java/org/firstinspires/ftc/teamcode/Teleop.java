package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware;

@TeleOp(name="Concept: Hardware Class", group="Robot")
public class Teleop extends LinearOpMode {
    // Create a RobotHardware object to be used to access robot hardware.
    // Prefix any hardware functions with "robot." to access this class.
    org.firstinspires.ftc.teamcode.Hardware robot = new org.firstinspires.ftc.teamcode.Hardware(this);

    @Override
    public void runOpMode() {
        double turn         = 0;
        double arm          = 0;
        double handOffset   = 0;

        // initialize all the hardware, using the hardware class. See how clean and simple this is?
        robot.init();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.movement();
            robot.arm_position();
            robot.claw_pos();
            move();
            move_arm();
            move_claw();
        }
    }
    public void move_arm(){
        if (gamepad1.dpad_up){
            robot.arm_position = 800;
        } else if (gamepad1.dpad_left){
            robot.arm_position = 600;
        } else if (gamepad1.dpad_down){
            robot.arm_position = 400;
        } else if (gamepad1.dpad_right){
            robot.arm_position = 200;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_up){
            robot.arm_position = 100;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_left){
            robot.arm_position = 80;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_down){
            robot.arm_position = 60;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_right){
            robot.arm_position = 20;
        }
    }
    public void move_claw(){
        if (gamepad1.a){
            robot.claw_pos = 0;
        } else if (gamepad1.b){
            robot.claw_pos = 180;
        }
    }
    public void move(){
        robot.drive = gamepad1.left_stick_y;
        robot.strafe = gamepad1.left_stick_x;
        robot.turn = gamepad1.right_stick_x;
    }
}