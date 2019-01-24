package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchCollector extends Subsystem {
  private DoubleSolenoid solenoid;
  // if there is no image procassing then we use a motor
  private SpeedController motor;

  public HatchCollector(SpeedController motor, DoubleSolenoid solenoid) {
    this.solenoid = solenoid;
    this.motor = motor;
  }

  /**
   * sets the status of the solenoid. DoubleSolinoids require "values" that
   * represent off, forward, and reversed
   */
  public void setSolenoid(Value value) {
    this.solenoid.set(value);
  }

  public void setMotor(Double speed) {
    this.motor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}
