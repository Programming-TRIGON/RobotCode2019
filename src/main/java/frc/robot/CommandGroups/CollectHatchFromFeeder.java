package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotStates;
import frc.robot.Commands.SetHatchLock;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.RobotConstants.LiftHeight;

/**
 * Sets the 180 and lift to feeder height and direction and opens the hatch collector
 */
public class CollectHatchFromFeeder extends CommandGroup {

    public CollectHatchFromFeeder() {
        addSequential(new SetHatchLock(Value.kReverse));
        OneEightyAngle angleToSet = RobotStates.isDriveInverted() ? OneEightyAngle.kBack : OneEightyAngle.kStraight; 
        addParallel(new SetOneEightyAngle(angleToSet));
        addSequential(new WaitCommand(0.3));
        addParallel(new SetLiftHeight(LiftHeight.kLiftBottomHatch));
    }
}
