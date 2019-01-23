package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Lift extends Subsystem {
  SpeedController rightMotor, leftMotor;
  Encoder encoder;
  SpeedControllerGroup motorGroup;
  public Lift(SpeedController leftMotor, SpeedController rightMotor, Encoder encoder) {
    this.motorGroup = new SpeedControllerGroup(this.rightMotor = rightMotor, this.leftMotor = leftMotor);
    this.encoder = encoder;
  }

  public void setMotorSpeed(Double speed) {
    motorGroup.set(speed);
  }
  //TODO encoder ticks to meters
  public double getHight() {
    return encoder.get();
  }

  @Override
  public void initDefaultCommand() {

  }
}