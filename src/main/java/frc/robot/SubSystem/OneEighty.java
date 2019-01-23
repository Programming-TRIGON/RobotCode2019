/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystem;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


public class OneEighty extends Subsystem {
  SpeedController oneEightyMotor;
  public OneEighty(SpeedController oneEightyMotor){
    this.oneEightyMotor = oneEightyMotor;
  }
  public void setOneEighty(double power){
    this.oneEightyMotor.set(power);
  }

  @Override
  public void initDefaultCommand() {

  }
}
