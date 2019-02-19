package frc.robot.Commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotStates;

public class SetOneEightyOverride extends Command {
  public SetOneEightyOverride() {
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.oneEighty.moveOneEightyOverride(Robot.oi.operatorXbox.getX(Hand.kLeft));
  }

  @Override
  protected boolean isFinished() {
    return !RobotStates.isOneEightyOverride();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.oneEighty.moveOneEightyOverride(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}