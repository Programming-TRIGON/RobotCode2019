package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchHolder extends Subsystem {
  /** this solenoid opens a pvc to catch the hatch */
  private DoubleSolenoid pvcSolenoid;
  /** these solenoids push the mechanism farword to chatch the hatch */
  private DoubleSolenoid pushSolenoid;

  public HatchHolder(DoubleSolenoid pvcSolenoid, DoubleSolenoid pushSolenoid) {
    this.pvcSolenoid = pvcSolenoid;
    this.pushSolenoid = pushSolenoid;
  }

  /**
   * sets the status of the front solenoid, the one that chatches the Hatch.
   * DoubleSolinoids require "values" that represent off, forward, and reversed.
   */
  public void setLock(DoubleSolenoid.Value value) {
    this.pvcSolenoid.set(value);
    this.pvcSolenoid.set(Value.kOff);
  }

  /**
   * sets the status of the rear solenoids, the ones that pushes the SS outwards.
   * DoubleSolinoids require "values" that represent off, forward, and reversed
   */
  public void setEjection(DoubleSolenoid.Value value) {
    this.pushSolenoid.set(value);
  }

  @Override
  public void initDefaultCommand() {

  }
}