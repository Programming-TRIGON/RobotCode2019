package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.Commands.OneEightyOverrideCheck;

/**
 * the class that is on the lift and turns 180 degrees allowing the placement of
 * cargo/hatch according to the need of the driver
 */
public class OneEighty extends JoystickOverridableSubsystem {
  /** declares the motor that turns the SS */
  private TalonSRX motor;
  private AnalogPotentiometer potentiometer;
  // This supplier checks if the S.S. is high enough to move.

  public OneEighty(TalonSRX motor, AnalogPotentiometer potentiometer) {
    this.motor = motor;
    this.potentiometer = potentiometer;
    motor.setInverted(true);
    motor.setNeutralMode(NeutralMode.Brake);
  }

  public void moveOneEightyOverride(double power) {
    this.motor.set(ControlMode.PercentOutput, power);
  }

  /** turns the SS to where the driver wants it */
  public void setOneEighty(double power) {
    if ((power > 0 && getAngle() >= 225) || (power < 0 && getAngle() <= 0))
      this.motor.set(ControlMode.PercentOutput, 0);
    else
      this.motor.set(ControlMode.PercentOutput, power);
  }

  public double getAngle() {
    return potentiometer.get();
  }

  public AnalogPotentiometer getPotentiometer() {
    return this.potentiometer;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OneEightyOverrideCheck());
  }

  @Override
  public void move(double power) {
    setOneEighty(power);
  }
}