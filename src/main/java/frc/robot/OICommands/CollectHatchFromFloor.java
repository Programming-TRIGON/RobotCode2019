package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetLiftHeight;
import frc.robot.OneEightyCommands.SetOneEightyAngle;
import frc.robot.Vision.TrackVisionTarget;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/**
 * a command group for collecting hatch from the floor
 */
public class CollectHatchFromFloor extends CommandGroup {

    public CollectHatchFromFloor() {
    }

    @Override
    protected void initialize(){
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
