package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchHolder extends Subsystem {
  /** hatchLocker solenoid opens the pvc to catch the hatch and hatchEjector push the mechanism farword to chatch the hatch*/
  private DoubleSolenoid hatchLocker, hatchEjector;
  private Value lockState = Value.kReverse, ejectionState = Value.kReverse;

  public HatchHolder(DoubleSolenoid pvcSolenoid, DoubleSolenoid pushSolenoid) {
    this.hatchLocker = pvcSolenoid;
    this.hatchEjector = pushSolenoid;
    //defult values:    
    setLock(Value.kReverse);
    setEjection(Value.kReverse);
    }

  /**
   * sets the status of the front solenoid, the one that chatches the Hatch.
   * DoubleSolinoids require "values" that represent off, forward, and reversed.
   */
  public void setLock(Value value) {
    lockState = value;
    //if(!ejectionState.equals(Value.kForward))
    this.hatchLocker.set(value);
    //this.hatchLocker.set(Value.kOff);
  }

  /**
   * sets the status of the rear solenoids, the ones that pushes the SS outwards.
   * DoubleSolinoids require "values" that represent off, forward, and reversed
   */
  public void setEjection(Value value) {
    //if(!lockState.equals(Value.kForward))
    this.hatchEjector.set(value);
    //this.hatchEjector.set(Value.kOff);
  }

  @Override
  public void initDefaultCommand() {

  }
}