package frc.robot.Commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLiftHeight extends Command {
  private double height;
  private PIDController pidController;
  private PIDOutput pidOutput;
  private double lastTimeNotOnTarget;
  private double waitTime;

  public SetLiftHeight(double height) {
    requires(Robot.lift);
    this.height = height;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.pidOutput = new PIDOutput() {
      public void pidWrite(double output) {
        Robot.lift.setMotorSpeed(output);
      }
    };
    this.pidController = new PIDController(0.2, 0, 0, Robot.lift.getPotentoimeter(), this.pidOutput, 0.05);
    pidController.setAbsoluteTolerance(1);
    pidController.setSetpoint(height);
    pidController.setOutputRange(-1, 1);
    pidController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (!pidController.onTarget()) {
      lastTimeNotOnTarget = Timer.getFPGATimestamp();
    }
    if (Timer.getFPGATimestamp() - lastTimeNotOnTarget >= waitTime)
      return true;
    else
      return false;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pidController.disable();
    pidController.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
