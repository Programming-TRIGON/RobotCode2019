package frc.robot.OneEightyCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;

/**
 * sets the robot states desired angle.
 */
public class SetOneEightyDesireAngle extends InstantCommand {
  Supplier<OneEightyAngle> angleSupplier;

  public SetOneEightyDesireAngle(OneEightyAngle desiredAngle) {
    this.angleSupplier = () -> desiredAngle;
  }

  public SetOneEightyDesireAngle(Supplier<OneEightyAngle> desiredAngle) {
    this.angleSupplier = desiredAngle;
  }

  @Override
  protected void initialize() {
      RobotStates.setDesireOneEightyAngle(this.angleSupplier.get());
}
