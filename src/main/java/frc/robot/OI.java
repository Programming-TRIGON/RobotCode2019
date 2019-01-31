package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    private XboxController xbox; 
    private Button buttonA;
    public OI(){
        this.xbox = new XboxController(0);
        this.buttonA = new JoystickButton(this.xbox, 1);
    }
    
    
}
