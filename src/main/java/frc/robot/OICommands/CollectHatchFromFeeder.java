package frc.robot.OICommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        addParallel(new SetOneEightyDesireAngle(OneEightyAngle.kStraight));
        addSequential(new SetHeightIndex(LiftHeight.kLiftBottomHatch));
        
        /*addSequential(new SetHatchLock(Value.kReverse));
        addSequential(new SetCargoFolderState(Value.kReverse));
        addParallel(new SetOneEightyAngle(OneEightyAngle.kStraight));
        addSequential(new WaitCommand(0.5));
        addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));*/
    }
}
