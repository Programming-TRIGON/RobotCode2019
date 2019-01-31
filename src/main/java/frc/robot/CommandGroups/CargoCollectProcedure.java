package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetAngle;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetLiftHeight;

public class CargoCollectProcedure extends CommandGroup {
  double angle;
  double collectPower;
  double holderPower;

  public CargoCollectProcedure(double angle, double collectPower, double holderPower) {
    this.angle = angle;
    this.collectPower = collectPower;
    this.holderPower = holderPower;
    /** starts by unfolding the crago collecter */
    addSequential(new SetCargoFolderState(false));
    /**
     * if the required angle isn't what we want, we lift up the lift (because
     * mechanika) and and turn to the required angle
     */
    if (angle - 1 < Robot.oneEighty.getAngle() && Robot.oneEighty.getAngle() < angle + 1) {
      addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.MINUMAN_ONE_EIGHTY_LIFT_HEIGHT));
      addSequential(new SetAngle(angle));
    }
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetLiftHeight(RobotConstants.RobotDimensions.BOTTOM_LIFT_HEIGHT));
    /** collects the cargo */
    addSequential(new CollectCargo(collectPower, holderPower));
  }
}
