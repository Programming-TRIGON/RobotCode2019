package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchCollector extends Subsystem {
  Solenoid solenoid;
  //if there is no image procassing then we use a motor
  SpeedController motor;

  public HatchCollector(SpeedController motor, Solenoid solenoid){
    this.solenoid = solenoid;
    this.motor = motor;
  }

  public void setSolenoid(Boolean on){
    this.solenoid.set(on);
  }
  public void setMotor(Double speed){
    this.motor.set(speed);
  }
  @Override
  public void initDefaultCommand() {

  }
}
