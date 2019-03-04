package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.DrivingCommands.ToggleDriveInverted;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetLiftHeight;
import frc.robot.OneEightyCommands.SetOneEightyAngle;

public class AfterHatchFeederPreparation extends CommandGroup {
  /**
   * preparing the robot to score hatchs after taking them from the feeder
   */
  public AfterHatchFeederPreparation() {
  }

  @Override
  protected void initialize(){
    //pay attention to the sequence:
    RobotStates.setHasCargo(false);
    addSequential(new SetHatchLock(Value.kForward));
    addSequential(new ToggleDriveInverted());
    addSequential(new WaitCommand(1));
    addSequential(new SetCargoFolderState(Value.kForward));
    addParallel(new SetLiftHeight(LiftHeight.kOneEightySafety));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetCargoFolderState(Value.kReverse));
    OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kStraight : OneEightyAngle.kBack;
    addParallel(new SetOneEightyAngle(angleToSet));
  }
}
