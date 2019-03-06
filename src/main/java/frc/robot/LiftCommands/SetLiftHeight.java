package frc.robot.LiftCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class SetLiftHeight extends CommandGroup {
  /**
   * Set lift height with overraid option
   */
  public SetLiftHeight(LiftHeight height) {
    //
    addSequential(new SetHeightIndex(height)); //write index to robot states
    addSequential(new ReachLiftHeight(height)); // this command will end when overraide lift state is true else it will do pid on the height given
    addSequential(new SetLiftOverride()); // this command will run when overraide lift state is true
  }
}
