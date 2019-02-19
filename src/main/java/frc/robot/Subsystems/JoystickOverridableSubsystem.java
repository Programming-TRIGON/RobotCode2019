package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/*
* Class extended by subsystems we want to control with a joystick with a generic command.
*/

public abstract class JoystickOverridableSubsystem extends Subsystem{
    public abstract void move(double power);
    public abstract void setSafeControl(boolean isSafe);
}