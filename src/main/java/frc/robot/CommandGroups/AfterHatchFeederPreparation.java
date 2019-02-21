package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class AfterHatchFeederPreparation extends CommandGroup {
  /**
   * preparing the robot to score hatchs after taking them from the feeder
   */
  public AfterHatchFeederPreparation() {
    RobotStates.setHasCargo(false);
    addSequential(new SetHatchLock(Value.kForward));
    RobotStates.setDriveInverted(!RobotStates.isDriveInverted());
    addSequential(new WaitCommand(0.3));
    RobotStates.setHeightIndex(-1);
    addParallel(new SetLiftHeight(LiftHeight.kOneEightySafety));
    OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kStraight : OneEightyAngle.kBack;
    addParallel(new SetOneEightyAngle(angleToSet));
    addSequential(new WaitCommand(0.3));
    RobotStates.setHeightIndex(0);
    addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
  }
}
