package frc.robot.LiftCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class LiftDefaultCommand extends CommandGroup {
  /**
   * Set lift height with overraid option
   */
  public LiftDefaultCommand(Supplier<LiftHeight> height) {
    addSequential(new ReachLiftHeight(height)); // this command will end when overraide lift state is true else it will do pid on the height given
    addSequential(new SetLiftOverride()); // this command will run when overraide lift state is true
  }
  public LiftDefaultCommand(LiftHeight height) {
    addSequential(new ReachLiftHeight(height)); // this command will end when overraide lift state is true else it will do pid on the height given
    addSequential(new SetLiftOverride()); // this command will run when overraide lift state is true
  }

}
