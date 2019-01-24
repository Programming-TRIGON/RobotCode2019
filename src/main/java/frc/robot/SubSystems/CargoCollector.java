package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A SS that collects the cargo and holds it by turning wheels on top of the
 * cargo and bringing it in.
 */
public class CargoCollector extends Subsystem {
  /** The motor that turns the wheel for bringing in the cargo. */
  private SpeedController collectorMotor;
  /**
   * After the cargo collector collects the cargo it goes into the cargo holder.
   * these motors put the cargo in the holder. after lifted up these motors shoot
   * the cargo out of them.
   */
  private SpeedControllerGroup cargoHolder;

  public CargoCollector(SpeedController collectorMotor, SpeedController rightCargoHolder,
      SpeedController leftCargoHolder) {
    this.collectorMotor = collectorMotor;
    this.cargoHolder = new SpeedControllerGroup(rightCargoHolder, leftCargoHolder);
  }

  /**
   * This function sets the speed at which the cargo collector motor will turn the
   * wheels to bring the cargo into the robot.
   */
  public void setCargoCollectorMotor(Double speed) {
    this.collectorMotor.set(speed);
  }

  /**
   * This function sets the speed at which the cargo holder motor will turn to
   * bring the cargo into the holder.
   */
  public void setCargoHolderMotors(Double speed) {
    this.cargoHolder.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}