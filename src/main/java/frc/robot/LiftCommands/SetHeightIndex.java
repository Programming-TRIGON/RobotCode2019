package frc.robot.LiftCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.LiftHeight;

public class SetHeightIndex extends Command {
  Supplier<LiftHeight> heightSupplier;
  public SetHeightIndex(LiftHeight height) {
    this.heightSupplier = () -> height;
  }
  public SetHeightIndex(Supplier<LiftHeight> height) {
    this.heightSupplier = height;
  }

  @Override
  protected void initialize() {
    RobotStates.setLiftHeight(heightSupplier.get());
    //set height index acorrding to height 
    LiftHeight height = heightSupplier.get();
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
        break;
      /*case kRocketTopCargo:
        RobotStates.setHeightIndex(2);
        break;
      case kRocketTopHatch:
        RobotStates.setHeightIndex(2);
        break;*/
      case kCargoShip:
        RobotStates.setHeightIndex(2); //was 3 
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
