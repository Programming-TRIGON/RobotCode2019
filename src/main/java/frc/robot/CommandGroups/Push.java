package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.Commands.PushCargo;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class Push extends CommandGroup {
  /**
   * Pushes the right piece
   */
  public Push() {
    if(RobotStates.getHeightIndex() == -1){
      if(RobotStates.isHasCargo()){
        addParallel(new SetLiftHeight(LiftHeight.kRocketMiddleCargo));
        addSequential(new WaitCommand(0.5));
        addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));//acording to driving
      }else{
        addParallel(new SetLiftHeight(LiftHeight.kRocketMiddleHatch));
        addSequential(new WaitCommand(0.5));
        addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));
      }
    }
    if(RobotStates.isHasCargo())
      addSequential(new PushCargo());
    else
      addSequential(new EjectHatch());
  }
}
