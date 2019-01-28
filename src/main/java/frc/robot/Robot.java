package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.CargoCollector;
import frc.robot.Subsystems.CargoFolder;
import frc.robot.Subsystems.HatchCollector;
import frc.robot.Subsystems.HatchHolder;
import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.OneEighty;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static HatchCollector hatchCollector;
  public static Lift lift;
  public static HatchHolder hatchHolder;
  public static OneEighty oneEighty;
  public static CargoCollector cargoCollector;
  public static CargoFolder cargoFolder;
 

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    /** creates the SS htach collector that collects hatch pannels */
    this.hatchCollector = new HatchCollector(
        new DoubleSolenoid(RobotMap.HATCH_COLLECTOR_SOLENOID_A, RobotMap.HATCH_COLLECTOR_SOLENOID_B));

    /** defining the subsystem lift that highers the cargo and hatch holders */
    lift = new Lift(new TalonSRX(RobotMap.LIFT_LEFT_MOTOR), new TalonSRX(RobotMap.LIFT_RIGHT_MOTOR),
        new DigitalInput(RobotMap.LIFT_BOTTOM_MICRO_SWITCH), new DigitalInput(RobotMap.LIFT_TOP_MICRO_SWITCH),
        new AnalogPotentiometer(RobotMap.LIFT_POTENTIOMETER, RobotConstants.Sensors.LIFT_POTENTIOMETER_SCALE_FACTOR,
            RobotConstants.Sensors.LIFT_POTENTOIMETER_OFFSET));
    /**
     * creates the new susbsystem with three solenoids, two that extends the whole
     * SS outward one one that catches the hatch
     */
    Robot.hatchHolder = new HatchHolder(RobotComponents.HatchHolder.PVC, RobotComponents.HatchHolder.RIGHT_HOLDER, RobotComponents.HatchHolder.LEFT_HOLDER);
      
    /*
     * creates the SS that turns the subsytems cargo and hatch holder 180 degrees
     */
    Robot.oneEighty = new OneEighty(RobotComponents.OneEighty.MOTOR, RobotComponents.OneEighty.POT);
      
    /*
     * creates the new SS that collects corgo by turning wheels that bring it in
     */
    Robot.cargoCollector = new CargoCollector(RobotComponents.CargoCollector.COLECTOR_MOTOR, 
    RobotComponents.CargoCollector.RIGHT_HOLDER, RobotComponents.CargoCollector.LEFT_HOLDER, RobotComponents.CargoCollector.COLOR_SENSOR);
    /*
     * creates the SS corgo fold that extends and retracts the whole SS of the cargo
     * collector with it
     */
    Robot.cargoFolder = new CargoFolder(RobotComponents.CargoFolder.SOLENOID, RobotComponents.CargoFolder.BOTTOM_SWITCH, RobotComponents.CargoFolder.TOP_SWITCH);

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