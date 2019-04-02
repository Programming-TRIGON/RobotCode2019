package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        addSequential(new SetHatchLock(Value.kReverse));
        addSequential(new SetCargoFolderState(Value.kReverse));
        addSequential(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));
        addSequential(new WaitCommand(0.5));
        addSequential(new SetHeightIndex(LiftHeight.kHatchCollection));
    }
}
