/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class test extends LinearOpMode {
    Servo claw;
    DcMotor backL;
    DcMotor backR;
    DcMotor frontL;
    DcMotor frontR;
    DcMotor arm;

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() {


        claw = hardwareMap.get(Servo.class, "Claw");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        arm = hardwareMap.get(DcMotor.class, "Arm");
        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        //arm.setTargetPosition(60);
        //arm.setPower(.5);
        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //claw.setPosition(0);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {

            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if (tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if (tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */
        if (tagOfInterest == null || tagOfInterest.id == LEFT) {
            //trajectory
            telemetry.addLine("Left");
            telemetry.update();
            // left();

        } else if (tagOfInterest == null || tagOfInterest.id == MIDDLE) {
            //trajectory
            telemetry.addLine("MIDDLE");
            telemetry.update();
            //claw.setPosition(0);
            //  middle();
        } else if (tagOfInterest == null || tagOfInterest.id == RIGHT) {
            //trajectory
            // claw.setPosition(180);
            // right();
        }

        test();
        //claw.setPosition(0);
        arm.setTargetPosition(80);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //backL.setPower(-.5);
        //frontL.setPower(-.5);
        //sleep(200);
        //arm.setTargetPosition(200);
        //arm.setPower(.5);
        //  arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        //while (opModeIsActive()) {sleep(20);}
    }

    void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

    public void left() {

        backL.setPower(.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(.5);
        sleep(1000);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(100);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(500);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(100);
        arm.setTargetPosition(500);
        arm.setPower(1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        claw.setPosition(180);

    }

    public void right() {
        backL.setPower(-.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(-.5);
        sleep(1800);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(10);
        backL.setPower(1);
        backR.setPower(1);
        frontL.setPower(1);
        frontR.setPower(1);
        sleep(600);
    }

    public void middle() {
        backL.setPower(-.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(-.5);
        sleep(1800);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(10);
        backL.setPower(1);
        backR.setPower(1);
        frontL.setPower(1);
        frontR.setPower(1);
        sleep(600);
        backL.setPower(.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(.5);
        sleep(1800);

    }
    public void test (){
        // backR.setPower(-.5);
        // backL.setPower(.5);
        // frontR.setPower(.5);
        // frontL.setPower(-.5);
        // sleep(1000);
        //backL.setPower(0);
        // backR.setPower(0);
        // frontL.setPower(0);
        //frontR.setPower(0);
        //sleep(10);
        claw.setPosition(0);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(1000);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(200);
        backL.setPower(-.5);
        backR.setPower(0);
        frontL.setPower(-.5);
        frontR.setPower(0);
        sleep(625);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(200);
        arm.setTargetPosition(800);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(175);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(300);
        claw.setPosition(180);
        sleep(1000);
        backL.setPower(-.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(-.5);
        sleep(100);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(200);
        arm.setTargetPosition(230);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        backL.setPower(.5);
        backR.setPower(0);
        frontL.setPower(.5);
        frontR.setPower(0);
        sleep(550);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(940);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(300);
        backL.setPower(0);
        backR.setPower(-.6);
        frontL.setPower(0);
        frontR.setPower(-.6);
        sleep(1100);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(920);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(300);
        claw.setPosition(0);
        sleep(500);
        arm.setTargetPosition(1200);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        backL.setPower(-.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(-.5);
        sleep(800);
        backL.setPower(0);
        backR.setPower(-.5);
        frontL.setPower(0);
        frontR.setPower(-.5);
        sleep(1020);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(400);
        arm.setTargetPosition(0);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(1000);
        claw.setPosition(180);
        sleep(200);
        arm.setTargetPosition(1200);
        arm.setPower(.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(500);
        backL.setPower(-0.5);
        backR.setPower(-0.6);
        frontL.setPower(-0.5);
        frontR.setPower(-0.5);
        sleep(1020);
        backL.setPower(0);
        backR.setPower(0);
        frontL.setPower(0);
        frontR.setPower(0);
        sleep(400);
        backL.setPower(.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(.5);
        sleep(200);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        sleep(800);
        backL.setPower(.5);
        backR.setPower(-.5);
        frontL.setPower(-.5);
        frontR.setPower(.5);
        sleep(800);
    }

    public void encoders(){
        backL.setTargetPosition(24440);
        backR.setTargetPosition(24440);
        frontL.setTargetPosition(24440);
        frontR.setTargetPosition(24440);
        backL.setPower(.5);
        backR.setPower(.5);
        frontL.setPower(.5);
        frontR.setPower(.5);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}