package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.LiftHeight;

public class SetHeightIndex extends Command {
  LiftHeight height;
  public SetHeightIndex(LiftHeight height) {
    this.height = height;
  }

  @Override
  protected void initialize() {
    //set height index acorrding to height 
    switch (height) {
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
      default: 
        RobotStates.setHeightIndex(-1);
        break;
      }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
