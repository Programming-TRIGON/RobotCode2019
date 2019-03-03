package frc.robot.LiftCommands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;

/**
 * This command lets the operator control the Lift with a joystick incase the
 * sensors break
 */
public class SetLiftOverride extends Command {
  public SetLiftOverride() {
    requires(Robot.lift);
  }

  @Override
  protected void initialize() {
    Robot.lift.setSafeControl(false);
  }

  @Override
  protected void execute() {
    Robot.lift.setMotorSpeed(Robot.oi.operatorXbox.getY(Hand.kLeft));
  }

  @Override
  protected boolean isFinished() {
    return !RobotStates.isLiftOverride();
  }

  @Override
  protected void end() {
    Robot.lift.setMotorSpeed(0);
    Robot.lift.setSafeControl(true);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
