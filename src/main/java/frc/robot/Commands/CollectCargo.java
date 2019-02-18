package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** collects and holds the cargo */
public class CollectCargo extends Command {
  double collectorPower;
  double holderPower;

  public CollectCargo(double collectPower, double holderPower) {
    requires(Robot.cargoCollector);
    this.collectorPower = collectPower;
    this.holderPower = holderPower;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    /** sets the power of the holder and collector motors */
    Robot.cargoCollector.setCollectorMotor(collectorPower);
    Robot.cargoCollector.setHolderMotors(holderPower);
  }

  @Override
  protected boolean isFinished() {
    /** a switch checks if the it's holding a ball */
    return Robot.cargoCollector.isHoldingBall();
  }

  @Override
  protected void end() {
    Robot.cargoCollector.setCollectorMotor(0);
    Robot.cargoCollector.setHolderMotors(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
