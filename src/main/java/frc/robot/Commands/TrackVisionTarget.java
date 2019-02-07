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
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Vision.VisionPIDController;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionDirectionType;

public class TrackVisionTarget extends Command {

  VisionPIDSource.VisionTarget target;
  VisionPIDController visionXPIDController;
  VisionPIDController visionYPIDController;
  XboxController xbox;
  String networkTableChange;

  final Supplier<Double> XkP = ConstantHandler.addConstantDouble("XkP", 0.4);
  final Supplier<Double> XkI = ConstantHandler.addConstantDouble("XkI", 0);
  final Supplier<Double> XkD = ConstantHandler.addConstantDouble("XkD", 0);
  final Supplier<Double> xTolerance = ConstantHandler.addConstantDouble("xTolerance", 0.1);
  final Supplier<Double> YkP = ConstantHandler.addConstantDouble("YkP", 0.4);
  final Supplier<Double> YkI = ConstantHandler.addConstantDouble("YkI", 0);
  final Supplier<Double> YkD = ConstantHandler.addConstantDouble("YkD", 0);
  final Supplier<Double> yTolerance = ConstantHandler.addConstantDouble("YTolerance", 0.1);
  final double X_SETPOINT = 0;
  final double Y_SETPOINT = 0;
  private double x;
  private double y;

  public TrackVisionTarget(VisionPIDSource.VisionTarget target, XboxController xbox) {
    this.target = target;
    this.xbox = xbox;

    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());
    // pid sources for y and x
    VisionPIDSource visionXPIDSource = new VisionPIDSource(this.target, VisionDirectionType.x);
    VisionPIDSource visionYPIDSource = new VisionPIDSource(this.target, VisionDirectionType.y);
    // pid controller for the x axis
    visionXPIDController = new VisionPIDController(this.XkP.get(), this.XkI.get(), this.XkD.get(), visionXPIDSource,
        (output) -> x = output);
    visionXPIDController.setAbsoluteTolerance(this.xTolerance.get());
    visionXPIDController.setSetpoint(this.X_SETPOINT);
    visionXPIDController.setOutputRange(-1, 1);
    visionXPIDController.setInputRange(-1, 1);
    visionXPIDController.enable();
    // pid controller for the y axis
    visionYPIDController = new VisionPIDController(this.YkP.get(), this.YkI.get(), this.YkD.get(), visionYPIDSource,
        (output) -> y = output);
    visionYPIDController.setAbsoluteTolerance(this.yTolerance.get());
    visionYPIDController.setSetpoint(this.Y_SETPOINT);
    visionYPIDController.setOutputRange(-1, 1);
    visionYPIDController.setInputRange(-1, 1);
    visionYPIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if no direction is received, the driveTrain is controlled by the joystick
    if (this.x == 9999 || this.y == 9999) {
      double y = -xbox.getY(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y);
    } else {
      Robot.driveTrain.arcadeDrive(this.x, -this.y);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    interrupted();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //pid controllers are disabled and closed
    visionXPIDController.disable();
    visionYPIDController.disable();
    visionXPIDController.close();
    visionYPIDController.close();
  }
}
