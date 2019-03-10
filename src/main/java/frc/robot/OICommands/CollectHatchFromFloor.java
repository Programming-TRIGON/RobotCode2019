package frc.robot.OICommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.OneEightyDefaultCommand;

/**
 * a command group for collecting hatch from the floor
 */
public class CollectHatchFromFloor extends CommandGroup {

    public CollectHatchFromFloor() {
      addSequential(new SetHatchCollectorState(Value.kReverse));
      addSequential(new WaitCommand(0.7));
      addParallel(new OneEightyDefaultCommand(RobotConstants.OneEightyAngle.kStraight));    
      addSequential(new WaitCommand(0.7));    
      addParallel(new SetHeightIndex(LiftHeight.kHatchCollection));
      // addSequential(new TrackVisionTarget(VisionTarget.kHatch, Robot.oi.driverXbox,
      //   RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS,
      //   RobotConstants.RobotPIDSettings.VISION_DISTANCE_SETTINGS));
      // addSequential(new SetHatchCollectorState(Value.kReverse));
      // addSequential(new WaitCommand(0.2));
      // addSequential(new SetHatchLock(Value.kForward));
    }
}
