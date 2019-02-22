/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;

public class ReachOneEightyAngle extends Command {
  private double angle;

  public ReachOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle = angle;
  }

  public ReachOneEightyAngle(OneEightyAngle angle) {
    requires(Robot.oneEighty);
    this.angle = angle.key;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (this.angle > Robot.oneEighty.getAngle())
      Robot.oneEighty.setOneEighty(0.5);
    else
      Robot.oneEighty.setOneEighty(-0.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentAngle = Robot.oneEighty.getAngle();
    return ((currentAngle > angle - 1.5) && (currentAngle < angle + 1.5)) || RobotStates.isOneEightyOverride();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.oneEighty.setOneEighty(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
