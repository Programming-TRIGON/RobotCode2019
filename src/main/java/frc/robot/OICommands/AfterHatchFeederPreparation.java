package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.Commands.RumbleXbox;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.OneEightyCommands.ToggleOneEightyAngle;

public class AfterHatchFeederPreparation extends CommandGroup {
  /**
   * preparing the robot to score hatchs after taking them from the feeder
   */
  public AfterHatchFeederPreparation() {
    addSequential(new SetHatchLock(Value.kForward));
    addParallel(new InstantCommand(() -> RobotStates.setIsCollected(true)));
    addParallel(new RumbleXbox(false));
    //addSequential(new WaitCommand(0.85));
    //addSequential(new ToggleOneEightyAngle());
  }
}

