package frc.robot;

import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.OneEighty;
import frc.robot.TestCommands.CargoHolderTest;
import frc.robot.TestCommands.CargoRollerTest;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import frc.robot.Commands.CollectCargo;
import frc.robot.Commands.MoveSubsystemWithJoystick;
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

  public static Compressor comp;

  final SendableChooser<Command> testsChooser = new SendableChooser<Command>();;

  @Override
  public void robotInit() {
    comp = new Compressor(RobotMap.CAN.PCM);
    comp.start();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);

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

    Robot.oneEighty = new OneEighty(RobotComponents.OneEighty.MOTOR, RobotComponents.OneEighty.POT,
        Robot.lift::getHeight);

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
    RobotComponents.DriveTrain.FRONT_LEFT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_LEFT_M.getDeviceID()); // now front and rear motors are moving toghether
    RobotComponents.DriveTrain.FRONT_RIGHT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_RIGHT_M.getDeviceID()); // ditto
    // made functions that set speed to the motors on the drive train by double
    // insted of ControlMode and double
    Robot.driveTrain = new TankDrivetrain(
        (Double speed) -> RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, speed),
        (Double speed) -> RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, speed));

    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.lift, Robot.oi.operatorXbox, "lift"));
    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.oneEighty, Robot.oi.operatorXbox, "180"));
    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.cargoCollector, Robot.oi.operatorXbox, "cargo holder"));
    SmartDashboard.putData(new DriveArcade(Robot.driveTrain, Robot.oi.operatorXbox::getY, Robot.oi.operatorXbox::getX));

    dbc.addNumber("Drive train gyro", RobotComponents.DriveTrain.GYRO::getAngle);
  }

  @Override
  public void robotPeriodic() {
    Robot.dbc.update();
    SmartDashboard.putData(Scheduler.getInstance());
  }

  @Override
  public void disabledPeriodic() {
    //Scheduler.getInstance().run();
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
    //Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // testsChooser.addOption("cargoRoller", new CargoRollerTest());
    // testsChooser.addOption("cargoHolder", new CargoHolderTest());
    testsChooser.addOption("cargoCollection", new CollectCargo(0.3, 0.3));

    testsChooser.addOption("lift", new SetLiftHeight(LiftHeight.kRocketMiddleCargo));
    testsChooser.addOption("oneEighty", new SetOneEightyAngle(OneEightyAngle.kStraight));

    testsChooser.addOption("hatchEject", new SetHatchEject(Value.kForward));
    testsChooser.addOption("hatchEject", new SetHatchEject(Value.kReverse));

    testsChooser.addOption("hatchLock", new SetHatchLock(Value.kForward));
    testsChooser.addOption("hatchLock", new SetHatchLock(Value.kReverse));

    testsChooser.addOption("hatchCollector", new setHatchCollectorState(Value.kForward));
    testsChooser.addOption("hatchCollector", new setHatchCollectorState(Value.kReverse));


  }
  @Override
  public void testPeriodic() {
    SmartDashboard.putData("selected test command" ,testsChooser.getSelected());

  }
}