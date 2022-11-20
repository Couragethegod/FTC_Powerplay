package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    private LinearOpMode myOpMode = null;   // gain access to methods in the calling OpMode.

    //Create Motors
    private DcMotor backL = null;
    private DcMotor backR = null;
    private DcMotor frontL = null;
    private DcMotor frontR = null;
    private DcMotor arm = null;

    //Create Servos
    private Servo claw = null;
    private CRServo cr = null;

    //Variables
    HardwareMap hardwareMap = null;
    double drive;
    double turn;
    double strafe;
    int arm_position;
    int claw_pos;

    // Define a constructor that allows the OpMode to pass a reference to itself.
    public Hardware(LinearOpMode opmode) {myOpMode = opmode;}

    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     *
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */

    public void init() {

        //connect motors
        backL = hardwareMap.get(DcMotor.class, "backL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        arm = hardwareMap.get(DcMotor.class, "arm");

        //connect servos
        claw = hardwareMap.get(Servo.class, "claw");
        cr = hardwareMap.get(CRServo.class, "crservo");

        //set directions
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);

        //set Motor Modes
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set ZERO POWER BEHAVIOR
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set Motors to 0 power
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        arm.setPower(0);
    }
    public void movement(){
        backL.setPower(drive + strafe + turn);
        backR.setPower(drive + strafe - turn);
        frontL.setPower(drive - strafe - turn);
        frontR.setPower(drive - strafe + turn);
    }
    public void arm_position(){
        arm.setTargetPosition(arm_position);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void claw_pos(){
        claw.setPosition(claw_pos);
    }
}