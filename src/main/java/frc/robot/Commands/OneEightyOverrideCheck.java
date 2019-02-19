package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.CommandGroups.SetOneEightyAngle;

/**
 * This command checks which command should run. it should run when the operator
 * overrides/ unoverrides the one eighty.
 */
public class OneEightyOverrideCheck extends Command {
  public OneEightyOverrideCheck() {
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
    if (RobotStates.isOneEightyOverride()) {
      new SetOneEightyOverride().start();
    }
    // TODO: change to driving change
    else {
      new SetOneEightyAngle(RobotStates.getOneEightySetpoint()).start();
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
