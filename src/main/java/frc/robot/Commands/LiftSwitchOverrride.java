package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;

public class LiftSwitchOverrride extends Command {
  public LiftSwitchOverrride() {
  }

  @Override
  protected void initialize() {
    RobotStates.toggleLiftOverride();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
