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

  private final Supplier<Double> rotationKP = ConstantHandler.addConstantDouble("rotationKP", 0.4);
  private final Supplier<Double> rotationKI = ConstantHandler.addConstantDouble("rotationKI", 0);
  private final Supplier<Double> rotationKD = ConstantHandler.addConstantDouble("rotationKD", 0);
  private final Supplier<Double> rotationTolerance = ConstantHandler.addConstantDouble("rotationTolerance", 0.1);
  private final Supplier<Double> distanceKP = ConstantHandler.addConstantDouble("distanceKP", 0.4);
  private final Supplier<Double> distanceKI = ConstantHandler.addConstantDouble("distanceKI", 0);
  private final Supplier<Double> distanceKD = ConstantHandler.addConstantDouble("distanceKD", 0);
  private final Supplier<Double> distanceTolerance = ConstantHandler.addConstantDouble("distanceTolerance", 0.1);
  private final double ROTATION_SETPOINT = 0;
  private final double DISTANCE_SETPOINT = 0;
  private final double waitTime = 0.1;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target, XboxController xbox) {
    this.target = target;
    this.xbox = xbox;

    requires(Robot.DriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());
    // pid sources for distance and rotation
    VisionPIDSource rotationPIDSource = new VisionPIDSource(this.target, VisionDirectionType.x);
    VisionPIDSource distancePIDSource = new VisionPIDSource(this.target, VisionDirectionType.y);
    // pid controller for the rotation
    rotationPIDController = new VisionPIDController(this.rotationKP.get(), this.rotationKI.get(), this.rotationKD.get(),
        rotationPIDSource, (output) -> rotation = output);
    rotationPIDController.setAbsoluteTolerance(this.rotationTolerance.get());
    rotationPIDController.setSetpoint(this.ROTATION_SETPOINT);
    rotationPIDController.setOutputRange(-1, 1);
    rotationPIDController.setInputRange(-1, 1);
    // pid controller for the distance
    distancePIDController = new VisionPIDController(this.distanceKP.get(), this.distanceKI.get(), this.distanceKD.get(),
        distancePIDSource, (output) -> distance = output);
    distancePIDController.setAbsoluteTolerance(this.distanceTolerance.get());
    distancePIDController.setSetpoint(this.DISTANCE_SETPOINT);
    distancePIDController.setOutputRange(-1, 1);
    distancePIDController.setInputRange(-1, 1);
    //enables the controllers
    rotationPIDController.enable();
    distancePIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if no direction is received, the DriveTrain is controlled by the joystick
    if (this.rotation == 9999 || this.distance == 9999) {
      double y = -xbox.getY(Hand.kLeft);
      Robot.DriveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y);
    } else {
      Robot.DriveTrain.arcadeDrive(this.rotation, -this.distance);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // returns true if the robot reached his target
    if (this.distancePIDController.onTarget() && this.rotationPIDController.onTarget())
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