package frc.robot.OneEightyCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;

/**
 * sets the robot states desired angle.
 */
public class SetOneEightyDesireAngle extends InstantCommand {
  OneEightyAngle desiredAngle;
  public SetOneEightyDesireAngle(OneEightyAngle desiredAngle) {
    super();
    this.desiredAngle=desiredAngle;
  }

  @Override
  protected void initialize() {
    RobotStates.setDesireOneEightyAngle(this.desiredAngle);      
  }

}
