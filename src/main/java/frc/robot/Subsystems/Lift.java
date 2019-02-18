package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotConstants;

public class Lift extends JoystickOverridableSubsystem {
  /** Motors to raise/lower the lift */
  private TalonSRX leftMotor, rightMotor;
  /** Top and bottom limit switches */
  private DigitalInput topSwitch, bottomSwitch;

  private Encoder encoder;

  public Lift(TalonSRX rightMotor, TalonSRX leftMotor, DigitalInput topwSwitch, DigitalInput bottomSwitch,
      Encoder encoder) {
    this.rightMotor = rightMotor;
    this.leftMotor = leftMotor;
    this.topSwitch = topwSwitch;
    this.bottomSwitch = bottomSwitch;
    this.encoder = encoder;
    this.rightMotor.setInverted(true);
    this.leftMotor.setInverted(true);
    this.rightMotor.setNeutralMode(NeutralMode.Brake);
    this.leftMotor.setNeutralMode(NeutralMode.Brake);
  }

  /** sets the speed of the motors of the lift to higher/lower it */
  public void setMotorSpeed(double speed) {
    if(speed > 0 && isAtTop() || speed < 0 && isAtBottom()){
      rightMotor.set(ControlMode.PercentOutput, 0);
      leftMotor.set(ControlMode.PercentOutput, 0);
    }
      
    else{
      rightMotor.set(ControlMode.PercentOutput, speed);
      leftMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  /** This function checks whether the lift has activated the top micro switch. */
  public boolean isAtTop() {
    return topSwitch.get();
  }

  /**
   * This function checks whether the lift has activated the botton micro switch.
   */
  public boolean isAtBottom() {
    return bottomSwitch.get();
  }

  /** This function returns the curent state of the encoder. */
  public Encoder getEncoder() {
    return this.encoder;
  }

  public double getHeight(){
    return this.encoder.getDistance() + RobotConstants.Sensors.LIFT_ENCODER_OFFSET;
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  public void move(double power) {
    setMotorSpeed(power);
  }
}