package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetLiftHeight;

public class CargoCollectProcedure extends CommandGroup {
  double angle = RobotConstants.RobotDimensions.ONE_EIGHTY_FORWARD;
  double collectPower = 0.5;
  double holderPower = 0.7;
//TODO: real values.
  public CargoCollectProcedure() {
    /** starts by unfolding the crago collecter */
    addSequential(new SetCargoFolderState(false));
    /**turn to the required angle */
    addSequential(new SetAngle(this.angle));
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.BOTTOM_LIFT_HEIGHT));
    /** collects the cargo */
    addSequential(new CollectCargo(this.collectPower, this.holderPower));
  }
}
