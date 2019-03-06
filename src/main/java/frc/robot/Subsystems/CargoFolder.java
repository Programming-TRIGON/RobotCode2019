package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Folds and unfolds the cargo collector */
public class CargoFolder extends Subsystem {
  private DoubleSolenoid Folder;

  public CargoFolder(DoubleSolenoid solenoid) {
    this.Folder = solenoid;
    setFold(Value.kForward);
  }

  /** folds/unfolds the SS then turns the solenoid off for safety */
  public void setFold(DoubleSolenoid.Value value) {
    this.Folder.set(value);
  }

  public boolean isFold(){
    return this.Folder.get().equals(Value.kForward);
  }

  @Override
  public void initDefaultCommand() {

  }
}
