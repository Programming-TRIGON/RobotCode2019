/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class SetOneEightyAngle extends Command {
  private PIDController pidController;
  private double angle;
  private PIDSettings pidSettings;
  /**
   * 
   * @param angle the angle the SS seeks
   */
  public SetOneEightyAngle(double angle, PIDSettings pidSettings) {
    requires(Robot.oneEighty);
    this.angle = angle;
    this.pidSettings=pidSettings;
  }

  public SetOneEightyAngle(RobotConstants.OneEightyAngle angle) {
    this.angle = angle.key;
    requires(Robot.oneEighty);
  }

  @Override
  protected void initialize() {
    /*this.pidController = new PIDController(RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS.getKP(),
    RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS.getKI(),
    RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS.getKD(),
    Robot.oneEighty.getPotentiometer(),
    (output) -> Robot.oneEighty.setOneEighty(output));
    */
    
    this.pidController = new PIDController(pidSettings.getKP(),
    pidSettings.getKI(),
    pidSettings.getKD(),
    Robot.oneEighty.getPotentiometer(),
    (output) -> Robot.oneEighty.setOneEighty(output));

    //pidController.setAbsoluteTolerance(RobotConstants.RobotPIDSettings.ONE_EIGHTY_SET_ANGLE_SETTINGS.getTolerance());
    pidController.setAbsoluteTolerance(pidSettings.getTolerance());
    pidController.setOutputRange(-1, 1);
    pidController.setSetpoint(angle);
    pidController.enable();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    pidController.disable();
    pidController.close();    
  }
}
