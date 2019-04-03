package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Robot;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.RumbleXbox;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

/** collects cargo from the floor */

public class CollectCargoFromFloor extends CommandGroup {
  double COLLECTOR_POWER = 0.8;
  double HOLDER_POWER = 0.6;

  public CollectCargoFromFloor() {
    /** starts by unfolding the cargo collecter */
    addSequential(new SetCargoFolderState(Value.kReverse));
    /** turns to the required angle */
    addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kCargoCollection));
    addSequential(new WaitCommand(0.8));
    /** set lift height to bottom in order to collect cargo */
    addSequential(new SetHeightIndex(LiftHeight.kCargoCollection));
    /** collects the cargo */
    addSequential(new CollectCargo(this.COLLECTOR_POWER, this.HOLDER_POWER));
    addParallel(new InstantCommand(() -> RobotStates.setIsCollected(true)));
    addParallel(new RumbleXbox(true));
    addSequential(new AfterCargoFloorPreparation());
  }
}
