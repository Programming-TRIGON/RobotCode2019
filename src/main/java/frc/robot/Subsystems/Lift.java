package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.MathFunctions;

public class Lift extends Subsystem {
  /** This motor group combines the two motors that turn to higher the lift */
  private SpeedControllerGroup motorGroup;
  /** this encoder is in charge of keeping the lift where we set it */
  private Encoder encoder;

  public Lift(SpeedController rightMotor, SpeedController leftMotor, Encoder encoder) {
    this.motorGroup = new SpeedControllerGroup(rightMotor, leftMotor);
    this.encoder = encoder;
  }

  public void setMotorSpeed(Double speed) {
    motorGroup.set(speed);
  }

  /** we reset the encoders for measuring the correct distance */
  public void resetEncoders() {
    encoder.reset();
  }

  /**
   * The Encoder uses ticks to measure movement. 4 tick = 1 pulse. This function
   * tells the encoder how many meters does a single pulse produce. Then this
   * function tells us how many meters has the encoder counted.
   */
  public double getHight() {
    encoder.setDistancePerPulse(MathFunctions.PULSE_TO_METER);
    return encoder.getDistance();
  }

  @Override
  public void initDefaultCommand() {

  }
}