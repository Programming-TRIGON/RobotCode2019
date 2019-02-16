/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import frc.robot.Commands.DriveArcadeWithPID;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Vision.VisionPIDController;
import frc.robot.Vision.VisionPIDSource;

public class DriveArcadeWithVision extends DriveArcadeWithPID {
  protected double lastTimeNotOnTarget = 1;

  public DriveArcadeWithVision(TankDrivetrain drivetrain, VisionPIDSource visionPIDSource,
      Supplier<Double> setpointSupplier, Supplier<Double> movementSupplier, PIDSettings PIDSettings, double inputRange,
      boolean continuous) {
    super(drivetrain, visionPIDSource, setpointSupplier, movementSupplier, () -> false, PIDSettings, inputRange,
        continuous);
  }

  public DriveArcadeWithVision(TankDrivetrain drivetrain, VisionPIDSource visionPIDSource, double setpoint,
      double movement, PIDSettings PIDSettings, double inputRange, boolean continuous) {

    super(drivetrain, visionPIDSource, () -> setpoint, () -> movement, PIDSettings, inputRange, continuous);
  }

  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(((VisionPIDSource) PIDSource).getTarget().toString());
    this.rotationController = new VisionPIDController(PIDSettings.getKP(), PIDSettings.getKI(), PIDSettings.getKD(),
        (VisionPIDSource) PIDSource, (rotate) -> {
          if (rotate != 9999)
            drivetrain.arcadeDrive(movementSupplier.get(), -rotate);
        });
    rotationController.setAbsoluteTolerance(PIDSettings.getTolerance());
    rotationController.setSetpoint(setpointSupplier.get());
    rotationController.setOutputRange(-1, 1);
    rotationController.setInputRange(-inputRange / 2, inputRange / 2);
    rotationController.setContinuous(continuous);
    this.isFinishedSupplier = () -> {
      if (this.rotationController.onTarget())
        lastTimeNotOnTarget = Timer.getFPGATimestamp();
      return Timer.getFPGATimestamp() - lastTimeNotOnTarget >= this.PIDSettings.getWaitTime();
    };
    rotationController.enable();

  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
