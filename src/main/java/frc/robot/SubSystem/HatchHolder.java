/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HatchHolder extends Subsystem {
  Solenoid hatchHolderFrontSolenoid, hatchHolderBackRightSolenoid, hatchHolderBackLeftSolenoid;
  public HatchHolder(Solenoid hatchHolderF, Solenoid hatchHolderBR, Solenoid hatchHolderBL){
    this.hatchHolderFrontSolenoid = hatchHolderF;
    this.hatchHolderBackLeftSolenoid = hatchHolderBL;
    this.hatchHolderBackRightSolenoid = hatchHolderBR;
  }
  public void setCatch(Boolean on){
    this.hatchHolderFrontSolenoid.set(on);
  }
  public void setPush(Boolean on){
    this.hatchHolderBackLeftSolenoid.set(on);
    this.hatchHolderBackRightSolenoid.set(on);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
