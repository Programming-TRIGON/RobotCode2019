package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.RobotConstants.LiftHeight;

public class ToggleOneEightyAngle extends CommandGroup {
  /**
   * toggle the one eighty angle based on height and game piece
   */
  LiftHeight heightToSet;
  public ToggleOneEightyAngle() {
    addSequential(new SetCargoFolderState(Value.kReverse));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetHeightIndex(LiftHeight.kOneEightyCargoSafety));
    addSequential(new InstantCommand(() -> RobotStates.toggleOneEightyDesiredAngle()));
    addSequential(new WaitCommand(0.3));
    addSequential(new SetHeightIndex(() -> this.heightToSet));
  }

  @Override
  protected void initialize(){
    this.heightToSet = RobotStates.getLiftHeight();
  }
}
