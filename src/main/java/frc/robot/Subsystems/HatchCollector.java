package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * the SS hatch collector collects hatch pannels that uses either motor or
 * solenoid depending on which we use to catch the hatch by connecting to the
 * scothes
 */
public class HatchCollector extends Subsystem {
  /** folds the SS down to catch the hatch */
  private DoubleSolenoid solenoid;
  // if there is no image processing then we use a motor
  /** folds the SS down to catch the hatch */
  public HatchCollector(DoubleSolenoid solenoid) {
    this.solenoid = solenoid;
  }

  /**
   * sets the state
   * us of the solenoid. DoubleSolenoids require "values" that
   * represent off, forward, and reversed, the function sets the value of the
   * solenoid to fold down/up for getiing the hatch or putting it on the hatch
   * holder SS
   */
  public void setSolenoid(DoubleSolenoid.Value value) {
    this.solenoid.set(value);
    this.solenoid.set(Value.kOff);
  }


  /**
   * the function folds the motor down/up for getting the hatch or putting it on
   * the hatch holder SS
   */

  @Override
  public void initDefaultCommand() {

  }
}
