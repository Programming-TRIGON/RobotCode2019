package frc.robot.Commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.LiftHeight;

public class ReachLiftHeight extends Command {
  private Supplier<Double> height;
  private PIDController pidController;
  private PIDOutput pidOutput;

  /** sets the height of the lift depending on the situation */
  public ReachLiftHeight(LiftHeight finishingHeight) {
    requires(Robot.lift);
    this.height = () -> finishingHeight.key;
  }

  @Override
  protected void initialize() {
    this.pidOutput = new PIDOutput() {
      public void pidWrite(double output) {
        Robot.lift.setMotorSpeed(output);
      }
    };
    this.pidController = new PIDController(5,0,15,
      Robot.lift.getEncoder(), this.pidOutput);
    pidController.setSetpoint(height.get());
    pidController.setAbsoluteTolerance(0);
    pidController.setOutputRange(-0.5, 1);
    pidController.enable();
  }

  @Override
  protected void execute() {
    if (!Robot.cargoFolder.isFold() && Robot.lift.getHeight() <= RobotConstants.LiftHeight.kCargoFolderSafty.key)
      Robot.cargoFolder.setFold(Value.kForward);
  }

  @Override
  protected boolean isFinished() {
    return RobotStates.isLiftOverride();
  }

  @Override
  protected void end() {
    pidController.disable();
    pidController.close();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
