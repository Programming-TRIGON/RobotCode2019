package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.PushCargoPower;

public class ScoreCargo extends CommandGroup {
  double startingAngle;
  PushCargoPower pushCargoByHeight;
  LiftHeight height;
  OneEightyAngle finishingAngle = RobotConstants.OneEightyAngle.kStraight;
  LiftHeight finishingHeight = RobotConstants.LiftHeight.kLiftBottom; 


  public ScoreCargo(double startingAngle, LiftHeight height) {
    this.startingAngle = startingAngle;
    this.height = height;
    //connecting the Height to PushCargoByHeight to push the cargo in the right speed
    switch(height){
      case kRocketBottomCargo: pushCargoByHeight = PushCargoPower.kLowRocket; 
      break;
      case kRocketMiddleCargo: pushCargoByHeight = PushCargoPower.kMiddleRocket;
      break;
      case kRocketTopCargo: pushCargoByHeight = PushCargoPower.kLowRocket;
      break;
      default: pushCargoByHeight = PushCargoPower.kCargoShip;
      break;
    }
    addSequential(new SetLiftHeight(height));
    addSequential(new SetOneEightyAngle(startingAngle));
    addSequential(new PushCargo(pushCargoByHeight));

    addSequential(new SetOneEightyAngle(finishingAngle));
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
