package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants.LiftHeight;

public class SetLiftHeight extends Command {
  private Supplier<Double> height;
  private PIDController pidController;
  private PIDOutput pidOutput;
  private double lastTimeNotOnTarget;
  private double waitTime;
  private boolean isInfinate;
  private double tolerance = 1;
  private double kP = 0.2;
  private double kI = 0;
  private double kD = 0;
  private double timeFrame = 0.05;

  /** sets the height of the lift depending on the situation */
  public SetLiftHeight(LiftHeight finishingHeight) {
    this(finishingHeight.key, false);
  }

  public SetLiftHeight(double d, boolean isInfinate) {
    requires(Robot.lift);
    this.height = new Supplier<Double>() {
      @Override
      public Double get() {
        return d;
      }
    };
    this.isInfinate = isInfinate;
  }

  public SetLiftHeight(Supplier<Double> setpointSupplier, boolean isInfinate) {
    requires(Robot.lift);
    this.isInfinate = isInfinate;
    this.height = setpointSupplier;
  }

  @Override
  protected void initialize() {
    this.pidOutput = new PIDOutput() {
      public void pidWrite(double output) {
        Robot.lift.setMotorSpeed(output);
      }
    };
    pidController.setSetpoint(height.get());
    this.pidController = new PIDController(kP, kI, kD, Robot.lift.getEncoder(), this.pidOutput, timeFrame);
    pidController.setAbsoluteTolerance(tolerance);
    pidController.setOutputRange(-1, 1);
    pidController.enable();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    /** checks if we want to run the command forever */
    if (isInfinate)
      return false;
    /** checks if the lift is in position for lon enough */
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
