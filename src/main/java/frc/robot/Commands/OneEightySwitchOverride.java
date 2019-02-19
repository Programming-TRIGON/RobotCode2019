package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;

public class OneEightySwitchOverride extends Command {
  public OneEightySwitchOverride() {
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
    RobotStates.setoneEightyOverride();
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
