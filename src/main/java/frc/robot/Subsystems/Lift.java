package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
  /** This motor group combines the two motors that turn to higher the lift */
  private SpeedControllerGroup motorGroup;
  /** these limit switches make sure the lift doesn't pass the required highet */
  private DigitalInput topSwitch, bottomSwitch;

  private AnalogPotentiometer potentiometer;

  public Lift(SpeedController rightMotor, SpeedController leftMotor, DigitalInput topwSwitch, DigitalInput bottomSwitch,
      AnalogPotentiometer potentiometer) {
    this.motorGroup = new SpeedControllerGroup(rightMotor, leftMotor);
    this.topSwitch = topwSwitch;
    this.bottomSwitch = bottomSwitch;
    this.potentiometer = potentiometer;
  }

  /** sets the speed of the motors of the lift to higher/lower it */
  public void setMotorSpeed(Double speed) {
    motorGroup.set(speed);
  }

  /** This function checks whether the lift has activated the top micro switch. */
  public boolean getTopSwitch() {
    return topSwitch.get();
  }

  /**
   * This function checks whether the lift has activated the botton micro switch.
   */
  public boolean getBottomSwitch() {
    return bottomSwitch.get();
  }

  /** This function returns the curent state of the potentiometer. */
  public Double getPotentiometer() {
    return this.potentiometer.get();
  }

  @Override
  public void initDefaultCommand() {

  }
}