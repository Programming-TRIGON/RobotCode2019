package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;

public class SetOneEightyAngle extends CommandGroup {

  public SetOneEightyAngle(double angle) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }
  public SetOneEightyAngle(OneEightyAngle angle) {
    addSequential(new SetOneEightyDesireAngle(angle));
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }
}
