/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotComponents;

public class PidMovmentSupplier extends Command {
  double movmentPidOutput;
  Supplier<Double> movmentSupplier = () -> movmentPidOutput;
  PIDController movmentPidController;
  double distance;
  final double kP = 0.0025;
  final double kI = 0;
  final double kD = 0.004;

  public PidMovmentSupplier(double distance) {
    this.distance = distance;
  }

  @Override
  protected void initialize() {
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    this.movmentPidController = new PIDController(kP, kI, kD, new PIDSource() {

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }

      @Override
      public double pidGet() {
        return (RobotComponents.DriveTrain.LEFT_ENCODER.getDistance()
            + RobotComponents.DriveTrain.RIGHT_ENCODER.getDistance()) / 2;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    }, new PIDOutput() {

      @Override
      public void pidWrite(double output) {
        movmentPidOutput = output;
      }
    });
    movmentPidController.setAbsoluteTolerance(5);
    movmentPidController.setOutputRange(-1, 1);
    movmentPidController.setSetpoint(this.distance);
    movmentPidController.enable();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return this.movmentPidController.onTarget();
  }

  @Override
  protected void end() {
    this.movmentPidController.disable();
    this.movmentPidController.close();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
