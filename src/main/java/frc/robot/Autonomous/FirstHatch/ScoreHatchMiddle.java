package frc.robot.Autonomous.FirstHatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.DrivingCommands.DriveWithGyro;
import frc.robot.DrivingCommands.Turn;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.OICommands.CollectHatchFromFeeder;
import frc.robot.RobotConstants.RobotDimensions;
import frc.robot.RobotConstants.RobotPIDSettings;


/**
 * scores the first hatch in the middle rocket then goes to thefeeder for
 * getting the second hatch.
 */
public class ScoreHatchMiddle extends CommandGroup {
    final double FIRST_DISTANCE = 4.25 - RobotDimensions.ROBOT_LENGTH;
    final double TURN_45 = 45;
    final double SECOND_DISTANCE = 1.6907;
    final double TARGET_TRACK_TIME = 5;
    final double DRIVE_TO_FEEDER = 6.48;
    final double RAMP_TO_FEEDER = 2.32 + 1.2;
    final double RAMP_TO_HATCH = 5.45;
    final double TURN_TO_FEEDER = Math.atan(180 - (RAMP_TO_FEEDER / RAMP_TO_HATCH));

    public ScoreHatchMiddle(boolean isLeft) {

        // drive towards the cargo ship
        addSequential(new DriveWithGyro(FIRST_DISTANCE));

        // turn to be in line with the rocket
        addSequential(new Turn(TURN_45 * (isLeft ? 1 : -1)));

        // diagonal drive in the cargo ship's general direction
        addSequential(new DriveWithGyro(SECOND_DISTANCE));

        // Face the cargo ship
        addSequential(new Turn(-TURN_45 * (isLeft ? 1 : -1)));

        // Use vision to deliver the hatch
        /*addSequential(
                new DriveArcadeWithVision(Robot.driveTrain, VisionPIDSource.VisionTarget.kReflector, () -> 0.0,

                        Robot.oi::getYLeft, RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS),
                TARGET_TRACK_TIME);
        */
        addSequential(new EjectHatch());

        // Turn to be able to drive to the feeder
        addSequential(new Turn(TURN_TO_FEEDER * (isLeft ? 1 : -1)));

        // Drive to feeder
        addSequential(new DriveWithGyro(DRIVE_TO_FEEDER));

        // Turn to face the feeder
        addSequential(new Turn(TURN_TO_FEEDER - 180 * (isLeft ? 1 : -1)));

        // Collects the hatch from the feeder
        addSequential(new CollectHatchFromFeeder());//add after hatch feeder
    }
}
