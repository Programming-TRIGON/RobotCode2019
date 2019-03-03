package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.Commands.ReachOneEightyAngle;
import frc.robot.Commands.SetOneEightyOverride;
import frc.robot.Commands.StabilizeOneEightyAngle;
import frc.robot.RobotConstants.OneEightyAngle;

public class SetOneEightyAngle extends CommandGroup {

  public SetOneEightyAngle(double angle) {
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }
  public SetOneEightyAngle(OneEightyAngle angle) {
    RobotStates.setDesireOneEightyAngle(angle);
    addSequential(new ReachOneEightyAngle(angle));
    addSequential(new StabilizeOneEightyAngle(angle));
    addSequential(new SetOneEightyOverride());
  }
}
