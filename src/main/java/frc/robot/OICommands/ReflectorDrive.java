package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.GenericIfCommand;
import frc.robot.DrivingCommands.ReflectorDriveChooser;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class ReflectorDrive extends CommandGroup {
  /**
   * reflector drive with smart height changing 
   */
  LiftHeight heightToSet;
  boolean cargoFoldDown;
  public ReflectorDrive() {
    addSequential(new GenericIfCommand(new SetCargoFolderState(Value.kReverse), () -> this.cargoFoldDown));
    addSequential(new GenericIfCommand(new WaitCommand(0.5), () -> this.cargoFoldDown));
    addSequential(new SetHeightIndex(() -> this.heightToSet));
    addSequential(new ReflectorDriveChooser());
  }

  @Override
  protected void initialize(){
    if(RobotStates.getLiftHeight().equals(LiftHeight.kCargoCollection)
      || RobotStates.getLiftHeight().equals(LiftHeight.kLiftBottomHatch)){
      RobotStates.setLastHeight(RobotStates.getLiftHeight());
      //this.heightToSet = LiftHeight.;
      cargoFoldDown = true;
    } else {
      this.heightToSet = RobotStates.getLiftHeight();
      RobotStates.setLastHeight(this.heightToSet);
      cargoFoldDown = false;
    }
  }
}
