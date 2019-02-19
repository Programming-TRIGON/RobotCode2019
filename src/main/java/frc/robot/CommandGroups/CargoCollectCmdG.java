package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetLiftHeight;

/** collects cargo from the floor */

public class CargoCollectCmdG extends CommandGroup {
  double COLLECTOR_POWER = 0.75;
  double HOLDER_POWER = 0.6;

  // TODO: real values.
  public CargoCollectCmdG() {
    /** starts by unfolding the cargo collecter */
    addSequential(new SetCargoFolderState(Value.kForward));
    /** turns to the required angle */
    addSequential(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kStraight));
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetLiftHeight(RobotConstants.LiftHeight.kCargoCollection));
    /** collects the cargo */
    addSequential(new CollectCargo(this.COLLECTOR_POWER, this.HOLDER_POWER));
  }
}
