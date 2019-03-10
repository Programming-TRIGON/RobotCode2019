package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

/**
 * a command group for collecting hatch from the floor
 */
public class CollectHatchFromFloor extends CommandGroup {

    public CollectHatchFromFloor() {
      addParallel(new SetCargoFolderState(Value.kReverse));
      addSequential(new SetHatchCollectorState(Value.kForward));
      addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));    
      addSequential(new WaitCommand(0.3));    
      addParallel(new SetHeightIndex(LiftHeight.kHatchCollection));
    }
}
