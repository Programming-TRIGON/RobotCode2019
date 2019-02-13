package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class OI {
    XboxController operatorXbox = new XboxController(0);  
   
    public OI(){

    }   
    public double getYLeft(){
        return this.operatorXbox.getY(Hand.kLeft);
    }
}
