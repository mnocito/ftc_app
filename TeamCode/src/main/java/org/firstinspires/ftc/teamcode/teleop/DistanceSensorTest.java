package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.chassis.Robot;
import org.firstinspires.ftc.teamcode.misc.FtcUtils;
import org.firstinspires.ftc.teamcode.misc.RobotConstants;
//@Disabled
@TeleOp(name = "Distance Sensor Test")
public class DistanceSensorTest extends LinearOpMode {
    private double front = 0;
    private double back = 0;
    private Robot robot = new Robot();
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this, false);
        telemetry.addData("Status", "Initialization bas been completed");
        telemetry.update();
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("front", robot.frontDist());
            telemetry.addData("back", robot.backDist());
            telemetry.addData("diff", robot.distDifference());
            telemetry.update();
        }
        robot.stop();
    }
}