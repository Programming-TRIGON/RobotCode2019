package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.RobotStates;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.HatchHolderCommands.EjectHatch;

public class Push extends ConditionalCommand {
  /**
   * Pushes the right piece 
   */
  public Push() {
    super(new PushCargo(), new EjectHatch());
  }

  @Override
  protected boolean condition() {
    return RobotStates.isHasCargo();
  }
}
