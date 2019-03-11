package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Vision.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

public class ReflectorDrive extends ConditionalCommand {
  public ReflectorDrive() {
    super(new DriveArcadeWithVision(VisionTarget.kReflectorBackward), 
    new DriveArcadeWithVision(VisionTarget.kReflectorForward));
  }

  @Override
  protected boolean condition() {
    return RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kTopBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kTopBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kCargoShipBack; //we might want to make it by the real one angle 
  }
}
