package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;

public class OneEightySwitchOverride extends Command {
  public OneEightySwitchOverride() {
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
