/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.DrivingCommands.DriveArcadeWithPID;

/** Drives to a given target using vision */
public class DriveArcadeWithVision extends DriveArcadeWithPID {
  protected double lastTimeFound = 0;


  public DriveArcadeWithVision(TankDrivetrain drivetrain, VisionPIDSource.VisionTarget target, double movement,
      PIDSettings PIDSettings) {

    super(drivetrain, new VisionPIDSource(target, VisionPIDSource.VisionDirectionType.x), () -> {
      switch (target) {
      case kReflectorForward:
        return (Double)RobotConstants.Sensors.FORWARD_REFLECTOR_SETPOINT;
      case kReflectorBackward:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      default:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      }
    }, () -> movement, PIDSettings, 2, false);
  }
  public DriveArcadeWithVision(TankDrivetrain drivetrain, VisionPIDSource.VisionTarget target, Supplier<Double> movementSupplier,PIDSettings PIDSettings){
    super(drivetrain, new VisionPIDSource(target, VisionPIDSource.VisionDirectionType.x), () -> {
      switch (target) {
      case kReflectorForward:
        return (Double)RobotConstants.Sensors.FORWARD_REFLECTOR_SETPOINT;
      case kReflectorBackward:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      default:
        return (Double)RobotConstants.Sensors.BACKWARD_REFLECTOR_SETPOINT;
      }
    }, movementSupplier, PIDSettings, 2, false);

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
            drivetrain.arcadeDrive(movementSupplier.get(), -rotate);
            lastTimeFound = Timer.getFPGATimestamp();
          } else
            drivetrain.arcadeDrive(Robot.oi.driverXbox.getX(Hand.kLeft), Robot.oi.driverXbox.getY(Hand.kLeft));
        });

    rotationController.setAbsoluteTolerance(PIDSettings.getTolerance());
    rotationController.setSetpoint(setpointSupplier.get());
    rotationController.setOutputRange(-1, 1);
    rotationController.setInputRange(-inputRange / 2, inputRange / 2);
    rotationController.setContinuous(continuous);

    // Stop the tracking if the target is not found for a given amount of time
    this.isFinishedSupplier = () -> Timer.getFPGATimestamp() - lastTimeFound >= this.PIDSettings.getWaitTime()
        || isTimedOut();

    rotationController.enable();

  }
}
