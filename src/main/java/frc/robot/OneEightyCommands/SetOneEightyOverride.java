package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotStates;

/**
 * This command lets the operator control the one eighty with a joystick incase
 * the sensors break
 */
public class SetOneEightyOverride extends Command {
  final double DEADBAND = 0.2, SENSITIVITY = 0.25;
  public SetOneEightyOverride() {
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
    Robot.oneEighty.setSafeControl(false);
  }

  @Override
  protected void execute() {
    double power = Robot.oi.operatorXbox.getY(Hand.kLeft);
    int sign = power >0 ? 1:-1;
    power = Math.abs(power) >= DEADBAND ? (power-(sign*DEADBAND)) : 0;
    Robot.oneEighty.setOneEighty(-SENSITIVITY*power);
  }

  @Override
  protected boolean isFinished() {
    return !RobotStates.isOneEightyOverride();
  }

  @Override
  protected void end() {
    Robot.oneEighty.moveOneEightyOverride(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}