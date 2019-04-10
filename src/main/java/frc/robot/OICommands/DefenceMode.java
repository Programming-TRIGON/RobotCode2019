package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.GenericIfCommand;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
 /**
   * retracts all systems for deffence 
   */
public class DefenceMode extends CommandGroup {
 
  LiftHeight currentHeight;
  public DefenceMode() {
    addParallel(new GenericIfCommand(new SetCargoFolderState(Value.kReverse), () -> !currentHeight.equals(LiftHeight.kCargoCollection))); //open so lift can go down
    addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kCargoCollection));    
    addSequential(new Push());
    addSequential(new GenericIfCommand(new WaitCommand(0.5), () -> !currentHeight.equals(LiftHeight.kCargoCollection)));
    addSequential(new SetHeightIndex(LiftHeight.kCargoCollection));
    addSequential(new GenericIfCommand(new WaitCommand(2.5), () -> !currentHeight.equals(LiftHeight.kCargoCollection)));
    addParallel(new SetCargoFolderState(Value.kForward));
    addParallel(new SetHatchCollectorState(Value.kReverse));
    addSequential(new SetHatchLock(Value.kForward));
  }

  @Override
  protected void initialize(){
    currentHeight = RobotStates.getLiftHeight(); 
  }
}
