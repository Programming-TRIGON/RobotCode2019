package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A SS that collects the cargo and holds it by turning wheels on top of the
 * cargo and bringing it in.
 */
public class CargoCollector extends Subsystem {
  /** The motor that turns the wheel for bringing in the cargo. */
  private TalonSRX collectorMotor;
  /**
   * After the cargo collector collects the cargo it goes into the cargo holder.
   * these motors put the cargo in the holder. after lifted up these motors shoot
   * the cargo out of them.
   */
  private TalonSRX rightCargoHolder, leftCargoHolder;

  public CargoCollector(TalonSRX collectorMotor, TalonSRX rightCargoHolder, TalonSRX leftCargoHolder) {
    this.collectorMotor = collectorMotor;
    this.rightCargoHolder = rightCargoHolder;
    this.leftCargoHolder = leftCargoHolder;
  }

  /**
   * This function sets the speed at which the cargo collector motor will turn the
   * wheels to bring the cargo into the robot.
   */
  public void setCargoCollectorMotor(Double speed) {
    this.collectorMotor.set(ControlMode.PercentOutput, speed);
    ;
  }

  /**
   * This function sets the speed at which the cargo holder motor will turn to
   * bring the cargo into the holder.
   */
  public void setCargoHolderMotors(Double speed) {
    this.rightCargoHolder.set(ControlMode.PercentOutput, speed);
    this.leftCargoHolder.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}