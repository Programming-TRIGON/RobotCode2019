/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class SetAngle extends Command {
  PIDController pidController;
  double angle;

  /**
   * 
   * @param angle the angle the SS seeks
   */
  public SetAngle(double angle) {
    this.angle = angle;
    requires(Robot.oneEighty);
  }

  public SetAngle(RobotConstants.RobotDimensions.Angle angle) {
    this.angle = angle.key;
    requires(Robot.oneEighty);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // setting up the PID
    this.pidController = new PIDController(0.2, 0, 0, Robot.oneEighty.getPotentiometer(),
        (output) -> Robot.oneEighty.setOneEighty(output), 0.05);
    pidController.setAbsoluteTolerance(0.01);
    pidController.setSetpoint(angle);
    pidController.setOutputRange(-1, 1);
    pidController.enable();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // this function shouldn't be called
    pidController.disable();
    pidController.close();
    System.out.println("PID on the oneEigthy has stopped!");
    
  }
}
