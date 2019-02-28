package frc.robot.Commands;

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
  }

  @Override
  protected void execute() {
    Robot.lift.setSafeControl(true);
    Robot.lift.setMotorSpeed(Robot.oi.operatorXbox.getY(Hand.kRight));
  }

  @Override
  protected boolean isFinished() {
    return !RobotStates.isLiftOverride();
  }

  @Override
  protected void end() {
    Robot.lift.setMotorSpeed(0);
    Robot.lift.setSafeControl(false);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
