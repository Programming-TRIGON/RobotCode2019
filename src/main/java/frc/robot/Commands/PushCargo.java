package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PushCargo extends Command {
  double power;
  //defines power
  public PushCargo (double power) {
    //requires cargoCollector SS
    requires(Robot.cargoCollector);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //the cargo holder motors turn on 
    Robot.cargoCollector.setCargoHolderMotors(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //the command is finished when the ball is being held
    return isTimedOut();
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
