package frc.robot.DrivingCommands;

import com.spikes2212.genericsubsystems.drivetrains.commands.OrientWithPID;

import frc.robot.Robot;
import frc.robot.RobotComponents;
import frc.robot.Commands.GyroPIDSource;
import frc.robot.RobotConstants.RobotPIDSettings;

public class Turn extends OrientWithPID {
  static final GyroPIDSource GYRO_PID_SOURCE = new GyroPIDSource();
  /**
   *
   * @param angle the setpoint which the Robot turns using PID.
   */
  public Turn(double angle) {
    super(Robot.driveTrain, Turn.GYRO_PID_SOURCE, angle, RobotPIDSettings.TURN_SETTINGS, 360, true);
  }

  @Override
  protected void initialize() {
    RobotComponents.DriveTrain.GYRO.reset();
    super.initialize();
  }
}
