package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants.PushCargoPower;

/** puts the cargo in the rocket */

public class PushCargo extends Command {
  double power;
  final double TIMEOUT = 2;

  /** accepts the power from an Enum */
  public PushCargo(PushCargoPower power) {
    requires(Robot.cargoCollector);
    this.power = power.key;
  }

  @Override
  protected void initialize() {
    setTimeout(TIMEOUT);
  }

  @Override
  protected void execute() {
    /** ejects the cargo */
    Robot.cargoCollector.setHolderMotors(power);
  }

  @Override
  protected boolean isFinished() {
    // the command is finished when the ball is being held
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.cargoCollector.setHolderMotors(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
