package frc.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.LiftHeight;

public class SetLiftHeight extends Command {
  private Supplier<Double> height;
  private PIDController pidController;
  private PIDOutput pidOutput;
  private PIDSettings pidSettings;

  /** sets the height of the lift depending on the situation */
  public SetLiftHeight(LiftHeight finishingHeight) {
    this(finishingHeight.key);
  }

  public SetLiftHeight(double d) {
    requires(Robot.lift);
    this.height = () -> d;
  }


  public SetLiftHeight(Supplier<Double> setpointSupplier, PIDSettings pidSettings) {
    requires(Robot.lift);
    this.height = setpointSupplier;
    this.pidSettings = pidSettings;
  }

  @Override
  protected void initialize() {
    this.pidOutput = new PIDOutput() {
      public void pidWrite(double output) {
          Robot.lift.setMotorSpeed(output);
      }
    };
    pidController.setSetpoint(height.get());
    this.pidController = new PIDController(pidSettings.getKP(), pidSettings.getKI(), pidSettings.getKD(),
        Robot.lift.getEncoder(), this.pidOutput);
    pidController.setAbsoluteTolerance(pidSettings.getTolerance());
    pidController.setOutputRange(-1, 1);
    pidController.enable();
  }

  @Override
  protected void execute() {
    double newSetpoint = height.get();
    if (pidController.getSetpoint() != newSetpoint)
      pidController.setSetpoint(newSetpoint);
    if (newSetpoint <= RobotConstants.LiftHeight.kCargoFolderSafty.key)
      new SetCargoFolderState(Value.kForward).start();
  }

  @Override
  protected boolean isFinished() {
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
