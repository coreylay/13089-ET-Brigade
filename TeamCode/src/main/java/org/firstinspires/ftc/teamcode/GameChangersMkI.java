package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class GameChangersMkI extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    //Declare your parts here
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    //Everything here occurs when you hit the "init" button
    public void runOpMode(){
     FL = hardwareMap.get(DcMotor.class, "Motor Port 0");
     FL.setDirection(DcMotor.Direction.FORWARD);
     FR = hardwareMap.get(DcMotor.class, "Motor Port 1");
     FR.setDirection(DcMotorSimple.Direction.REVERSE);
     BL = hardwareMap.get(DcMotor.class, "Motor Port 2");
     BL.setDirection(DcMotorSimple.Direction.FORWARD);
     BR = hardwareMap.get(DcMotor.class, "Motor Port 3");
     BR.setDirection(DcMotorSimple.Direction.REVERSE);

     telemetry.addData("Status: ","Initialized");
     telemetry.update();

     //Everything after this point happens after you hit the start button
     waitForStart();
     runtime.reset();

        /*
         *The controls for this set up is RC car style, not Tank Drive
         *First set of commands is for driving the robot forwards and backwards
         *Second set of commands is for driving the robot sideways, or strafing
         *Third set of commands is for turning the robot left or right
         *The first set of operators is there to reverse the front to counter the direction the back is going. This motion makes it strafe
         * The second set of operators is there to ensure the left or right motors have the same values, and therefore makes turning possible
         */

        //Fully functional drive, nothing reversed!!!!
        //leftstick y: Forward Backwards  leftstick x: Turn Left Right  Rightstick x: Strafe Left Right
        FL.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x));
        FR.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x));
        BL.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
        BR.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x));

            /* Visual Representation of the directions the motors go, so I can rework the values for the equation above
            NOTE: For the left and right on the stick, left is a negative value, and right is a positive value
                  To strafe right, the right wheels must turn towards each other and vice versa
                      Front
            -     |------------|     -
             Wheel|            |Wheel
            +     |            |     +
                  |            |
            -     |            |     -
             Wheel|            |Wheel
            +     |------------|     +
                       Back
             */

        telemetry.addData("Front Right Power: ", FL.getPower());
        telemetry.addData("Front Right Power: ", FR.getPower());
        telemetry.addData("Front Right Power: ", BL.getPower());
        telemetry.addData("Front Right Power: ", BR.getPower());
        telemetry.update();
    }
}
