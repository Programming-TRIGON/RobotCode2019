/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;
import java.util.function.Supplier;
import com.spikes2212.dashboard.ConstantHandler;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTable;
import frc.robot.Robot;
import frc.robot.Vision.*;

public class TrackReflector extends Command {
  VisionPIDSource.VisionTarget target;
  VisionPIDController visionXPIDController;
  XboxController xbox;
  String networkTableChange;
  final Supplier<Double> XkP = ConstantHandler.addConstantDouble("XkP", 0.4);
  final Supplier<Double> XkI = ConstantHandler.addConstantDouble("XkI", 0);
  final Supplier<Double> XkD = ConstantHandler.addConstantDouble("XkD", 0);
  final Supplier<Double> reflectorTolerance = ConstantHandler.addConstantDouble("refrelctorTolerance", 0.3);
  final int SETPOINT = 0;
  private double x;

  public TrackReflector(XboxController xbox) {
    this.target = VisionPIDSource.VisionTarget.kReflector;
    this.xbox = xbox;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    edu.wpi.first.networktables.NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(this.target.toString());
    // pid source for x
    VisionPIDSource visionXPIDSource = new VisionPIDSource(this.target, VisionPIDSource.VisionDirectionType.x);
    // pid controller for the x axis
    visionXPIDController = new VisionPIDController(this.XkP.get(), this.XkI.get(), this.XkD.get(), visionXPIDSource,
        (output) -> x = output);
    visionXPIDController.setAbsoluteTolerance(this.reflectorTolerance.get());
    visionXPIDController.setSetpoint(this.SETPOINT);
    visionXPIDController.setOutputRange(-1, 1);
    visionXPIDController.setInputRange(-1, 1);
    visionXPIDController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if no direction is received, the driveTrain is controlled by the joystick
    if (x == 9999) {
      double y = -xbox.getY(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(xbox.getX(Hand.kLeft), y, Math.abs(y) <= 0.50);
    } else {
      double y = -xbox.getY(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(x, y, Math.abs(y) <= 0.50);
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
    visionXPIDController.disable();
    visionXPIDController.close();
  }
}
