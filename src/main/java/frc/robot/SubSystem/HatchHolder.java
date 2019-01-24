package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchHolder extends Subsystem {
  /** this solenoid opens a pvc to catch the hatch */
  private DoubleSolenoid catchSolenoid;
  /** pushes the catch solenoid to catch the hacth */
  private DoubleSolenoid rightPushSolenoid, leftPushSolenoid;

  public HatchHolder(DoubleSolenoid catchSolenoid, DoubleSolenoid rightPushSolenoid, DoubleSolenoid leftPushSolenoid) {
    this.catchSolenoid = catchSolenoid;
    this.rightPushSolenoid = rightPushSolenoid;
    this.leftPushSolenoid = leftPushSolenoid;

  }

  /**
   * sets the status of the front solenoid, the one that chatches the Hatch.
   * DoubleSolinoids require "values" that represent off, forward, and reversed
   */
  public void setCatch(Value value) {
    this.catchSolenoid.set(value);
  }

  /**
   * sets the status of the rear solenoids, the ones that pushes the SS outwards.
   * DoubleSolinoids require "values" that represent off, forward, and reversed
   */
  public void setPush(Value value) {
    this.leftPushSolenoid.set(value);
    this.rightPushSolenoid.set(value);
  }

  @Override
  public void initDefaultCommand() {

  }
}