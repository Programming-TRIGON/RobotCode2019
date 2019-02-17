
package frc.robot.Commands;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.RobotConstants;

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
    this.pidSettings=pidSettings;
  }
  
  public StabilizeOneEightyAngle(double angle) {
    requires(Robot.oneEighty);
    this.angle = angle;
  }

  public StabilizeOneEightyAngle(RobotConstants.OneEightyAngle angle) {
    this.angle = angle.key;
    requires(Robot.oneEighty);
    this.pidSettings = RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS;
  }

  @Override
  protected void initialize() {   
    this.pidController = new PIDController(pidSettings.getKP(),
    pidSettings.getKI(),
    pidSettings.getKD(),
    Robot.oneEighty.getPotentiometer(),
    (output) -> Robot.oneEighty.setOneEighty(output));

    //pidController.setAbsoluteTolerance(RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS.getTolerance());
    pidController.setAbsoluteTolerance(pidSettings.getTolerance());
    pidController.setOutputRange(-1, 1);
    pidController.setSetpoint(angle);
    pidController.enable();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    pidController.disable();
    pidController.close();    
  }
}
