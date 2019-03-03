package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;

public class OneEightyToggleOverride extends Command {
  public OneEightyToggleOverride() {
  }

  @Override
  protected void initialize() {
    RobotStates.toggleOneEightyOverride();
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
