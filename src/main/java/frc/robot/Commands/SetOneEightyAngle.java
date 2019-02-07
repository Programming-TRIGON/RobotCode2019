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

public class SetOneEightyAngle extends Command {
  private PIDController pidController;
  private double angle;
  private final double kP= 0.2;
  private final double kI= 0;
  private final double kD= 0;
  private final double PERIOD =0.05;
  private final double TOLERANCE =0.1;


  /**
   * 
   * @param angle the angle the SS seeks
   */
  public SetOneEightyAngle(double angle) {
    this.angle = angle;
    requires(Robot.oneEighty);
  }

  public SetOneEightyAngle(RobotConstants.OneEightyAngle angle) {
    this.angle = angle.key;
    requires(Robot.oneEighty);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // setting up the PID
    this.pidController = new PIDController(kP, kI, kD, Robot.oneEighty.getPotentiometer(),
        (output) -> Robot.oneEighty.setOneEighty(output), PERIOD);
    pidController.setAbsoluteTolerance(TOLERANCE);
    pidController.setOutputRange(-1, 1);
    pidController.setSetpoint(angle);
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
