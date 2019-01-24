package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoFold extends Subsystem {
  private SpeedControllerGroup motorGroup;

  public CargoFold(SpeedController rightMotor, SpeedController leftMotor) {
    this.motorGroup = new SpeedControllerGroup(rightMotor, leftMotor);
  }

  public void setFold(Double speed) {
    this.motorGroup.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}
