package frc.robot.Subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.RobotStates;
import frc.robot.OneEightyCommands.OneEightyDefaultCommand;

/**
 * the class that is on the lift and turns 180 degrees allowing the placement of
 * cargo/hatch according to the need of the driver
 */
public class OneEighty extends JoystickOverridableSubsystem {
  /** declares the motor that turns the SS */
  private TalonSRX motor;
  private AnalogPotentiometer potentiometer;
  private boolean isSafe = true;
  Supplier<Double> liftHeight;
  // This supplier checks if the S.S. is high enough to move.

  public OneEighty(TalonSRX motor, AnalogPotentiometer potentiometer, Supplier<Double> liftHeight) {
    this.motor = motor;
    this.potentiometer = potentiometer;
    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    this.liftHeight = liftHeight;
  }

  public void moveOneEightyOverride(double power) {
    this.motor.set(ControlMode.PercentOutput, 0.5 * power);
  }

  /** turns the SS to where the driver wants it */
  public void setOneEighty(double power) {
    // if (((power > 0 && getAngle() >= OneEightyAngle.kTopStraight.key)
    //     || (power < 0 && getAngle() <= OneEightyAngle.kTopBack.key) 
    // )//|| (Robot.lift.getHeight() <= LiftHeight.kOneEightySafety.key))
    //     && this.isSafe)
    //   this.motor.set(ControlMode.PercentOutput, 0);
    // else
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
    setDefaultCommand(new OneEightyDefaultCommand(()->RobotStates.getDesireOneEightyAngle(), liftHeight));
  }

  @Override
  public void move(double power) {
    setOneEighty(power);
  }

  @Override
  public void setSafeControl(boolean isSafe) {
    this.isSafe = isSafe;
  }
}