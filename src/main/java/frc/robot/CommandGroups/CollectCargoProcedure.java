package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetLiftHeight;

public class CollectCargoProcedure extends CommandGroup {
  double COLLECTOR_POWER = 0.5;
  double HOLDER_POWER = 0.7;

  // TODO: real values.
  public CollectCargoProcedure() {
    /** starts by unfolding the crago collecter */
    addSequential(new SetCargoFolderState(false));
    /** turn to the required angle */
    addSequential(new SetOneEightyAngle(RobotConstants.Angle.kStraight));
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetLiftHeight(RobotConstants.Height.kLiftBottom));
    /** collects the cargo */
    addSequential(new CollectCargo(this.COLLECTOR_POWER, this.HOLDER_POWER));
  }
}
