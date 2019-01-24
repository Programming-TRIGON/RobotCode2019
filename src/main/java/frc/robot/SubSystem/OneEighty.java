package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OneEighty extends Subsystem {
  private SpeedController motor;

  public OneEighty(SpeedController motor) {
    this.motor = motor;
  }

  public void setOneEighty(Double speed) {
    this.motor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}