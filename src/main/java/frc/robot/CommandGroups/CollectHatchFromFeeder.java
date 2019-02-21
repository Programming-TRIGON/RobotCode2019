package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotStates;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
        addParallel(new SetOneEightyAngle(angleToSet));
        RobotStates.setHeightIndex(0);
        addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
        addSequential(new SetHatchLock(Value.kReverse));
    }
}
