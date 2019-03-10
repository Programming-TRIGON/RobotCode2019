package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class AfterHatchFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting hatch from the floor
   */
  public AfterHatchFloorPreparation() {
    addSequential(new SetHatchCollectorState(Value.kReverse));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetHatchLock(Value.kForward));
    addSequential(new SetHeightIndex(LiftHeight.kOneEightySafety));
    addSequential(new SetCargoFolderState(Value.kReverse));
  }
}
