package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.Commands.ReachLiftHeight;
import frc.robot.Commands.SetLiftOverride;
import frc.robot.RobotConstants.LiftHeight;

public class SetLiftHeight extends CommandGroup {
  /**
   * Set lift height with overraid option
   */
  public SetLiftHeight(LiftHeight height) {
    switch (height) {
    case kCargoCollection:
      RobotStates.setHeightIndex(-1);
      break;
    case kHatchCollection:
      RobotStates.setHeightIndex(-1);
      break;
    case kOneEightySafety:
      RobotStates.setHeightIndex(-1);
      break;
    case kLiftBottomHatch:
      RobotStates.setHeightIndex(0);
      break;
    case kRocketBottomCargo:
      RobotStates.setHeightIndex(0);
      break;
    case kRocketMiddleCargo:
      RobotStates.setHeightIndex(1);
      break;
    case kRocketMiddleHatch:
      RobotStates.setHeightIndex(1);
    case kRocketTopCargo:
      RobotStates.setHeightIndex(2);
      break;
    case kRocketTopHatch:
      RobotStates.setHeightIndex(2);
      break;
    case kCargoShip:
      RobotStates.setHeightIndex(3);
      break;
    }
    addSequential(new ReachLiftHeight(height)); // this command will end when overraide lift state is true else it will do pid on the height given
    addSequential(new SetLiftOverride()); // this command will run when overraide lift state is true
  }
}
