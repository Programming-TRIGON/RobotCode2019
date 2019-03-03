package frc.robot.Vision;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Commands.DistancePIDSource;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/**
 * This command tracks the target in the constructor with both vision and
 * encoders: the driveTrain will drive a set amount of distance, by using the
 * supplier for calculating the error, and the given setpoint as the setpoint.
 * The driveTrain will also rotate using vision PID.
 */
public class TrackTargetByDistance extends Command {
  private final double SETPOINT = 0, TIMEOUT=5;
  private VisionPIDSource.VisionTarget target;
  private VisionPIDController rotationPIDController;
  private PIDController distancePIDController;
  private PIDSettings rotationSettings, distanceSettings;
  private double rotation, distance, distanceSetpoint, lastTimeNotOnTarget;

  public TrackTargetByDistance(VisionTarget target, double distance) {
    requires(Robot.driveTrain);
    setTimeout(TIMEOUT);
    this.target = target;
    this.distanceSetpoint = distance;
    this.rotationSettings = RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS;
    this.distanceSettings = RobotConstants.RobotPIDSettings.DRIVE_SETTINGS;
  }

  public TrackTargetByDistance(VisionTarget target, PIDSettings rotationSettings, PIDSettings distanceSetting,
      double distance) {
    requires(Robot.driveTrain);
    setTimeout(TIMEOUT);
    this.target = target;
    this.distanceSetpoint = distance;
    this.rotationSettings = rotationSettings;
    this.distanceSettings = distanceSetting;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());
    // pid source for rotation
    VisionPIDSource rotationPIDSource = new VisionPIDSource(this.target, VisionPIDSource.VisionDirectionType.x);
    // pid controller for the x axis
    rotationPIDController = new VisionPIDController(rotationSettings.getKP(), rotationSettings.getKI(),
        rotationSettings.getKD(), rotationPIDSource, (output) -> rotation = output);
    rotationPIDController.setAbsoluteTolerance(rotationSettings.getTolerance());
    rotationPIDController.setSetpoint(this.SETPOINT);
    rotationPIDController.setOutputRange(-1, 1);
    rotationPIDController.setInputRange(-1, 1);
    // pid controller for the y axis
    distancePIDController = new PIDController(distanceSettings.getKP(), distanceSettings.getKI(),
        distanceSettings.getKD(), new DistancePIDSource(), (output) -> distance = output);
    distancePIDController.setAbsoluteTolerance(distanceSettings.getTolerance());
    distancePIDController.setSetpoint(this.distanceSetpoint);
    distancePIDController.setOutputRange(-1, 1);
    distancePIDController.setInputRange(-1, 1);
    // enables the controllers
    rotationPIDController.enable();
    distancePIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if no direction is received, the driveTrain will move straight
    if (this.rotation != 9999)
      Robot.driveTrain.arcadeDrive(this.rotation, this.distance);
    else
      Robot.driveTrain.arcadeDrive(0, this.distance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // returns true if the robot reached his target
    if (this.distancePIDController.onTarget())
      lastTimeNotOnTarget = Timer.getFPGATimestamp();
    return Timer.getFPGATimestamp() - lastTimeNotOnTarget >= distanceSettings.getWaitTime()||isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // pid controllers are disabled and closed
    rotationPIDController.disable();
    rotationPIDController.close();
    distancePIDController.disable();
    distancePIDController.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
