package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.Angle;
import frc.robot.RobotConstants.Height;

public class CollectCargoProcedure extends CommandGroup {
  Angle angle = RobotConstants.Angle.kStraight;
  double COLLECTOR_POWER = 0.5;
  double HOLDER_POWER = 0.7;
  Height height;
//TODO: real values.
  public CollectCargoProcedure() {
    this.height = RobotConstants.Height.kLiftBottom;
    /** starts by unfolding the crago collecter */
    addSequential(new SetCargoFolderState(false));
    /**turn to the required angle */
    addSequential(new SetOneEightyAngle(this.angle));
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetLiftHeight(height));
    /** collects the cargo */
    addSequential(new CollectCargo(this.COLLECTOR_POWER, this.HOLDER_POWER));
  }
}
