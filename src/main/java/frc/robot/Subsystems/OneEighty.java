package frc.robot.Subsystems;

import java.util.function.Predicate;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * the class that is on the lift and turns 180 degrees allowing the placement of
 * cargo/hatch according to the need of the driver
 */
public class OneEighty extends JoystickOverridableSubsystem {
  /** declares the motor that turns the SS */
  private TalonSRX motor;
  private AnalogPotentiometer potentiometer;
  //This supplier checks if the S.S. is high enough to move.
  private AnalogInput analogPotentiometer;

  public OneEighty(TalonSRX motor, AnalogInput potentiometer){
    this.motor = motor;
    this.analogPotentiometer = potentiometer;
  } 

  /** turns the SS to where the driver wants it */
  public void setOneEighty(double power) {
      this.motor.set(ControlMode.PercentOutput, power);
  }

  public double getAngle() {
    return Math.exp(analogPotentiometer.getVoltage());
  }
  public AnalogPotentiometer getPotentiometer(){
    return this.potentiometer;
  }

  @Override
  public void initDefaultCommand() {

  }

  @Override
  public void move(double power) {
    setOneEighty(power);
  }
}