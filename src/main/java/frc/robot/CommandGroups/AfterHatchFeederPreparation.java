package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.Commands.SetHatchLock;
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
    addParallel(new SetLiftHeight(LiftHeight.kOneEightySafety));
    addSequential(new WaitCommand(0.3));
    OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kStraight : OneEightyAngle.kBack;
    addParallel(new SetOneEightyAngle(angleToSet));
    addSequential(new WaitCommand(0.3));
    addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
  }
}
