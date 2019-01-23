/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HatchCollector extends Subsystem {
  Solenoid hatchCollectorSolenoid;
  //if there is no image procassing then we use a motor
  SpeedController hatchCollectorMotor;

  public HatchCollector(SpeedController hatchCollectorMotor, Solenoid hatchCollectorSolenoid){
    this.hatchCollectorSolenoid = hatchCollectorSolenoid;
    this.hatchCollectorMotor = hatchCollectorMotor;
  }

  public void setSolenoid(Boolean solenoidOn){
    this.hatchCollectorSolenoid.set(solenoidOn);
  }
  public void setMotor(Double power){
    this.hatchCollectorMotor.set(power);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
