package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

public class Push extends CommandGroup {
  /**
   * Pushes the right piece or gets the lift to the middle height
   */
  public Push() {
  }
  
  @Override
  protected void initialize(){
    //if the operator want to score in the middle rocket heights, 
    //the lift will go up to the middle heights if the last height was the height after collcecting game piece.
    //else (which mean we are in low, middle, high or cargo ship height) the right game piece will be ejected 
    if(RobotStates.getHeightIndex() == -1){ 
      //the next commandGroup that will run after releasing the same button,
      //will know that the right game piece already pushed
      RobotStates.setIsPushed(false);
      if(RobotStates.isHasCargo()){
        addParallel(new SetHeightIndex(LiftHeight.kRocketMiddleCargo));
        addSequential(new WaitCommand(0.5));
        OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
        addParallel(new SetOneEightyDesireAngle(angleToSet));
      }else{
        addParallel(new SetHeightIndex(LiftHeight.kRocketMiddleHatch));
        addSequential(new WaitCommand(0.5));
        OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kStraight : OneEightyAngle.kBack;
        addParallel(new SetOneEightyDesireAngle(angleToSet));
      }
    } else {
      RobotStates.setIsPushed(true); 
      if(RobotStates.isHasCargo())
        addSequential(new PushCargo());
      else
        addSequential(new EjectHatch());
    }
  }
}
