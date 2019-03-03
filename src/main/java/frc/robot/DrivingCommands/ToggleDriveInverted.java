package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotStates;

/**
  * Toggle Inverted Drive 
  */
public class ToggleDriveInverted extends InstantCommand {
  
  public ToggleDriveInverted() {
  }

  @Override
  protected void initialize() {
    RobotStates.toggleDriveInverted();
  }
}
