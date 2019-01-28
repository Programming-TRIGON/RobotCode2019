package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * the class that is on the lift and turns 180 degrees allowing the placement of
 * cargo/hatch according to the need of the driver
 */
public class OneEighty extends JoystickOverridableSubsystem {
  /** declares the motor that turns the SS */
  private TalonSRX motor;
  private AnalogPotentiometer potentiometer;

  public OneEighty(TalonSRX motor, AnalogPotentiometer potentiometer) {
    this.motor = motor;
    this.potentiometer = potentiometer;
  }

  /** turns the SS to where the driver wants it */
  public void setOneEighty(double power) {
    this.motor.set(ControlMode.PercentOutput, power);
  }

  public double getAngle() {
    return potentiometer.get();
  }

  @Override
  public void initDefaultCommand() {

  }

  @Override
  public void move(double power) {
    setOneEighty(power);
  }
}