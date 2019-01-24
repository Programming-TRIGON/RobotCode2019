package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.MathFunctions;

public class Lift extends Subsystem {
  private Encoder encoder;
  private SpeedControllerGroup motorGroup;

  public Lift(SpeedController rightMotor, SpeedController leftMotor, Encoder encoder) {
    this.motorGroup = new SpeedControllerGroup(rightMotor, leftMotor);
    this.encoder = encoder;
  }

  public void setMotorSpeed(Double speed) {
    motorGroup.set(speed);
  }

  /**
   * The Encoder uses ticks to measure movement. 4 tick = 1 pulse. This function
   * tells the encoder how many meters does a single pulse produce. Then this
   * function tells us how many meters has the encoder counted.
   */
  public void resetEncoders(){
    encoder.reset();
  }
  public double getHight() {
    encoder.setDistancePerPulse(MathFunctions.PULSE_TO_METER);
    return encoder.getDistance();
  }

  @Override
  public void initDefaultCommand() {

  }
}