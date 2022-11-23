package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleoptest extends OpMode {
    DcMotor backL;
    DcMotor backR;
    DcMotor frontL;
    DcMotor frontR;
    DcMotor arm;

    Servo torqueR;
    Servo torqueL;
    CRServo speedR;
    CRServo speedL;



    @Override
    public void init() {
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        arm = hardwareMap.get(DcMotor.class, "Arm");

        torqueR = hardwareMap.get(Servo.class, "R torque");
        torqueL = hardwareMap.get(Servo.class, "L torque");
        speedR = hardwareMap.get(CRServo.class, "R speed");
        speedL = hardwareMap.get(CRServo.class, "L speed");

        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        torqueR.scaleRange(0.2, 0.8);
        torqueL.scaleRange(0.2, 0.8);
    }

    @Override
    public void init_loop() {


    }

    @Override
    public void loop() {
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        move();
        arm();

    }


    public void move() {
        double drive;
        double turn;
        double strafe;
        drive = gamepad1.left_stick_y;
        turn = gamepad1.left_stick_x;


        backL.setPower((drive - turn) / 2);
        backR.setPower(((-1 * drive) - turn) / 2);
        frontL.setPower((drive - turn) / 2);
        frontR.setPower(((-1 * drive) - turn) / 2);

    }

    public void arm() {
        if (gamepad1.dpad_up) {
            arm.setTargetPosition(-1775);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_left) {
            arm.setTargetPosition(-1000);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_down) {
            arm.setTargetPosition(-500);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_right) {
            arm.setTargetPosition(-20);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
    public void claw(){
        if (gamepad1.right_bumper){
        speedL.setPower(.2);
        speedR.setPower(.2);
        }
        else if (gamepad1.left_bumper){
            speedL.setPower(.2);
            speedR.setPower(.2);
        }
        if (gamepad1.a){
            torqueL.setPosition(.2);
            torqueR.setPosition(.2);
        } else if (gamepad1.b){
            torqueL.setPosition(0);
            torqueR.setPosition(0);
        }
    }
}
