package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotStates;

/**
 * Changing the drive direction 
 */
public class SetDriveInverted extends InstantCommand {
  boolean state;

  public SetDriveInverted(boolean state) {
    this.state = state;
  }

  @Override
  protected void initialize() {
    if(this.state)
      RobotStates.toggleDriveInverted();  
    //RobotStates.setDriveInverted(state);
  }

}
