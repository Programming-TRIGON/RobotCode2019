package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;

public class AfterCargoFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting cargo from the floor
   */
  public AfterCargoFloorPreparation() {
    addSequential(new CollectCargo(0,0,false), 0.05);
    /** prepare the lift and the 180 subsystems to score */
    addSequential(new SetHeightIndex(LiftHeight.kOneEightyCargoSafety));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));
    addSequential(new SetCargoFolderState(Value.kForward));
  }
}
