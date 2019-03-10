/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Vision.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

public class ReflectorDrive extends ConditionalCommand {
  public ReflectorDrive() {
    super(new DriveArcadeWithVision(VisionTarget.kReflectorBackward), new DriveArcadeWithVision(VisionTarget.kReflectorForward));
  }

  @Override
  protected boolean condition() {
    return RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kTopBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kTopBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kCargoShipBack;
  }
}
