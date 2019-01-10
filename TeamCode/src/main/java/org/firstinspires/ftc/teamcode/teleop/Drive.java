package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.chassis.Robot;
import org.firstinspires.ftc.teamcode.misc.FtcUtils;
import org.firstinspires.ftc.teamcode.misc.RobotConstants;

@TeleOp(name = "Drive")
public class Drive extends LinearOpMode {
    private double currentNomServoPos = 0;
    private ElapsedTime runtime = new ElapsedTime();
    private double rightx = 0;
    private double leftx = 0;
    private double lefty = 0;
    double FLPow = 0;
    double BLPow = 0;
    double FRPow = 0;
    double BRPow = 0;
    double[] pows = new double[4];
    double m = 0.0;
    private boolean pressed = false;
    private Robot robot = new Robot();
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this, false);
        //robot.markerServo(RobotConstants.MARKERSERVO_HOLD);
        telemetry.addData("Status", "Initialization bas been completed");
        telemetry.update();
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            //telemetry.addData("servo", robot.nomServoPos());
            telemetry.addData("extend", robot.getExtendTicks());
            telemetry.addData("lift", robot.getLiftTicks());
            lefty = FtcUtils.motorScale(-gamepad1.left_stick_y) * RobotConstants.sensitivity;
            rightx = FtcUtils.motorScale(gamepad1.right_stick_x) * RobotConstants.sensitivity;
            leftx = FtcUtils.motorScale(gamepad1.left_stick_x) * RobotConstants.sensitivity;
            //      Front Left = +Speed + Turn - Strafe      Front Right  = +Speed - Turn + Strafe
            //      Back Left  = +Speed + Turn + Strafe      Back Right  = +Speed - Turn - Strafe
            pows[0] = lefty + leftx + rightx;
            pows[1] = lefty - leftx + rightx;
            pows[2] = lefty - leftx - rightx;
            pows[3] = lefty + leftx - rightx;
            for (double v : pows)
                if (Math.abs(v) > m)
                    m = v;
            if (m != 0) { // if the max power isn't 0 (can't divide by 0)
                pows[0] /= Math.abs(m);
                pows[1] /= Math.abs(m);
                pows[2] /= Math.abs(m);
                pows[3] /= Math.abs(m);
            }
            robot.drive(FtcUtils.motorScale(pows[0]), FtcUtils.motorScale(pows[1]), FtcUtils.motorScale(pows[2]), FtcUtils.motorScale(pows[3]));
            /*if (FtcUtils.scale(gamepad2.right_trigger, 0, 1) > RobotConstants.threshold) {
                robot.nom(1);
            } else if (FtcUtils.scale(gamepad2.left_trigger, 0, 1) > RobotConstants.threshold) {
                robot.nom(-1);
            } else {
                robot.nom(0);
            }*/
            if (FtcUtils.abs(FtcUtils.motorScale(gamepad2.right_stick_y)) > RobotConstants.threshold && robot.canExtend()) {
                if (robot.canExtendUp()) {
                    robot.extend(-1);
                } else if (robot.canExtendDown()) {
                    robot.extend(1);
                }
            } else {
                robot.extend(0);
            }
            if (FtcUtils.abs(FtcUtils.motorScale(gamepad2.left_stick_y)) > RobotConstants.threshold && robot.canLift()) {
                robot.lift(FtcUtils.sign(gamepad2.left_stick_y));
                /*if (robot.canExtendUp()) {
                    robot.extend(-1);
                } else if (robot.canExtendDown()) {
                    robot.extend(1);
                }*/
            } else {
                robot.lift(0);
            }
            /*
            if (gamepad2.y) {
                robot.nomServo(RobotConstants.NOMSERVO_UP);
            }
            if (gamepad2.a) {
                robot.nomServo(RobotConstants.NOMSERVO_DOWN);
            }
            if (gamepad2.x) {
                robot.nomServo(RobotConstants.NOMSERVO_NEUTRAL);
            }
            if (gamepad2.right_bumper) {
                if (!pressed) {
                    pressed = true;
                    RobotConstants.MIN_EXTEND_TICKS -= 100;
                }
            } else {
                pressed = false;
            }
            if (gamepad2.dpad_up) {
                robot.hang(1);
            } else if (gamepad2.dpad_down) {
                robot.hang(-1);
            } else {
                robot.hang(0);
            }*/
            telemetry.update();
            idle();
        }
        robot.stop();
    }
}