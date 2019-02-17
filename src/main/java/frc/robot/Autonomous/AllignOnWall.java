/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotComponents;

public class AllignOnWall extends DriveArcade {
  /**
   * Add your docs here.
   */
  double CURRENT_THRESHOLD = 0;

  public AllignOnWall() {
    super(Robot.driveTrain, -0.5, 0);
  }

  @Override
  protected boolean isFinished(){
    SmartDashboard.putNumber("Drivetrain current", getAverageCurrent());
    return getAverageCurrent() >= CURRENT_THRESHOLD;
  }

  private double getAverageCurrent(){
    return (RobotComponents.DriveTrain.FRONT_LEFT_M.getOutputCurrent() +
    RobotComponents.DriveTrain.FRONT_RIGHT_M.getOutputCurrent() +
    RobotComponents.DriveTrain.REAR_LEFT_M.getOutputCurrent() +
    RobotComponents.DriveTrain.REAR_RIGHT_M.getOutputCurrent())/4;
  }
}
