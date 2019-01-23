package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoFlip extends Subsystem {
  SpeedController rightMotor, leftMotor;
  SpeedControllerGroup motorGroup;
  public CargoFlip(SpeedController rightMotor, SpeedController leftMotor){
    this.rightMotor = rightMotor;
    this.leftMotor = leftMotor;
    this.motorGroup = new SpeedControllerGroup(this.rightMotor, this.leftMotor);
  }
  public void setFlip(Double speed){
    this.motorGroup.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}
