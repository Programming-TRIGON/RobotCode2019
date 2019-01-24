package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoCollector extends Subsystem {
  private SpeedController motor;

  public CargoCollector(SpeedController motor) {
    this.motor = motor;
  }

  public void setMotor(Double speed) {
    this.motor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}