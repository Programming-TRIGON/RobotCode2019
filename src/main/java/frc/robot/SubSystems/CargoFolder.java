package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Folds the whole SS of the cargo collector */
public class CargoFolder extends Subsystem {
  /** This motor group connects the two motors that bring the SS up and down */
  private SpeedControllerGroup motorGroup;

  public CargoFolder(SpeedController rightMotor, SpeedController leftMotor) {
    this.motorGroup = new SpeedControllerGroup(rightMotor, leftMotor);
  }

  /** folds/unfolds the SS */
  public void setFold(Double speed) {
    this.motorGroup.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}
