/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DrivingCommands;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcadeWithPID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;

public class GyroDriveWithJoystick extends CommandGroup {
  /**
   * Add your docs here.
   */
  double setpoint;
  public GyroDriveWithJoystick() {
    addSequential(new DriveArcadeWithPID_edited((TankDrivetrain)Robot.driveTrain, 
    (PIDSource)RobotComponents.DriveTrain.GYRO, 
    () -> setpoint, 
    () -> (RobotStates.isDriveInverted()?1:-1)*Robot.oi.driverXbox.getY(Hand.kLeft), 
    RobotConstants.RobotPIDSettings.GYRO_DRIVE_SETTINGS, 360, true));
  }

  @Override
  protected void initialize() {
    setpoint = RobotComponents.DriveTrain.GYRO.getAngle();
  }
}
