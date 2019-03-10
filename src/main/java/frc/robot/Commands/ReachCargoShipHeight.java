package frc.robot.Commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.ReachLiftHeight;
import frc.robot.RobotConstants.LiftHeight;

public class ReachCargoShipHeight extends CommandGroup {
  /**
   * this shit cmdG made in distric #1
   */
  public ReachCargoShipHeight() {
    addSequential(new SetCargoFolderState(Value.kReverse));
    addSequential(new WaitCommand(0.4));
    addParallel(new ReachLiftHeight(LiftHeight.kRocketMiddleCargo));
    addSequential(new WaitCommand(0.45));
    addSequential(new SetCargoFolderState(Value.kForward));
  }
}
