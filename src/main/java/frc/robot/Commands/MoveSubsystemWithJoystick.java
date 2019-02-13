package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems.JoystickOverridableSubsystem;

public class MoveSubsystemWithJoystick extends Command {
  JoystickOverridableSubsystem subsystem;
  GenericHID joystick;

  public MoveSubsystemWithJoystick(JoystickOverridableSubsystem subsystem, GenericHID joystick) {
    requires(subsystem);
    this.subsystem = subsystem;
    this.joystick = joystick;
  }

  public MoveSubsystemWithJoystick(JoystickOverridableSubsystem subsystem, GenericHID joystick, String name) {
    requires(subsystem);
    this.subsystem = subsystem;
    this.joystick = joystick;
    this.setName(name);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.subsystem.move(this.joystick.getY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.subsystem.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
