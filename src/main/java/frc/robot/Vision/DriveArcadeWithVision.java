package frc.robot.Vision;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;
import frc.robot.DrivingCommands.DriveArcadeWithPID;
import frc.robot.Subsystems.DifferentialTankDrivetrain;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

/** Drives to a given target using vision */
public class DriveArcadeWithVision extends DriveArcadeWithPID {
  protected double lastTimeFound = 0;
  DifferentialTankDrivetrain drivetrain;

  public DriveArcadeWithVision(DifferentialTankDrivetrain drivetrain, VisionPIDSource.VisionTarget target, double movement,
      PIDSettings PIDSettings) {

    super(drivetrain, new VisionPIDSource(target, VisionPIDSource.VisionDirectionType.x), () -> {
      switch (target) {
      case kReflectorBackward:
        return RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      default:
        return RobotConstants.Sensors.FORWARD_REFLECTOR_SETPOINT;
      }
    }, () -> movement, PIDSettings, 2, false);
    this.drivetrain = drivetrain;
  }
  public DriveArcadeWithVision(VisionPIDSource.VisionTarget target) {
      this(target, RobotConstants.RobotPIDSettings.VISION_TURN_SETTINGS);
  }
  // public DriveArcadeWithVision(TankDrivetrain drivetrain, VisionPIDSource.VisionTarget target, Supplier<Double> movementSupplier,PIDSettings PIDSettings){
  //   super(drivetrain, new VisionPIDSource(target, VisionPIDSource.VisionDirectionType.x), () -> {
  //     switch (target) {
  //     case kReflectorForward:
  //       return (Double)RobotConstants.Sensors.FORWARD_REFLECTOR_SETPOINT;
  //     case kReflectorBackward:
  //       return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
  //     default:
  //       return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
  //     }
  //   }, movementSupplier, PIDSettings, 2, false);

  //}

  public DriveArcadeWithVision(VisionTarget target, com.spikes2212.utils.PIDSettings pidSettings) {
    super(Robot.driveTrain, new VisionPIDSource(target, VisionPIDSource.VisionDirectionType.x), () -> {
      switch (target) {
      case kReflectorForward:
        return (Double)RobotConstants.Sensors.FORWARD_REFLECTOR_SETPOINT;
      case kReflectorBackward:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      default:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      }
    }, ()->Robot.oi.driverXbox.getY(Hand.kLeft), pidSettings, 2, false);
    this.drivetrain = Robot.driveTrain;
}
@Override
  protected void initialize() {
    // Set target in the Network Table
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(((VisionPIDSource) PIDSource).getTarget().toString());

    this.rotationController = new VisionPIDController(PIDSettings.getKP(), PIDSettings.getKI(), PIDSettings.getKD(),
        (VisionPIDSource) PIDSource, (rotate) -> {
          if (rotate != 9999) {
            drivetrain.arcadeDrive((RobotStates.isDriveInverted() ? 1:-1)*movementSupplier.get(), rotate, 0.1);
            lastTimeFound = Timer.getFPGATimestamp();
          } else
            drivetrain.arcadeDrive((RobotStates.isDriveInverted() ? 1:-1)*Robot.oi.driverXbox.getY(Hand.kLeft), 0);
        });

    rotationController.setAbsoluteTolerance(PIDSettings.getTolerance());
    rotationController.setSetpoint(setpointSupplier.get());
    rotationController.setOutputRange(-1, 1);
    rotationController.setInputRange(-inputRange / 2, inputRange / 2);
    rotationController.setContinuous(continuous);

    // Stop the tracking if the target is not found for a given amount of time
    // this.isFinishedSupplier = () -> Timer.getFPGATimestamp() - lastTimeFound >= this.PIDSettings.getWaitTime()
    //     || isTimedOut();

    rotationController.enable();
  }
}
