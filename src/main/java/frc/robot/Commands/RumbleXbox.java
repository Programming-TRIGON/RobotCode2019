package frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RumbleXbox extends Command {
  boolean rumbleTwoXbox;
  public RumbleXbox(boolean rumbleTwoXbox) {
    setTimeout(0.2);    
  }

  @Override
  protected void initialize() {
    Robot.oi.driverXbox.setRumble(RumbleType.kLeftRumble, 1);
    Robot.oi.driverXbox.setRumble(RumbleType.kRightRumble, 1);
    if(this.rumbleTwoXbox){
      Robot.oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 1);
      Robot.oi.operatorXbox.setRumble(RumbleType.kRightRumble, 1);
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.oi.driverXbox.setRumble(RumbleType.kLeftRumble, 0);
    Robot.oi.driverXbox.setRumble(RumbleType.kRightRumble, 0);
    Robot.oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 0);
    Robot.oi.operatorXbox.setRumble(RumbleType.kRightRumble, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
