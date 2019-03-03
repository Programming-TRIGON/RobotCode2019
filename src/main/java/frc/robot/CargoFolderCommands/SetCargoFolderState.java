package frc.robot.CargoFolderCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** folds the cargo collector to the floor */

public class SetCargoFolderState extends Command {
  private Value folderState;

  public SetCargoFolderState(Value folderState) {
    requires(Robot.cargoFolder);
    this.folderState = folderState;
  }

  @Override
  protected void initialize() {
    // Folds/unfolds based on the fold parameter.
    Robot.cargoFolder.setFold(this.folderState);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
