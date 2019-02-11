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
  private double desiredAngle, angle, angleDifference, turn, rightSpeed, leftSpeed;
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
    // TODO: check when this bug is fixed (wpilibs side)
    this.leftTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".right");
    this.rightTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".left");
    /**
     * The encoder follower does PID using encoder input to find the motor power
     */
    this.encoderFollowerLeft = new EncoderFollower(this.leftTrajectory);
    this.encoderFollowerRight = new EncoderFollower(this.rightTrajectory);
    /**
     * giving the encoderFollowers the current ticks (starting point) and the CPR
     * and wheelDiamter as we don't want it to be in ticks we want a usable
     * measurment
     */
    encoderFollowerLeft.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get(),
        RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
    encoderFollowerRight.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get(),
        RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
    /** setting the PID values: kp, ki, kd, kv, ka */
    encoderFollowerLeft.configurePIDVA(this.kP, this.kI, this.kD, 1 / this.maxVelocity, this.kA);
    encoderFollowerRight.configurePIDVA(this.kP, this.kI, this.kD, 1 / this.maxVelocity, this.kA);
  }

  @Override
  protected void execute() {
    /** PID on encoders */
    this.leftSpeed = encoderFollowerLeft.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get());
    this.rightSpeed = encoderFollowerRight.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get());
    /** getting the current angle */
    this.angle = RobotComponents.DriveTrain.DRIVETRAIN_GYRO.getAngle();
    /**
     * getting our desired angle. we use only the left path because both sides of
     * the robot are parallel
     */
    this.desiredAngle = Pathfinder.r2d(encoderFollowerLeft.getHeading());
    /**
     * getting the anle the robot needs to move. boundHalf: converts values to a
     * number between 180 - -180 so it doesn't end up turning over 180 degrees
     */
    this.angleDifference = Pathfinder.boundHalfDegrees(this.desiredAngle - this.angle);
    /**
     * the output is returned in 180 - -180 degress we want it as a speed to insert
     * into the motors
     */
    // TODO: Set real value
    this.turn = this.turnSize * this.angleDifference;
    /** setting the numbers given by the PID to the motors */
    Robot.driveTrain.tankDrive((this.leftSpeed + this.turn), (this.rightSpeed - this.turn));
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
