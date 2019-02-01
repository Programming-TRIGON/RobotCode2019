package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Folds and unfolds the cargo collector roller */
public class CargoFolder extends Subsystem {
  /** This motor group connects the two motors that bring the SS up and down */
  private DoubleSolenoid solenoid;
  /** these switches ensure that the cargo folder has reached its destination */
  private DigitalInput bottomSwitch, topSwitch;

  public CargoFolder(DoubleSolenoid solenoid, DigitalInput bottomSwitch, DigitalInput topSwitch) {
    this.solenoid = solenoid;
    this.bottomSwitch = bottomSwitch;
    this.topSwitch = topSwitch;
  }

  /** folds/unfolds the SS */
  public void setFold(DoubleSolenoid.Value value) {
    this.solenoid.set(value);
  }

  public boolean isUnfolded() {
    return bottomSwitch.get();
  }

  public boolean isFolded() {
    return topSwitch.get();
  }

  @Override
  public void initDefaultCommand() {

  }
}
