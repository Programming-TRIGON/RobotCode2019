package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PushCargo extends Command {
  double power;
  final double TIMEOUT = 1;
  //defines power
  public PushCargo (double power) {
    requires(Robot.cargoCollector);
    this.power = power;
  }


// Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(TIMEOUT);
  }

  @Override
  protected void execute() {
    Robot.cargoCollector.setHolderMotors(power);
  }

  @Override
  protected boolean isFinished() {
    //the command is finished when the ball is being held
    // note from editor: really?
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.cargoCollector.setHolderMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
