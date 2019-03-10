package frc.robot.Triggers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * make the xbox triggres buttons
 */
public class XboxTrigger extends Trigger {
  XboxController xbox;
  Hand hand;
  public XboxTrigger(XboxController xbox, Hand hand){
    this.xbox = xbox;
    this.hand = hand;
  }
  @Override
  public boolean get() {
    return this.xbox.getTriggerAxis(this.hand) >= 0.25;
  }
}
