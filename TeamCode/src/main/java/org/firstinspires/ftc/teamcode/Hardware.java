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
    private static DcMotor backL = null;
    private static DcMotor backR = null;
    private static DcMotor frontL = null;
    private static DcMotor frontR = null;
    private static DcMotor arm1 = null;
    private        DcMotor arm2 = null;

    //Create Servos
    private Servo claw = null;
    private Servo torqueR = null;
    private Servo torqueL = null;
    private CRServo speedR = null;
    private CRServo speedL = null;


    //Variables
    HardwareMap hardwareMap = null;
    static double drive;
    static double turn;
    double strafe;
    double speedr;
    double speedl;
    double torquel;
    double torquer;

    double BLpower;
    double BRpower;
    double FLpower;
    double FRpower;

    int arm_position;


    // Define a constructor that allows the OpMode to pass a reference to itself.
    public Hardware(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     * <p>
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */

    public void init() {

        //connect motors
        backL = hardwareMap.get(DcMotor.class, "backL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        arm1 = hardwareMap.get(DcMotor.class, "arm1");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");


        //connect servos
        torqueR = hardwareMap.get(Servo.class, "R torque");
        torqueL = hardwareMap.get(Servo.class, "L torque");
        speedR = hardwareMap.get(CRServo.class, "R speed");
        speedL = hardwareMap.get(CRServo.class, "L speed");

        //set directions
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        arm1.setDirection(DcMotorSimple.Direction.FORWARD);
        arm2.setDirection(DcMotorSimple.Direction.FORWARD);

        //set Motor Modes


        backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set ZERO POWER BEHAVIOR
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        torqueR.scaleRange(0.2, 0.8);
        torqueL.scaleRange(0.2, 0.8);

        //set Motors to 0 power
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        arm1.setPower(0);
    }

    public static void movement() {
        backL.setPower((drive - turn) / 2);
        backR.setPower(((-1 * drive) - turn) / 2);
        frontL.setPower((drive - turn) / 2);
        frontR.setPower(((-1 * drive) - turn) / 2);
    }

    public void arm_position() {
        arm1.setTargetPosition(arm_position);
        arm1.setPower(.5);
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setTargetPosition(arm_position);
        arm2.setPower(.5);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void claw_pos() {
        speedL.setPower(speedl);
        speedR.setPower(speedr);
        torqueL.setPosition(torquel);
        torqueR.setPosition(torquer);
    }
    public void autonmovement(){
        backL.setPower(BLpower);
        backR.setPower(BRpower);
        frontL.setPower(FLpower);
        frontR.setPower(FRpower);
    }
    public void stop(){
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
    }
    public void resetencoders(){
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}