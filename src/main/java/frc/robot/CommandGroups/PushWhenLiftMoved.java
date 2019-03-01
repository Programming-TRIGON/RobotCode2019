package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.Commands.PushCargo;

public class PushWhenLiftMoved extends CommandGroup {
  /**
   * if Push commandGroup only moved the lift, this CommandGroup will Push the right piece
   * after releasing the button that activate the Push CmdGroup thats give the operator 
   * "change his mind" if he dont want to score game piece in the middle heights right away
   */
  public PushWhenLiftMoved() {
    if(!RobotStates.isPushed()){
      RobotStates.setIsPushed(true);
      if(RobotStates.isHasCargo())
        addSequential(new PushCargo());
      else
        addSequential(new EjectHatch());
    }
  }
}
