/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.ResetLift;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetHatchCollectorState;
 /**
   * retracts all systems. 
   */
public class DefenceMode extends CommandGroup {
 
  Value retractFolder = Value.kReverse;
  Value retractHatch = Value.kReverse;
  public DefenceMode() {

    if(RobotStates.isHasCargo()){
      addSequential(new PushCargo());
    }
    else{ 
      addSequential(new EjectHatch());
    }
    addSequential(new SetOneEightyAngle(OneEightyAngle.kStraight));
    addSequential(new ResetLift());
    addParallel(new SetCargoFolderState(retractFolder));
    addSequential(new SetHatchCollectorState(retractHatch));

  }
}
