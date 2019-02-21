/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotStates;

/**
 * Add your docs here.
 */
public class SetDriveInverted extends InstantCommand {
  boolean state;

  public SetDriveInverted(boolean state) {
    this.state = state;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    RobotStates.setDriveInverted(state);
  }

}
