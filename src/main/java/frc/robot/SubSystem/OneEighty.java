package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * the class that is on the lift and turns 180 degrees allowing the placement of
 * cargo/hatch according to the need of the driver
 */
public class OneEighty extends Subsystem {
  /** declares the motor that turns the SS */
  private SpeedController motor;

  public OneEighty(SpeedController motor) {
    this.motor = motor;
  }

  /** turns the SS to where the driver wants it */
  public void setOneEighty(Double speed) {
    this.motor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}