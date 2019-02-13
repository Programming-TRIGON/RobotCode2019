/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.TestCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.Commands.MoveSubsystemWithTimeout;

public class CargoRollerTest extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoRollerTest() {
    addSequential(new MoveSubsystemWithTimeout(Robot.cargoCollector, 0.3, 1));
  }
}
