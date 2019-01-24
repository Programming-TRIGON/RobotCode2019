package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/** extends and retracts the whole SS of the cargo collector */
public class CargoFold extends Subsystem {
  /** this motor group cnnects the two motors that brinng the SS up and down */
  private SpeedControllerGroup motorGroup;

  public CargoFold(SpeedController rightMotor, SpeedController leftMotor) {
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
