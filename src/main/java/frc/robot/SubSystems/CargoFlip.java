/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CargoFlip extends Subsystem {
  SpeedController cargoFlipRightMotor, cargoFlipLeftMotor;
  SpeedControllerGroup cargoFlip;
  public CargoFlip(SpeedController cargoFlipRightMotor, SpeedController cargoFlipLeftMotor){
    this.cargoFlipRightMotor = cargoFlipRightMotor;
    this.cargoFlipLeftMotor = cargoFlipLeftMotor;
    this.cargoFlip = new SpeedControllerGroup(this.cargoFlipRightMotor, this.cargoFlipLeftMotor);
  }
  public void setFlip(Double speed){
    this.cargoFlip.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
