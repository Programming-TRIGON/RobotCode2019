/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems.JoystickOverridableSubsystem;

public class MoveSubsystemWithTimeout extends Command {

  double power;
  JoystickOverridableSubsystem subsystem;
  public MoveSubsystemWithTimeout(JoystickOverridableSubsystem subsystem, double power, double timeout) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setTimeout(timeout);
    requires(subsystem);
    this.subsystem = subsystem;
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    subsystem.move(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    subsystem.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
