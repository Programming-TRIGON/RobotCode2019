package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.DrivingCommands.ToggleDriveInverted;
import frc.robot.HatchHolderCommands.SetHatchLock;

public class AfterHatchFeederPreparation extends CommandGroup {
  /**
   * preparing the robot to score hatchs after taking them from the feeder
   */
  public AfterHatchFeederPreparation() {
    addSequential(new SetHatchLock(Value.kForward));
    addSequential(new ToggleDriveInverted());
    /*addSequential(new WaitCommand(1));
    addSequential(new SetCargoFolderState(Value.kForward));
    addParallel(new SetLiftHeight(LiftHeight.kOneEightySafety));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetCargoFolderState(Value.kReverse));
    addParallel(new SetOneEightyAngle(OneEightyAngle.kBack));*/
  }
}
