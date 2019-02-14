package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.OneEighty;
import frc.robot.TestCommands.CargoHolderTest;
import frc.robot.TestCommands.CargoRollerTest;
import frc.robot.Autonomous.TestPID;
import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.SetCargoFolderState;
import frc.robot.Commands.SetHatchEject;
import frc.robot.Commands.SetHatchLock;
import frc.robot.Commands.SetLiftHeight;
import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Commands.setHatchCollectorState;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Subsystems.CargoCollector;
import frc.robot.Subsystems.HatchHolder;
import frc.robot.Subsystems.CargoFolder;
import frc.robot.Subsystems.HatchCollector;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  public static TankDrivetrain driveTrain;

  public static DashBoardController dbc;
  public static OI oi;

  final SendableChooser<Command> testsChooser = new SendableChooser<Command>();;
  public static Compressor comp;

  @Override
  public void robotInit() {
    comp = new Compressor(1);
    comp.stop();
    
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    SmartDashboard.putData("Test Commands", testsChooser);

    Robot.oi = new OI();

    Robot.dbc = new DashBoardController();

    /** creates the SS htach collector that collects hatch pannels */
    Robot.hatchCollector = new HatchCollector(RobotComponents.HatchCollector.SOLENOID);

    /** defining the subsystem lift that highers the cargo and hatch holders */
    Robot.lift = new Lift(RobotComponents.Lift.LIFT_RIGHT_M, RobotComponents.Lift.LIFT_LEFT_M,
        RobotComponents.Lift.TOP_SWITCH, RobotComponents.Lift.BOTTOM_SWITCH, RobotComponents.Lift.POT);

    /**
     * creates the new susbsystem with three solenoids, two that extends the whole
     * SS outward one one that catches the hatch
     */
    Robot.hatchHolder = new HatchHolder(RobotComponents.HatchHolder.PVC, RobotComponents.HatchHolder.PUSH_SOLENOID);

    /*
     * creates the SS that turns the subsytems cargo and hatch holder 180 degrees
     */
    Robot.oneEighty = new OneEighty(RobotComponents.OneEighty.MOTOR, RobotComponents.OneEighty.POT);

    /*
     * creates the new SS that collects corgo by turning wheels that bring it in
     */
    Robot.cargoCollector = new CargoCollector(RobotComponents.CargoCollector.COLECTOR_MOTOR,
        RobotComponents.CargoCollector.RIGHT_HOLDER, RobotComponents.CargoCollector.LEFT_HOLDER,
        RobotComponents.CargoCollector.SWITCH);
    
    /*
     * creates the SS corgo fold that extends and retracts the whole SS of the cargo
     * collector with it
     */
    Robot.cargoFolder = new CargoFolder(RobotComponents.CargoFolder.SOLENOID);

    /*
     * creates the drive train SS with SpikesLib
     */
    RobotComponents.DriveTrain.FRONT_LEFT_M.setInverted(true);
    RobotComponents.DriveTrain.REAR_LEFT_M.setInverted(true);

    RobotComponents.DriveTrain.FRONT_LEFT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_LEFT_M.getDeviceID()); 
    RobotComponents.DriveTrain.FRONT_RIGHT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_RIGHT_M.getDeviceID()); 
    
    RobotComponents.DriveTrain.LEFT_ENCODER.setDistancePerPulse(RobotConstants.ENCODER_DPP);
    RobotComponents.DriveTrain.RIGHT_ENCODER.setDistancePerPulse(RobotConstants.ENCODER_DPP);

    Robot.driveTrain = new TankDrivetrain(
      (Double speed) -> RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, speed),
      (Double speed) -> RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, -speed));

    SmartDashboard.putData(new TestPID());

    SmartDashboard.putData("Hatch Lock", new SetHatchLock(Value.kForward));
    SmartDashboard.putData("Hatch Unlock", new SetHatchLock(Value.kReverse));
    SmartDashboard.putData("Hatch Collector Up", new setHatchCollectorState(Value.kForward));
    SmartDashboard.putData("Hatch Collector Down", new setHatchCollectorState(Value.kReverse));
    SmartDashboard.putData("Cargo Folder Up", new SetCargoFolderState(Value.kForward));
    SmartDashboard.putData("Cargo Folder Down", new SetCargoFolderState(Value.kReverse));
    SmartDashboard.putData("Hatch Eject Push", new SetHatchEject(Value.kForward));
    SmartDashboard.putData("Hatch Eject Pull", new SetHatchEject(Value.kReverse));
    SmartDashboard.putData("Drive", new DriveArcade(Robot.driveTrain, Robot.oi.operatorXbox::getY, Robot.oi.operatorXbox::getX));
    SmartDashboard.putData("Collect Cargo", new CollectCargo(0.85, 0.5));

    dbc.addNumber("Gyro", RobotComponents.DriveTrain.GYRO::getAngle);
    dbc.addNumber("Right encoder", RobotComponents.DriveTrain.RIGHT_ENCODER::getDistance);
    dbc.addNumber("Left encoder", RobotComponents.DriveTrain.LEFT_ENCODER::getDistance);
    dbc.addNumber("180 pot", Robot.oneEighty::getAngle);
    
    addTests();

  }

  @Override
  public void robotPeriodic() {
    Robot.dbc.update();
    SmartDashboard.putData("Scheduler", Scheduler.getInstance());
  }
  
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
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
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
  }
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putData("selected test command", this.testsChooser.getSelected());
  }

  private void addTests(){
    testsChooser.addDefault("Hatch Unlock Default", new SetHatchLock(Value.kReverse));

    testsChooser.addOption("Cargo Roller", new CargoRollerTest());
    testsChooser.addOption("Cargo Holder", new CargoHolderTest());
    testsChooser.addOption("Lift", new SetLiftHeight(LiftHeight.kRocketMiddleCargo));
    testsChooser.addOption("One Eighty", new SetOneEightyAngle(OneEightyAngle.kStraight));

    testsChooser.addOption("Cargo Folder Up", new SetCargoFolderState(Value.kForward));
    testsChooser.addOption("Cargo Folder Down", new SetCargoFolderState(Value.kReverse));

    testsChooser.addOption("Hatch Eject", new SetHatchEject(Value.kForward));
    testsChooser.addOption("Hatch Pull", new SetHatchEject(Value.kReverse));

    testsChooser.addOption("Hatch Lock", new SetHatchLock(Value.kForward));
    testsChooser.addOption("Hatch Unlock", new SetHatchLock(Value.kReverse));

    testsChooser.addOption("Hatch Collector Up", new setHatchCollectorState(Value.kForward));
    testsChooser.addOption("Hatch Collector Down", new setHatchCollectorState(Value.kReverse));
  }
}