package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.PrepareToScoreHeight;

public class PrepareToScore extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PrepareToScore(PrepareToScoreHeight height) {
    LiftHeight heightToSet = LiftHeight.kOneEightySafety;
    OneEightyAngle angleToSet = OneEightyAngle.kFeeder;

    // We need to know whether to angle the 180 forward or reverse
    if (!RobotStates.isDriveInverted()){
      angleToSet = RobotStates.isHasCargo() ? OneEightyAngle.kStraight : OneEightyAngle.kBack; 
    }
    else {
      angleToSet = RobotStates.isHasCargo() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
    }

    // Choose which height should be set based on what the operator input and what game piece we have
    switch (height){
      case kLow:
        RobotStates.setHeightIndex(0);
        if (RobotStates.isHasCargo())
          heightToSet = LiftHeight.kRocketBottomCargo;
        else 
          heightToSet = LiftHeight.kRocketBottomHatch;
        break;
      case kMedium:
        RobotStates.setHeightIndex(1);
        if (RobotStates.isHasCargo())
          heightToSet = LiftHeight.kRocketMiddleCargo;
        else 
          heightToSet = LiftHeight.kRocketMiddleHatch;
        break;
      case kHigh:
        RobotStates.setHeightIndex(2);
        if (RobotStates.isHasCargo()){
          heightToSet = LiftHeight.kRocketTopCargo;
          // The only time the 180 isn't straight is when it has to be angled up
          angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kTopBack : OneEightyAngle.kTopStraight;
        }
        else
          heightToSet = LiftHeight.kRocketTopHatch;
        break;
      case kCargoShip:
        if(RobotStates.isHasCargo())
          heightToSet = LiftHeight.kCargoShip;
        else
          heightToSet = LiftHeight.kRocketBottomHatch;
    }
    
    addSequential(new SetLiftHeight(heightToSet));
    addSequential(new SetOneEightyAngle(angleToSet));
  }

  public PrepareToScore(boolean increaseHeight) {
    LiftHeight heightToSet = LiftHeight.kOneEightySafety;
    OneEightyAngle angleToSet = OneEightyAngle.kFeeder;

    // We need to know whether to angle the 180 forward or reverse
    if (!RobotStates.isDriveInverted()){
     angleToSet = RobotStates.isHasCargo() ? OneEightyAngle.kStraight : OneEightyAngle.kBack; 
    }
    else {
      angleToSet = RobotStates.isHasCargo() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
    }
    
    if(increaseHeight)
      RobotStates.increaseHeight();
    else 
      RobotStates.decreaseHeight();
    // Choose which height should be set based on what the operator input and what game piece we have
    switch (RobotConstants.heights[RobotStates.getHeightIndex()]){
      case kLow:
        if (RobotStates.isHasCargo())
          heightToSet = LiftHeight.kRocketBottomCargo;
        else 
          heightToSet = LiftHeight.kRocketBottomHatch;
        break;
      case kMedium:
        if (RobotStates.isHasCargo())
          heightToSet = LiftHeight.kRocketMiddleCargo;
        else 
          heightToSet = LiftHeight.kRocketMiddleHatch;
        break;
      case kHigh:
        if (RobotStates.isHasCargo()){
          heightToSet = LiftHeight.kRocketTopCargo;
          // The only time the 180 isn't straight is when it has to be angled up
          angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kTopBack : OneEightyAngle.kTopStraight;
        }
        else
          heightToSet = LiftHeight.kRocketTopHatch;
        break;
      case kCargoShip:
        if(RobotStates.isHasCargo())
          heightToSet = LiftHeight.kCargoShip;
        else
          heightToSet = LiftHeight.kRocketBottomHatch;
    }
    
    addSequential(new SetLiftHeight(heightToSet));
    addSequential(new SetOneEightyAngle(angleToSet));
  }
}