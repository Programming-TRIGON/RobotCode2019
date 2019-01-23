/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  SpeedController liftMotorRight, liftMotorLeft;
  Encoder encoder;
  SpeedControllerGroup liftMotorGroup = new SpeedControllerGroup(this.liftMotorLeft, this.liftMotorRight);

  public Lift(SpeedController liftMotorLeft, SpeedController liftMotorRight, Encoder encoder) {
    this.liftMotorLeft = liftMotorLeft;
    this.liftMotorRight = liftMotorRight;
    this.encoder = encoder;
  }

  public void setMotorSpeed(double power) {
    liftMotorGroup.set(power);
  }

  public double encoderTicks() {
    return encoder.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
