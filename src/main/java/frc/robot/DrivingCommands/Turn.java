package frc.robot.Autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.RobotConstants.RobotPIDSettings;

public class Turn extends OrientWithPID {
  /**
   * 
   * @param angle the setpoint which the Robot turns using PID.
   */
  public Turn(double angle) {
    super(Robot.driveTrain, RobotComponents.DriveTrain.GYRO, angle, RobotPIDSettings.TURN_SETTINGS, 360,
        true);
  }
}
