package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.HatchHolder;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public HatchHolder hatchHolder;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    /**
     * creates the new susbsystem with three solenoids, two that extends the whole
     * SS outward one one that catches the hatch
     */
    this.hatchHolder = new HatchHolder(
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_PVC_SOLENOID_A, RobotMap.HATCH_HOLDER_PVC_SOLENOID_B),
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_RIGHT_PUSH_SOLENOID_A, RobotMap.HATCH_HOLDER_RIGHT_PUSH_SOLENOID_B),
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_LEFT_PUSH_SOLENOID_A, RobotMap.HATCH_HOLDER_LEFT_PUSH_SOLENOID_B));
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      break;
    case kDefaultAuto:
    default:
      break;
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }
}