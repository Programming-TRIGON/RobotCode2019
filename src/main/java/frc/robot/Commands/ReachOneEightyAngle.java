/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReachOneEightyAngle extends Command {
  private double angle;
  public ReachOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle=angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (this.angle>Robot.oneEighty.getAngle())
      Robot.oneEighty.setOneEighty(0.2);
    else
      Robot.oneEighty.setOneEighty(-0.2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentAngle=Robot.oneEighty.getAngle();
    return (currentAngle > angle-3) && (currentAngle < angle+3);
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
    Robot.oneEighty.setOneEighty(0);
  }
}
