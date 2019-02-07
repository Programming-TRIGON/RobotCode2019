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
  private EncoderFollower encoderFollowerLeft, encoderFollowerRight;
  private Trajectory leftTrajectory, rightTrajectory;
  private String pathName;

  public PathFinder(String pathName) {
    requires(Robot.driveTrain);
    this.pathName = pathName;
  }

  @Override
  protected void initialize() {

    this.rightTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".right");
    this.leftTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".left");

    this.encoderFollowerLeft = new EncoderFollower(this.leftTrajectory);
    this.encoderFollowerRight = new EncoderFollower(this.rightTrajectory);
    encoderFollowerLeft.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get(),
        RobotConstants.PathFinder.TicksPerRevolution, RobotConstants.PathFinder.WheelDiameter);
    encoderFollowerRight.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get(),
        RobotConstants.PathFinder.TicksPerRevolution, RobotConstants.PathFinder.WheelDiameter);
    encoderFollowerLeft.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotConstants.PathFinder.MaxVelocity, 0);
    RobotComponents.DriveTrain.DRIVETRAIN_GYRO.reset();
  }

  @Override
  protected void execute() {

    this.left_speed = encoderFollowerLeft.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get());
    this.right_speed = encoderFollowerRight.calculate(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get());
    this.heading = RobotComponents.DriveTrain.DRIVETRAIN_GYRO.getAngle();
    this.desired_heading = Pathfinder.r2d(encoderFollowerLeft.getHeading());
    this.heading_difference = Pathfinder.boundHalfDegrees(this.desired_heading - this.heading);
    this.turn = 0.8 * (-1.0 / 80.0) * this.heading_difference;
    Robot.driveTrain.tankDrive((this.left_speed + this.turn), (this.right_speed - this.turn));
  }

  @Override
  protected boolean isFinished() {
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
