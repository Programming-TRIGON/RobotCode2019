package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HatchHolder extends Subsystem {
  Solenoid frontSolenoid, rightBackSolenoid, leftBackSolenoid;
  public HatchHolder(Solenoid frontSolenoid, Solenoid rightBackSolenoid, Solenoid leftBackSolenoid){
    this.frontSolenoid = frontSolenoid; 
    this.rightBackSolenoid = rightBackSolenoid;
    this.leftBackSolenoid = leftBackSolenoid;

  }
  public void setCatch(Boolean on){
    this.frontSolenoid.set(on);
  }
  public void setPush(Boolean on){
    this.leftBackSolenoid.set(on);
    this.rightBackSolenoid.set(on);
  }
  @Override
  public void initDefaultCommand() {

  }
}