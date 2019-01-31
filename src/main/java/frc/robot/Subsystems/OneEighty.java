package frc.robot.Subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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
  private Supplier<Boolean> liftSupplier;

  public OneEighty(TalonSRX motor, AnalogPotentiometer potentiometer) {
    this.motor = motor;
    this.potentiometer = potentiometer;
    liftSupplier = () -> Robot.lift.getHeight() >= RobotConstants.oneEighty.MINIMUM_HEIGHT;
  } 

  /** turns the SS to where the driver wants it */
  public void setOneEighty(double power) {
    if(liftSupplier.get())
      this.motor.set(ControlMode.PercentOutput, power);
  }

  public double getAngle() {
    return potentiometer.get();
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