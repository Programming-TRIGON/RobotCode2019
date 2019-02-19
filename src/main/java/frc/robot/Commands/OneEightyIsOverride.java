package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.CommandGroups.SetOneEightyAngle;

public class OneEightyIsOverride extends Command {
  public OneEightyIsOverride() {
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
    if (RobotStates.isOneEightyOverride()) {
      new SetOneEightyOverride().start();
    }
    // TODO: change to driving change
    else {
      new SetOneEightyAngle(RobotStates.getOneEightySetpoint());
    }
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
