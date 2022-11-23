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
            hardware.resetencoders();
            Hardware.movement();
            hardware.arm_position();
            hardware.claw_pos();
            move();
            move_arm();
            //claw();
        }
    }
    public void move_arm(){
        if (gamepad1.dpad_up){
            hardware.arm_position = -170;
        } else if (gamepad1.dpad_left){
            hardware.arm_position = -1075;
        } else if (gamepad1.dpad_down){
            hardware.arm_position = -500;
        } else if (gamepad1.dpad_right){
            hardware.arm_position = -20;
        }
    }
    public void claw(){
        if (gamepad1.a){
            hardware.claw_pos = 0;
        } else if (gamepad1.b){
            hardware.claw_pos = 180;
        }
    }
    public void move(){
        hardware.drive = gamepad1.left_stick_y;
        hardware.turn = gamepad1.left_stick_x;
    }
}