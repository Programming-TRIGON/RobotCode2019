package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotStates;


  /**
   * switches what the robot thinks its holding.
   */
public class SetHasCargo extends InstantCommand {
  boolean hasCargo;
  
  public SetHasCargo(boolean hasCargo) {
    this.hasCargo=hasCargo;
  }

  @Override
  protected void initialize() {
      RobotStates.setHasCargo(this.hasCargo);
      RobotStates.setHeightIndex(0);
  }
}
