package frc.robot.LiftCommands;

import java.util.function.Supplier;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotStates;
import frc.robot.RobotConstants.LiftHeight;

public class ReachLiftHeight extends Command {
  private Supplier<LiftHeight> height;
  private PIDController pidController;
  private PIDOutput pidOutput;
  private PIDSettings pidSettings;
  private double setpoint;

  private double lastTimeNotOn = 0;

  /** sets the height of the lift depending on the situation */
  public ReachLiftHeight(LiftHeight finishingHeight) {
    requires(Robot.lift);
    this.height = () -> finishingHeight;
    this.pidSettings = RobotConstants.RobotPIDSettings.LIFT_HEIGHT_SETTINGS;
  }

  public ReachLiftHeight(LiftHeight finishingHeight, PIDSettings pidSettings) {
    requires(Robot.lift);
    this.height = () -> finishingHeight;
    this.pidSettings = pidSettings;
  }
  public ReachLiftHeight(Supplier<LiftHeight> finishingHeight) {
    requires(Robot.lift);
    this.height =  finishingHeight;
    this.pidSettings = RobotConstants.RobotPIDSettings.LIFT_HEIGHT_SETTINGS;
  }

  @Override
  protected void initialize() {
    Robot.cargoFolder.setFold(Value.kReverse);

    this.pidOutput = new PIDOutput() {
      public void pidWrite(double output) {
        Robot.lift.setMotorSpeed(output + RobotConstants.LIFT_BASE_POWER);
      }
    };
    this.pidController = new PIDController(this.pidSettings.getKP(),
      this.pidSettings.getKI(),
      this.pidSettings.getKD(),
      Robot.lift.getEncoder(), this.pidOutput);
    this.setpoint = height.get().key;
    pidController.setSetpoint(setpoint);
    pidController.setAbsoluteTolerance(this.pidSettings.getTolerance());
    pidController.setOutputRange(-0.7, 1); // was 0.5,1
    pidController.enable();
  }

  
  @Override
  protected void execute() {
    // if (Robot.lift.getHeight() <= RobotConstants.LiftHeight.kCargoFolderSafty.key)
    //   Robot.cargoFolder.setFold(Value.kForward);
    double newSetpoint = height.get().key;
    if(newSetpoint!= setpoint){
      pidController.setSetpoint(newSetpoint);
      setpoint = newSetpoint;
    }
  }

  @Override
  protected boolean isFinished() {
    // if(!this.pidController.onTarget())
    // {   
    //    this.lastTimeNotOn = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
    // }
    // if((edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - this.lastTimeNotOn ) >= this.pidSettings.getWaitTime())
    //     return false; ///Aahahahahahahahahahahahaha
    return RobotStates.isLiftOverride();
  }

  @Override
  protected void end() {
    pidController.disable();
    pidController.close();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
