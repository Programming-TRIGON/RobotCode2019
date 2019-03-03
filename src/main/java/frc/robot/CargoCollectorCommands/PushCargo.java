package frc.robot.CargoCollectorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** puts the cargo in the rocket */

public class PushCargo extends Command {
  final double TIMEOUT = 1;
  /** accepts the power from an Enum */
  public PushCargo() {
    requires(Robot.cargoCollector);
  }

  @Override
  protected void initialize() {
    setTimeout(TIMEOUT);
    /** ejects the cargo */
    Robot.cargoCollector.setHolderMotors(1.5);
  }

  @Override
  protected void execute() {
    
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
