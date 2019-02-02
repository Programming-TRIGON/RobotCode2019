package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CollectCargo extends Command {
  //the power for the collectorMotor.
  double collectPower;
  //the power for the holderMotors.
  double holderPower;
  
  public CollectCargo(double collectPower, double holderPower) {
    //requires the cargoCollector
    requires(Robot.cargoCollector);
    this.collectPower = collectPower;
    this.holderPower = holderPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoCollector.setCollectorMotor(collectPower);
    Robot.cargoCollector.setHolderMotors(holderPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.cargoCollector.isHoldingBall();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
