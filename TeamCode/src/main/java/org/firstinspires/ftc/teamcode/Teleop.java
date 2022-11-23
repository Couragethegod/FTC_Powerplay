package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Concept: Teleop Class", group="hardware")
public class Teleop extends LinearOpMode {
    // Create a hardwareHardware object to be used to access hardware hardware.
    // Prefix any hardware functions with "hardware." to access this class.
    org.firstinspires.ftc.teamcode.Hardware hardware = new org.firstinspires.ftc.teamcode.Hardware(this);

    @Override
    public void runOpMode() throws InterruptedException{
        // initialize all the hardware, using the hardware class. See how clean and simple this is?
        hardware.init();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            hardware.movement();
            hardware.arm_position();
            hardware.claw_pos();
            move();
            move_arm();
            move_claw();
        }
    }
    public void move_arm(){
        if (gamepad1.dpad_up){
            hardware.arm_position = 800;
        } else if (gamepad1.dpad_left){
            hardware.arm_position = 600;
        } else if (gamepad1.dpad_down){
            hardware.arm_position = 400;
        } else if (gamepad1.dpad_right){
            hardware.arm_position = 200;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_up){
            hardware.arm_position = 100;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_left){
            hardware.arm_position = 80;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_down){
            hardware.arm_position = 60;
        } else if ((gamepad1.left_bumper) && gamepad1.dpad_right){
            hardware.arm_position = 20;
        }
    }
    public void move_claw(){
        if (gamepad1.a){
            hardware.claw_pos = 0;
        } else if (gamepad1.b){
            hardware.claw_pos = 180;
        }
    }
    public void move(){
        hardware.drive = gamepad1.left_stick_y;
        hardware.strafe = gamepad1.left_stick_x;
        hardware.turn = gamepad1.right_stick_x;
    }
}