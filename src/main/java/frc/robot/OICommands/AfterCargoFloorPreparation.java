/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.SetLiftHeight;
import frc.robot.OneEightyCommands.SetOneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class AfterCargoFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting cargo from the floor
   */
  public AfterCargoFloorPreparation() {
  }

  @Override
  protected void initialize(){
    addSequential(new CollectCargo(0,0));
    /** prepare the lift and the 180 subsystems to score */
    addParallel(new SetLiftHeight(LiftHeight.kOneEightyCargoSafety));
    addSequential(new WaitCommand(0.3));
    OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
    addParallel(new SetOneEightyAngle(angleToSet));
    addSequential(new SetCargoFolderState(Value.kReverse));
  }
}
