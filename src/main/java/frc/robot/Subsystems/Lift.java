package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotStates;
import frc.robot.LiftCommands.LiftDefaultCommand;

public class Lift extends JoystickOverridableSubsystem {
  private TalonSRX leftMotor, rightMotor;
  private DigitalInput topSwitch, bottomSwitch;
  private Encoder encoder;
  private boolean safeControl = true;
  double motorRamp = 0.25;

  final int peakCurrentLimit = 20, continuousCurrentLimit = 3;

  public Lift(TalonSRX rightMotor, TalonSRX leftMotor, DigitalInput topwSwitch, DigitalInput bottomSwitch,
      Encoder encoder) {
    this.rightMotor = rightMotor;
    this.leftMotor = leftMotor;
    this.topSwitch = topwSwitch;
    this.bottomSwitch = bottomSwitch;
    this.encoder = encoder;
    this.rightMotor.setInverted(false);
    this.leftMotor.setInverted(false);
    this.rightMotor.setNeutralMode(NeutralMode.Brake);
    this.leftMotor.setNeutralMode(NeutralMode.Brake);
    leftMotor.configOpenloopRamp(motorRamp);
    rightMotor.configOpenloopRamp(motorRamp);

    leftMotor.configContinuousCurrentLimit(continuousCurrentLimit);
    rightMotor.configContinuousCurrentLimit(continuousCurrentLimit);

    rightMotor.configPeakCurrentDuration(2000);
    leftMotor.configPeakCurrentDuration(2000);

    leftMotor.configPeakCurrentLimit(peakCurrentLimit);
    rightMotor.configPeakCurrentLimit(peakCurrentLimit);

    leftMotor.enableCurrentLimit(true);
    rightMotor.enableCurrentLimit(true);
  }

  /** sets the speed of the motors of the lift to higher/lower it */
  public void setMotorSpeed(double speed) {
    if (((speed > 0 && isAtTop()) || (speed < 0 && isAtBottom()))) {
      rightMotor.set(ControlMode.PercentOutput, 0);
      leftMotor.set(ControlMode.PercentOutput, 0);
    } else {
      rightMotor.set(ControlMode.PercentOutput, speed);
      leftMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  /** This function checks whether the lift has activated the top micro switch. */
  public boolean isAtTop() {
    return !topSwitch.get();
  }

  /**
   * This function checks whether the lift has activated the botton micro switch.
   */
  public boolean isAtBottom() {
    return !bottomSwitch.get();
  }

  /** This function returns the curent state of the encoder. */
  public Encoder getEncoder() {
    return this.encoder;
  }

  public double getHeight() {
    return this.encoder.getDistance() / 0.555;
  }

  @Override
  public void move(double power) {
    setMotorSpeed(power);
  }

  @Override
  public void setSafeControl(boolean isSafe) {
    this.safeControl = isSafe;
  }
  
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftDefaultCommand(RobotStates::getLiftHeight));
  }

}