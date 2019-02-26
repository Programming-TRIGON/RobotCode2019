package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.TrackVisionTarget;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.Vision.VisionPIDSource.VisionTarget;
import frc.robot.Commands.SetHatchCollectorState;

/**
 * a command group for collecting hatch from the floor
 */
public class CollectHatch extends CommandGroup {

    public CollectHatch() {
        addParallel(new SetLiftHeight(LiftHeight.kHatchCollection));
        addParallel(new SetOneEightyAngle(RobotConstants.OneEightyAngle.kBack));
        addSequential(new TrackVisionTarget(VisionTarget.kHatch, Robot.oi.driverXbox, 0.0,
                RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS, 0.0,
                RobotConstants.RobotPIDSettings.VISION_DISTANCE_SETTINGS));
        addSequential(new SetHatchCollectorState(Value.kForward));
        addSequential(new WaitCommand(1));
        addSequential(new SetHatchCollectorState(Value.kReverse));
        addSequential(new SetHatchLock(Value.kForward));
    }
}
