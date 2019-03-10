/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.OICommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class AfterHatchFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting hatch from the floor
   */
  public AfterHatchFloorPreparation() {
    addSequential(new SetHatchCollectorState(Value.kReverse));
    addSequential(new SetHatchLock(Value.kForward));
    addParallel(new SetHeightIndex(LiftHeight.kOneEightyCargoSafety));
    addSequential(new WaitCommand(0.3));
    Supplier<OneEightyAngle> angleToSet = ()-> RobotStates.isDriveInverted() ? OneEightyAngle.kStraight : OneEightyAngle.kBack; 
    addParallel(new SetOneEightyDesireAngle(angleToSet));
    addSequential(new SetCargoFolderState(Value.kReverse));
  }
}
