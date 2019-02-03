package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.PushCargo;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.Angle;
import frc.robot.RobotConstants.Height;
import frc.robot.RobotConstants.PushCargoByHeight;

public class ScoreCargo extends CommandGroup {
  double startingAngle;
  PushCargoByHeight pushCargoByHeight;
  Height height;
  Angle finishingAngle = RobotConstants.Angle.kStraight;;
  Height finishingHeight = RobotConstants.Height.kLiftBottom; ;

  public ScoreCargo(double startingAngle, Height height) {
    this.startingAngle = startingAngle;
    this.height = height;
    //connecting the Height to PushCargoByHeight to push the cargo in the right speed
    switch(height){
      case kRocketBottomCargo: pushCargoByHeight = PushCargoByHeight.kLowRocket;
      case kRocketMiddleCargo: pushCargoByHeight = PushCargoByHeight.kMiddleRocket;
      case kRocketTopCargo: pushCargoByHeight = PushCargoByHeight.kLowRocket;
      default: pushCargoByHeight = PushCargoByHeight.kCargoShip;
    }
    addSequential(new SetOneEightyAngle(startingAngle));
    addSequential(new SetLiftHeight(height));
    addSequential(new PushCargo(pushCargoByHeight));

    addSequential(new SetOneEightyAngle(finishingAngle));
    addSequential(new SetLiftHeight(finishingHeight));
  }
}
