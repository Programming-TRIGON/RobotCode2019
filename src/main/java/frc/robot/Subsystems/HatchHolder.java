package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchHolder extends Subsystem {
  /**
   * hatchLocker solenoid opens the pvc to catch the hatch and hatchEjector push
   * the mechanism farword to chatch the hatch
   */
  private DoubleSolenoid hatchLocker, hatchEjector;
  private Value lockState = Value.kReverse, ejectionState = Value.kReverse;

  public HatchHolder(DoubleSolenoid pvcSolenoid, DoubleSolenoid pushSolenoid) {
    this.hatchLocker = pvcSolenoid;
    this.hatchEjector = pushSolenoid;
    // defult values:
    setLock(Value.kReverse);
    setEjection(Value.kReverse);
  }

  /**
   * sets the status of the front solenoid, the one that chatches the Hatch.
   * DoubleSolinoids require "values" that represent off, forward, and reversed.
   */
  public void setLock(Value value) {
    lockState = value;

    if (ejectionState == kReverse) {
      this.hatchLocker.set(value);
    }
  }

  /**
   * sets the status of the rear solenoids, the ones that pushes the SS outwards.
   * DoubleSolinoids require "values" that represent off, forward, and reversed
   */
  public void setEjection(Value value) {
    ejectionState = value;

    if (lockState == kReverse) {
      this.hatchEjector.set(value);
    }
  }

  @Override
  public void initDefaultCommand() {
  }
}