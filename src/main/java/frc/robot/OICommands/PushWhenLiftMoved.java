package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.LiftCommands.SetHeightIndex;

public class PushWhenLiftMoved extends CommandGroup {
  /**
   * if Push commandGroup only moved the lift, this CommandGroup will Push the right piece
   * after releasing the button that activate the Push CmdGroup thats give the operator 
   * "change his mind" if he dont want to score game piece in the middle heights right away
   */
  public PushWhenLiftMoved() {

      if(RobotStates.isHasCargo())
        addSequential(new PushCargo());
      else
        addSequential(new EjectHatch());
    addSequential(new WaitCommand(1));
    //lift will go down to 180 safe height to spin 
    addParallel(new SetHeightIndex(LiftHeight.kOneEightySafety));
    addSequential(new SetCargoFolderState(Value.kReverse));
  }

  @Override
  protected void initialize(){
    if(!RobotStates.isPushed()){
      RobotStates.setIsPushed(true);
    }
  }
}
