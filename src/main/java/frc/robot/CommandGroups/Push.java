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
