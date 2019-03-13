package frc.robot.DrivingCommands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Vision.DriveArcadeWithVision;
import frc.robot.Vision.VisionPIDSource.VisionTarget;

public class ReflectorDriveChooser extends ConditionalCommand {
  public ReflectorDriveChooser() {
    super(new DriveArcadeWithVision(VisionTarget.kReflectorForward), 
    new DriveArcadeWithVision(VisionTarget.kReflectorBackward)); //they are inverted! 
  }

  @Override
  protected boolean condition() {
    return (RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kTopBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kBack ||
    RobotStates.getDesireOneEightyAngle() == OneEightyAngle.kCargoShipBack) && RobotStates.isHasCargo(); //we might want to make it by the real one angle 
  }
}
