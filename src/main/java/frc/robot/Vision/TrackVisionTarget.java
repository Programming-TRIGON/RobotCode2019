
package frc.robot.Commands;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.Vision.VisionPIDController;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionDirectionType;

/**
 * This command makes the follow the target in the constructor by using vision
 * PID.
 */
public class TrackVisionTarget extends Command {

  private VisionPIDSource.VisionTarget target;
  private VisionPIDController rotationPIDController;
  private VisionPIDController distancePIDController;
  private XboxController xbox;
  private double rotation;
  private double distance;
  private double lastTimeNotOnTarget;
  private PIDSettings rotationSettings;
  private PIDSettings distanceSettings;
  private final double ROTATION_SETPOINT;
  private final double DISTANCE_SETPOINT;
/**
 * 
 * @param target target to follow
 * @param xbox an xbox controller to control the drivetrain
 * @param rotationSetpoint setpoint for the rotation PID
 * @param rotationSettings PIDSettings for rotation PID. WaitTime is used from this settings.
 * @param distanceSetpoint setpoint for the rotation PID
 * @param distanceSettings PIDSettings for rotation PID. WaitTime isn't used.
 */
  public TrackVisionTarget(VisionPIDSource.VisionTarget target, XboxController xbox, double rotationSetpoint,
  PIDSettings rotationSettings, double distanceSetpoint,PIDSettings distanceSettings) {
    this.target = target;
    this.xbox = xbox;
    DISTANCE_SETPOINT = distanceSetpoint;
    ROTATION_SETPOINT = rotationSetpoint;
    this.rotationSettings = rotationSettings;
    this.distanceSettings = distanceSettings;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    // NetworkTableEntry target = imageProcessingTable.getEntry("target");
    // target.setString(this.target.toString());
    // pid sources for distance and rotation
    VisionPIDSource rotationPIDSource = new VisionPIDSource(this.target, VisionDirectionType.x);
    VisionPIDSource distancePIDSource = new VisionPIDSource(this.target, VisionDirectionType.y);
    // pid controller for the rotation
    rotationPIDController = new VisionPIDController(rotationSettings.getKP(), rotationSettings.getKI(), rotationSettings.getKD(),
        rotationPIDSource, (output) -> rotation = output);
    rotationPIDController.setAbsoluteTolerance(rotationSettings.getTolerance());
    rotationPIDController.setSetpoint(this.ROTATION_SETPOINT);
    rotationPIDController.setOutputRange(-1, 1);
    rotationPIDController.setInputRange(-1, 1);
    // pid controller for the distance
    distancePIDController = new VisionPIDController(distanceSettings.getKP(), distanceSettings.getKI(), distanceSettings.getKD(),
        distancePIDSource, (output) -> distance = output);
    distancePIDController.setAbsoluteTolerance(distanceSettings.getTolerance());
    distancePIDController.setSetpoint(this.DISTANCE_SETPOINT);
    distancePIDController.setOutputRange(-1, 1);
    distancePIDController.setInputRange(-1, 1);
    // enables the controllers
    rotationPIDController.enable();
    distancePIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if no direction is received, the driveTrain is controlled by the joystick
    if (this.rotation == 9999 || this.distance == 9999) {
      double y = -xbox.getY(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y);
    } else {
      Robot.driveTrain.arcadeDrive(this.rotation, -this.distance);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // returns true if the robot reached his target
    if (this.distancePIDController.onTarget() && this.rotationPIDController.onTarget())
      lastTimeNotOnTarget = Timer.getFPGATimestamp();
    return Timer.getFPGATimestamp() - lastTimeNotOnTarget >= rotationSettings.getWaitTime();
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
