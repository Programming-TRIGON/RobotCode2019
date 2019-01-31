package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PushCargo extends Command {
  double power;
  double TIMEOUT;
  //defines power
  public PushCargo (double power) {
    //requires cargoCollector SS
    requires(Robot.cargoCollector);
    this.power = power;
    this.TIMEOUT = 1;
  }


// Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(TIMEOUT);
  }

  @Override
  protected void execute() {
    /** the cargo holder motors turn on **/
    Robot.cargoCollector.setHolderMotors(power);
  }

  @Override
  protected boolean isFinished() {
    //the command is finished when the ball is being held
    return isTimedOut();
  }

  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
