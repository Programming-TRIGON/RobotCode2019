package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;

/**
 * This command checks which command should run. it should run when the operator
 * overrides/ unoverrides the Lift.
 */
public class LiftOverrideCheck extends Command {
  public LiftOverrideCheck() {
    requires(Robot.lift);
  }

  @Override
  protected void initialize() {
    if (RobotStates.isLiftOverride()) {
      new SetLiftOverride().start();
    }
    // TODO: change to driving change
    else {
      new SetLiftHeight(RobotStates.getLiftSetpoint()).start();
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
