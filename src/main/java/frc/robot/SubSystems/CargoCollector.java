package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * a SS that collects the cargo and holds it by turning wheels on top of the
 * cargo and bringing it in
 */
public class CargoCollector extends Subsystem {
  /** the motor that turns the wheel for bringing in the cargo */
  private SpeedController motor;

  public CargoCollector(SpeedController motor) {
    this.motor = motor;
  }

  /** the functions sets the motor to turn the wheels that bring the cargo */
  public void setMotor(Double speed) {
    this.motor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}