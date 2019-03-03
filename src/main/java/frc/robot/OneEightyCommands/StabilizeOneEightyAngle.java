package frc.robot.OneEightyCommands;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;

public class StabilizeOneEightyAngle extends Command {
  private PIDController pidController;
  private double angle;
  private PIDSettings pidSettings;

  /**
   * 
   * @param angle the angle the SS seeks
   */
  public StabilizeOneEightyAngle(double angle, PIDSettings pidSettings) {
    requires(Robot.oneEighty);
    this.angle = angle;
    this.pidSettings = pidSettings;
  }

  public StabilizeOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle = angle;
    this.pidSettings = RobotConstants.RobotPIDSettings.ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS;
  }

  public StabilizeOneEightyAngle(RobotConstants.OneEightyAngle angle) {
    requires(Robot.oneEighty);
    this.angle = angle.key;
    this.pidSettings = RobotConstants.RobotPIDSettings.ONE_EIGHTY_STABILIZE_ANGLE_SETTINGS;
  }

  @Override
  protected void initialize() {
    if(this.angle == OneEightyAngle.kCargoCollection.key)
      Robot.oneEighty.setSafeControl(false);
    this.pidController = new PIDController(pidSettings.getKP(), pidSettings.getKI(), pidSettings.getKD(),
      Robot.oneEighty.getPotentiometer(), (output) -> Robot.oneEighty.setOneEighty(output));

    pidController.setAbsoluteTolerance(pidSettings.getTolerance());
    pidController.setOutputRange(-1, 1);
    pidController.setSetpoint(angle);
    pidController.enable();
  }

  @Override
  protected boolean isFinished() {
    return RobotStates.isOneEightyOverride();
  }

  @Override
  protected void end() {
    pidController.disable();
    pidController.close();
    Robot.oneEighty.setSafeControl(true);
    Robot.oneEighty.move(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
