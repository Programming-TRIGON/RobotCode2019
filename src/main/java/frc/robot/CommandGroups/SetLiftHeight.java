package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.ReachLiftHeight;
import frc.robot.Commands.SetLiftOverride;
import frc.robot.RobotConstants.LiftHeight;

public class SetLiftHeight extends CommandGroup {
  /**
   * Set lift height with overraid option
   */
  public SetLiftHeight(LiftHeight height) {
    addSequential(new ReachLiftHeight(height)); //this command will end when overraide lift state is true else it will do pid on the height given 
    
    addSequential(new SetLiftOverride()); //this command will run when overraide lift state is true
  }
}
