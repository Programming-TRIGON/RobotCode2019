package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.DrivingCommands.DriveWithGyro;
import frc.robot.LiftCommands.ReachLiftHeight;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.RobotPIDSettings;
import frc.robot.CargoFolderCommands.SetCargoFolderState;

public class testAuto extends CommandGroup {
  /**
   * insert distances for driving and turning for testing the autonomous
   */
  public testAuto() {
    addSequential(new DriveWithGyro(300));
    addSequential(new OrientWithPID(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, 90, RobotPIDSettings.TURN_SETTINGS, 360, true));
    addSequential(new SetCargoFolderState(Value.kForward));
    addSequential(new ReachLiftHeight(LiftHeight.kCargoShip));
    //addSequential(command);
  }
}

