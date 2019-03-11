package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.RumbleXbox;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class AfterHatchFeederPreparation extends CommandGroup {
  /**
   * preparing the robot to score hatchs after taking them from the feeder
   */
  public AfterHatchFeederPreparation() {
    addSequential(new SetHatchLock(Value.kForward));
    addParallel(new RumbleXbox(false));
    addSequential(new WaitCommand(0.85));
    addSequential(new SetHeightIndex(LiftHeight.kOneEightySafety));
    addSequential(new WaitCommand(0.5));
    addParallel(new SetCargoFolderState(Value.kForward));
    addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kBack));
  }
}
