package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class teleoptest extends OpMode {
    DcMotor backL;
    DcMotor backR;
    DcMotor frontL;
    DcMotor frontR;

    DcMotor arm;


    @Override
    public void init(){
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");

        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        arm = hardwareMap.get(DcMotor.class, "Arm");
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //arm.setDirection(DcMotorSimple.Direction.REVERSE);



    }


    public void move(){
        double vertical;
        double horizontal;
        double pivot;
        vertical = -gamepad1.left_stick_y;
        pivot = gamepad1.left_stick_x;


        frontL.setPower(vertical - pivot);
        backL.setPower(vertical - pivot);
        frontR.setPower((-1 * vertical) + pivot);
        backR.setPower((-1 *vertical) + pivot);

    }

    public void arm(){
        if (gamepad1.dpad_up){
            arm.setTargetPosition(-1775);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_left){
            arm.setTargetPosition(-1000);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_down){
            arm.setTargetPosition(-20);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (gamepad1.dpad_right){
            arm.setTargetPosition(-420);
            arm.setPower(.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


    @Override
    public void init_loop(){


    }
    @Override
    public void loop(){
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        move();
        arm();

    }
}
