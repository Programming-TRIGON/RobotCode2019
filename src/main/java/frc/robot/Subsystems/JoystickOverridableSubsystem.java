package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class JoystickOverridableSubsystem extends Subsystem{
    public abstract void move(double power);
}