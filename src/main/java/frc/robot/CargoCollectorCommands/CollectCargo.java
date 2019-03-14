package frc.robot.CargoCollectorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** collects and holds the cargo */
public class CollectCargo extends Command {
  double collectorPower;
  double holderPower;
  boolean switchStop;

  public CollectCargo(double collectPower, double holderPower, boolean switchStop) {
    requires(Robot.cargoCollector);
    this.collectorPower = collectPower;
    this.holderPower = holderPower;
    this.switchStop = switchStop;
  }
  public CollectCargo(double collectPower, double holderPower) {
    this(collectPower, holderPower, true);
  }

  

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    /** sets the power of the holder and collector motors */
    Robot.cargoCollector.setCollectorMotor(collectorPower);
    Robot.cargoCollector.setHolderMotors(-holderPower);
  }

  @Override
  protected boolean isFinished() {
    /** a switch checks if the it's holding a ball */
    return Robot.cargoCollector.isHoldingBall() && switchStop;
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
