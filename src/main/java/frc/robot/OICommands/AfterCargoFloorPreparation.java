package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoCollectorCommands.KeepCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.GenericIfCommand;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

public class AfterCargoFloorPreparation extends CommandGroup {
  /**
   * prepare the robot after collecting cargo from the floor
   */
  public AfterCargoFloorPreparation() {
    addSequential(new GenericIfCommand(new CollectCargo(0,0,false), 
    new KeepCargo(), () -> !RobotStates.isHasCargo()));
    
    // --------we might want to uncomment this--------
    
    /** prepare the lift and the 180 subsystems to score */
    // addSequential(new GenericIfCommand(new SetHeightIndex(LiftHeight.kOneEightyCargoSafety), 
    // RobotStates::isHasCargo)); 
    // addSequential(new WaitCommand(0.3));
    // addSequential(new GenericIfCommand(new SetOneEightyDesireAngle(OneEightyAngle.kStraight),
    // RobotStates::isHasCargo));
    // addSequential(new SetCargoFolderState(Value.kForward)); //we might want to make it if command
  }
}
