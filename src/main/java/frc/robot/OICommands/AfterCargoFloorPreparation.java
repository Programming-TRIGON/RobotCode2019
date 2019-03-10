/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.CargoCollectorCommands.CollectCargo;

public class AfterCargoFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting cargo from the floor
   */
  public AfterCargoFloorPreparation() {
    addSequential(new CollectCargo(0,0,false), 0.05);
    /** prepare the lift and the 180 subsystems to score */
    // addParallel(new SetLiftHeight(LiftHeight.kOneEightyCargoSafety));
    // addSequential(new WaitCommand(0.3));
    // Supplier<OneEightyAngle> angleToSet = ()-> RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
    // addParallel(new SetOneEightyAngle(angleToSet));
    // addSequential(new SetCargoFolderState(Value.kForward));
  }
}
