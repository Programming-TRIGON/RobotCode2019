package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.SetLiftHeight;
import frc.robot.OneEightyCommands.SetOneEightyAngle;

/** collects cargo from the floor */

public class CollectCargoFromFloor extends CommandGroup {
  double COLLECTOR_POWER = 0.75;
  double HOLDER_POWER = 0.6;

  public CollectCargoFromFloor() {
  }

  @Override
  protected void initialize(){
    /** starts by unfolding the cargo collecter */
    addSequential(new SetCargoFolderState(Value.kForward));
    /** turns to the required angle */
    addParallel(new SetOneEightyAngle(OneEightyAngle.kCargoCollection));
    addSequential(new WaitCommand(0.3));
    /** set lift height to bottom in order to collect cargo */
    addParallel(new SetLiftHeight(LiftHeight.kCargoCollection));
    /** collects the cargo */
    addSequential(new CollectCargo(this.COLLECTOR_POWER, this.HOLDER_POWER));
    /** prepare the lift and the 180 subsystems to score */
    addParallel(new SetLiftHeight(LiftHeight.kOneEightyCargoSafety));
    addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));
  }
}
