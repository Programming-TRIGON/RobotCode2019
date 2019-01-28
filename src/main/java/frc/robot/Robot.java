package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Lift;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.OneEighty;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.SubSystems.CargoCollector;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.HatchHolder;
import frc.robot.SubSystems.CargoFolder;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static Lift lift;
  public HatchHolder hatchHolder;
  public OneEighty oneEighty;
  public static CargoCollector cargoCollector;
  public static CargoFolder cargoFolder;
 

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    /** defining the subsystem lift that highers the cargo and hatch holders */
    lift = new Lift(new TalonSRX(RobotMap.LIFT_LEFT_MOTOR), new TalonSRX(RobotMap.LIFT_RIGHT_MOTOR),
        new DigitalInput(RobotMap.LIFT_BOTTOM_MICRO_SWITCH), new DigitalInput(RobotMap.LIFT_TOP_MICRO_SWITCH),
        new AnalogPotentiometer(RobotMap.LIFT_POTENTIOMETER, RobotConstants.Sensors.LIFT_POTENTIOMETER_SCALE_FACTOR,
            RobotConstants.Sensors.LIFT_POTENTOIMETER_OFFSET));
    /**
     * creates the new susbsystem with three solenoids, two that extends the whole
     * SS outward one one that catches the hatch
     */
    this.hatchHolder = new HatchHolder(
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_PVC_SOLENOID_A, RobotMap.HATCH_HOLDER_PVC_SOLENOID_B),
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_RIGHT_PUSH_SOLENOID_A, RobotMap.HATCH_HOLDER_RIGHT_PUSH_SOLENOID_B),
        new DoubleSolenoid(RobotMap.HATCH_HOLDER_LEFT_PUSH_SOLENOID_A, RobotMap.HATCH_HOLDER_LEFT_PUSH_SOLENOID_B));
    /*
     * creates the SS that turns the subsytems cargo and hatch holder 180 degrees
     */
    this.oneEighty = new OneEighty(
        new TalonSRX(RobotMap.ONE_EIGHTY_MOTOR), new AnalogPotentiometer(RobotMap.ONE_EIGHTY_POTENTIOMETER,
            RobotConstants.POTENTIOMETER_ANGLE_MULTIPLIER, RobotConstants.POTENTIOMETER_OFFSET));
    /*
     * creates the new SS that collects corgo by turning wheels that bring it in
     */
    this.cargoCollector = new CargoCollector(new TalonSRX(RobotMap.CARGO_COLLECTOR_MOTOR),
        new VictorSPX(RobotMap.CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR),
        new VictorSPX(RobotMap.CARGO_COLLECTOR_HOLDER_LEFT_MOTOR),new AnalogInput(0));
    /*
     * creates the SS corgo fold that extends and retracts the whole SS of the cargo
     * collector with it
     */
    this.cargoFolder = new CargoFolder(
        new DoubleSolenoid(RobotMap.CARGO_FOLDER_SOLENOID_A, RobotMap.CARGO_FOLDER_SOLENOID_B),
        new DigitalInput(RobotMap.CARGO_FOLDER_TOP_SWITCH), new DigitalInput(RobotMap.CARGO_FOLDER_BOTTOM_SWITCH));

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