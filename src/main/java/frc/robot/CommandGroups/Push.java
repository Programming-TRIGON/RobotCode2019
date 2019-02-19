/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;

public class Push extends CommandGroup {
  /**
   * Pushes the right piece
   */
  public Push() {
    if(RobotStates.getHeightIndex() == -1){
      RobotStates.setHeightIndex(1);
      if(RobotStates.isHasCargo())
        addSequential(new SetLiftHeight(LiftHeight.kRocketMiddleCargo));
      else
        addSequential(new SetLiftHeight(LiftHeight.kRocketMiddleHatch));
    }
    if(RobotStates.isHasCargo())
      addSequential(new PushCargo());
    else
      addSequential(new EjectHatch());
  }
}
