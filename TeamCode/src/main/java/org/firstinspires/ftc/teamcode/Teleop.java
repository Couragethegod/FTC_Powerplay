package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="Concept: Teleop Class", group="hardware")
public class Teleop extends LinearOpMode {
    Hardware hardware;
    // Create a hardwareHardware object to be used to access hardware hardware.
    // Prefix any hardware functions with "hardware." to access this class.
    //org.firstinspires.ftc.teamcode.Hardware hardware = new org.firstinspires.ftc.teamcode.Hardware(this);

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
            claw();
        }
    }
    public void move_arm(){
        if (gamepad1.dpad_up) {
            hardware.arm_position = -1075;
        }
        else if (gamepad1.dpad_left) {
            hardware.arm_position = -700;

        }
        else if (gamepad1.dpad_down) {
            hardware.arm_position = -420;
        }
        else if (gamepad1.dpad_right) {
            hardware.arm_position = -40;
        }
    }


    public void move(){
        Hardware.drive = gamepad1.left_stick_y;
        Hardware.turn = gamepad1.left_stick_x;
    }
    public void claw(){
        if (gamepad1.right_bumper){
            hardware.torquel = .05;
            hardware.torquer = 0;
        } else if (gamepad1.left_bumper){
            hardware.torquel = 0;
            hardware.torquer = .05;
        }
        if (gamepad1.a){
            hardware.speedl = 0;
            hardware.speedr = 0;
        } else if (gamepad1.b){
            hardware.speedl = 1;
            hardware.speedr = -1;
        } else if (gamepad1.x){
            hardware.speedl = -1;
            hardware.speedr = 1;
        }
    }
}