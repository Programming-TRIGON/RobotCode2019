package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class PathFinder extends Command {
  // TODO: make real values
  private double desired_heading, heading, heading_difference, turn, right_speed, left_speed;
  private double kP, kI, kD, kA, turnSize, maxVelocity;
  private EncoderFollower encoderFollowerLeft, encoderFollowerRight;
  private Trajectory leftTrajectory, rightTrajectory;
  private String pathName;

  public PathFinder(String pathName) {
    requires(Robot.driveTrain);
    this.pathName = pathName;
    this.kP = 0.2;
    this.kI = 0;
    this.kD = 0;
    this.kA = 0;
    this.turnSize = -0.01;
    this.maxVelocity = 0.4;
  }

  @Override
  protected void initialize() {
    /** reseting sensors */
    RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.reset();
    RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.reset();
    RobotComponents.DriveTrain.DRIVETRAIN_GYRO.reset();
    /**
     * The tarjectory is the path(each side of the drivetrain has one) this is like
     * the "error" in PID
     */
    this.rightTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".right");
    this.leftTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".left");
    /**
     * The encoder follower does PID using encoder input to find the motor power
     */
    this.encoderFollowerLeft = new EncoderFollower(this.leftTrajectory);
    this.encoderFollowerRight = new EncoderFollower(this.rightTrajectory);
    /**
     * giving the encoderFollowers the current ticks and the CPR and wheelDiamter as
     * we don't want it sto bbe in ticks we want a usable measurment
     */
    encoderFollowerLeft.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get(),
        RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
    encoderFollowerRight.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get(),
        RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
    /** setting the PID values: kp, ki,kd, kv, ka */
    encoderFollowerLeft.configurePIDVA(this.kP, this.kI, this.kD, 1 / this.maxVelocity, this.kA);
  }

  @Override
  protected void execute() {
    /** PID on encoders */
    this.left_speed = encoderFollowerLeft.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get());
    this.right_speed = encoderFollowerRight.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get());
    /** getting the current angle */
    this.heading = RobotComponents.DriveTrain.DRIVETRAIN_GYRO.getAngle();
    /** getting our desired angle */
    this.desired_heading = Pathfinder.r2d(encoderFollowerLeft.getHeading());
    /**
     * finding our desired speed. boundHalf: converts values to a number between 180
     * - -180 so it doesn't end up turning over 180 degrees
     */
    this.heading_difference = Pathfinder.boundHalfDegrees(this.desired_heading - this.heading);
    /**
     * making the value smaller so it doesn't have as much of a affect as the
     * encoders
     */
    // TODO: FIX THIS
    this.turn = this.turnSize * this.heading_difference;
    /** setting the numbers given by the PID to the motors */
    Robot.driveTrain.tankDrive((this.left_speed + this.turn), (this.right_speed - this.turn));
  }

  @Override
  protected boolean isFinished() {
    /** checking if the path is finshed */
    return encoderFollowerLeft.isFinished() && encoderFollowerRight.isFinished();
  }

  @Override
  protected void end() {
    Robot.driveTrain.tankDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
