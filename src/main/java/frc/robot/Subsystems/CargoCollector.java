package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This subsystem contains: - The roller that collects the cargo from the top -
 * The wheels that hold the cargo in the 180 lift
 */
public class CargoCollector extends JoystickOverridableSubsystem {
  /** The motor that turns the wheel for bringing in the cargo. */
  private TalonSRX collectorMotor;

  /**
   * After the cargo collector collects the cargo it goes into the cargo holder.
   * these motors put the cargo in the holder. after lifted up these motors shoot
   * the cargo out of them.
   */

  private TalonSRX leftHolder, rightHolder;
  private DigitalInput microswitch;

  public CargoCollector(TalonSRX collectorMotor, TalonSRX rightCargoHolder, TalonSRX leftCargoHolder, DigitalInput DI) {
    this.collectorMotor = collectorMotor;
    this.leftHolder = leftCargoHolder;
    this.rightHolder = rightCargoHolder;
    this.microswitch = DI;
    this.leftHolder.set(ControlMode.Follower, this.leftHolder.getDeviceID());
  }

  /**
   * This function sets the power at which the cargo collector motor will turn the
   * wheels to bring the cargo into the robot.
   */
  public void setCollectorMotor(double power) {
    this.collectorMotor.set(ControlMode.PercentOutput, power);

  }

  /**
   * This function sets the power at which the cargo holder motor will turn to
   * bring the cargo into the holder.
   */
  public void setHolderMotors(double power) {
    this.rightHolder.set(ControlMode.PercentOutput, power);
  }

  /**
   * This function returns true if the ball is being held
   */
  public boolean isHoldingBall() {
    return this.microswitch.get();
  }

  @Override
  public void initDefaultCommand() {

  }

  @Override
  public void move(double power) {
    setCollectorMotor(power);
  }
}