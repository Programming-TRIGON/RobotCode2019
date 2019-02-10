/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;
import com.spikes2212.dashboard.ConstantHandler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Vision.VisionPIDController;
import frc.robot.Vision.VisionPIDSource;

/**
 * This command tracks the target in the constructor with both vision and
 * encoders: the driveTrain will drive a set amount of distance, by using the
 * supplier for calculating the error, and the given setpoint as the setpoint.
 * The driveTrain will also rotate using vision PID.
 */
public class TrackTargetByDistance extends Command {
  private double lastTimeNotOnTarget;
  private final double waitTime = 0.1;
  private VisionPIDSource.VisionTarget target;
  private VisionPIDController rotationPIDController;
  private PIDController distancePIDController;
  private double distanceSetpoint;
  private Supplier<Double> distanceSupplier;
  private double rotation;
  private double distance;
  private final Supplier<Double> rotationKP = ConstantHandler.addConstantDouble("rotationKP", 0.4);
  private final Supplier<Double> rotationKI = ConstantHandler.addConstantDouble("rotationKI", 0);
  private final Supplier<Double> rotationKD = ConstantHandler.addConstantDouble("rotationKD", 0);
  private final Supplier<Double> rotationTolerance = ConstantHandler.addConstantDouble("rotationTolerance", 0.1);
  private final Supplier<Double> distanceTolerance = ConstantHandler.addConstantDouble("distanceTolerance", 0.3);
  private final Supplier<Double> distanceKP = ConstantHandler.addConstantDouble("distanceKP", 0.4);
  private final Supplier<Double> distanceKI = ConstantHandler.addConstantDouble("distanceKI", 0);
  private final Supplier<Double> distanceKD = ConstantHandler.addConstantDouble("distanceKD", 0);
  private final double SETPOINT = 0;

  public TrackTargetByDistance(VisionPIDSource.VisionTarget target,
      Supplier<Double> distanceSupplier, double setpoint) {
    this.target = target;
    this.distanceSupplier = distanceSupplier;
    this.distanceSetpoint = setpoint;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());
    // pid source for rotation
    VisionPIDSource rotationPIDSource = new VisionPIDSource(this.target, VisionPIDSource.VisionDirectionType.x);
    // pid source for distance
    PIDSource distancePIDSource = new PIDSource() {

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }

      @Override
      public double pidGet() {
        return distanceSupplier.get();
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    };
    // pid controller for the x axis
    rotationPIDController = new VisionPIDController(this.rotationKP.get(), this.rotationKI.get(), this.rotationKD.get(),
        rotationPIDSource, (output) -> rotation = output);
    rotationPIDController.setAbsoluteTolerance(this.rotationTolerance.get());
    rotationPIDController.setSetpoint(this.SETPOINT);
    rotationPIDController.setOutputRange(-1, 1);
    rotationPIDController.setInputRange(-1, 1);
    // pid controller for the y axis
    distancePIDController = new PIDController(this.distanceKP.get(), this.distanceKI.get(), this.distanceKD.get(),
        distancePIDSource, (output) -> distance = output);
    distancePIDController.setAbsoluteTolerance(this.distanceTolerance.get());
    distancePIDController.setSetpoint(this.distanceSetpoint);
    distancePIDController.setOutputRange(-1, 1);
    distancePIDController.setInputRange(-1, 1);
    //enables the controllers
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
    return Timer.getFPGATimestamp() - lastTimeNotOnTarget >= this.waitTime;
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
