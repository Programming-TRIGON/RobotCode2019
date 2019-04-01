package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.spikes2212.dashboard.DashBoardController;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants.LiftHeight;
import frc.robot.RobotConstants.OneEightyAngle;
import frc.robot.Autonomous.testAuto;
import frc.robot.CargoCollectorCommands.CollectCargo;
import frc.robot.CargoCollectorCommands.PushCargo;
import frc.robot.CargoFolderCommands.SetCargoFolderState;
import frc.robot.Commands.ChangeCam;
import frc.robot.Commands.CompressorStart;
import frc.robot.Commands.CompressorStop;
import frc.robot.Commands.MoveSubsystemWithJoystick;
import frc.robot.Commands.TestPID;
import frc.robot.DrivingCommands.CheesyDrive;
import frc.robot.HatchCollectorCommands.SetHatchCollectorState;
import frc.robot.HatchHolderCommands.EjectHatch;
import frc.robot.HatchHolderCommands.SetHatchEject;
import frc.robot.HatchHolderCommands.SetHatchLock;
import frc.robot.LiftCommands.SetHeightIndex;
import frc.robot.OneEightyCommands.SetOneEightyDesireAngle;
import frc.robot.Subsystems.CargoCollector;
import frc.robot.Subsystems.CargoFolder;
import frc.robot.Subsystems.DifferentialTankDrivetrain;
import frc.robot.Subsystems.HatchCollector;
import frc.robot.Subsystems.HatchHolder;
import frc.robot.Subsystems.JoystickOverridableSubsystem;
import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.OneEighty;
import frc.robot.Vision.VisionPIDSource;

public class Robot extends TimedRobot {
  private Button a;
  private static final String left = "Left";
  private static final String right = "Right";
  private String m_autoSelected;
  Command autoCommand;
  private final SendableChooser<String> auto_chooser = new SendableChooser<>();

  public static HatchCollector hatchCollector;
  public static Lift lift;
  public static HatchHolder hatchHolder;
  public static OneEighty oneEighty;
  public static CargoCollector cargoCollector;
  public static CargoFolder cargoFolder;
  public static DifferentialTankDrivetrain driveTrain;

  public static DashBoardController dbc;
  public static OI oi;

  final SendableChooser<Command> testsChooser = new SendableChooser<Command>();
  final SendableChooser<JoystickOverridableSubsystem> MoveWithJoystickChooser = new SendableChooser<JoystickOverridableSubsystem>();
  Command testCommand;

  public static Compressor compressor;

  @Override
  public void robotInit() {
    compressor = new Compressor(1);
    compressor.start();
    // compressor.stop();

    auto_chooser.setDefaultOption("Default Auto", left);
    auto_chooser.addOption("My Auto", right);
    SmartDashboard.putData("Auto choices", auto_chooser);
    SmartDashboard.putData("Test Commands", testsChooser);
    SmartDashboard.putData("move ss chooser", MoveWithJoystickChooser);

    // Set deafult vision target to reflector
    NetworkTable imageProcessingTable = NetworkTableInstance.getDefault().getTable("ImageProcessing");
    NetworkTableEntry target = imageProcessingTable.getEntry("target");
    target.setString(VisionPIDSource.VisionTarget.kReflectorForward.toString());

    Robot.dbc = new DashBoardController();

    /** creates the SS htach collector that collects hatch pannels */
    Robot.hatchCollector = new HatchCollector(RobotComponents.HatchCollector.SOLENOID);

    /** defining the subsystem lift that highers the cargo and hatch holders */
    Robot.lift = new Lift(RobotComponents.Lift.LIFT_RIGHT_M, RobotComponents.Lift.LIFT_LEFT_M,

        RobotComponents.Lift.TOP_SWITCH, RobotComponents.Lift.BOTTOM_SWITCH, RobotComponents.Lift.ENCODER);

    RobotComponents.Lift.ENCODER.setDistancePerPulse(RobotConstants.Sensors.LIFT_ENCODER_DPP);

    /**
     * creates the new susbsystem with three solenoids, two that extends the whole
     * SS outward one one that catches the hatch
     */
    Robot.hatchHolder = new HatchHolder(RobotComponents.HatchHolder.PVC, RobotComponents.HatchHolder.PUSH_SOLENOID);

    /*
     * creates the SS that turns the subsytems cargo and hatch holder 180 degrees
     */

    Robot.oneEighty = new OneEighty(RobotComponents.OneEighty.MOTOR, RobotComponents.OneEighty.POT, Robot.lift::getHeight);

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
    RobotComponents.DriveTrain.FRONT_LEFT_M.setInverted(false);
    RobotComponents.DriveTrain.REAR_LEFT_M.setInverted(false);

    RobotComponents.DriveTrain.REAR_RIGHT_M.setInverted(false);
    RobotComponents.DriveTrain.FRONT_RIGHT_M.setInverted(false);

    RobotComponents.DriveTrain.FRONT_LEFT_M.setNeutralMode(NeutralMode.Coast);
    RobotComponents.DriveTrain.REAR_LEFT_M.setNeutralMode(NeutralMode.Coast);
    RobotComponents.DriveTrain.REAR_RIGHT_M.setNeutralMode(NeutralMode.Coast);
    RobotComponents.DriveTrain.FRONT_RIGHT_M.setNeutralMode(NeutralMode.Coast);

    RobotComponents.DriveTrain.FRONT_LEFT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_LEFT_M.getDeviceID());
    RobotComponents.DriveTrain.FRONT_RIGHT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_RIGHT_M.getDeviceID());

    RobotComponents.DriveTrain.LEFT_ENCODER.setDistancePerPulse(RobotConstants.Sensors.DRIVE_ENCODER_DPP);
    RobotComponents.DriveTrain.RIGHT_ENCODER.setDistancePerPulse(RobotConstants.Sensors.DRIVE_ENCODER_DPP);

    RobotComponents.Lift.ENCODER.setDistancePerPulse(RobotConstants.Sensors.LIFT_ENCODER_DPP);

    // Robot.driveTrain = new TankDrivetrain(
    //     (Double speed) -> RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, speed),
    //     (Double speed) -> RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, -speed));

    Robot.driveTrain = new DifferentialTankDrivetrain(RobotComponents.DriveTrain.REAR_LEFT_M, RobotComponents.DriveTrain.REAR_RIGHT_M);  
    
    //lol
    Robot.oi = new OI();
    
    this.a = new JoystickButton(Robot.oi.driverXbox, 8);
    a.whileHeld(new InstantCommand(() -> {
      Robot.oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 1);
      Robot.oi.operatorXbox.setRumble(RumbleType.kRightRumble, 1);
    }));

    Robot.driveTrain.setDefaultCommand(
      new CheesyDrive(Robot.driveTrain, ()->Robot.oi.driverXbox.getY(Hand.kLeft), ()->Robot.oi.driverXbox.getX(Hand.kLeft)));

    // Open/Close solenoids
    SmartDashboard.putData("Hatch Lock", new SetHatchLock(Value.kForward));
    SmartDashboard.putData("Hatch Unlock", new SetHatchLock(Value.kReverse));
    SmartDashboard.putData("Hatch Collector Up", new SetHatchCollectorState(Value.kForward));
    SmartDashboard.putData("Hatch Collector Down", new SetHatchCollectorState(Value.kReverse));
    SmartDashboard.putData("Cargo folder Up", new SetCargoFolderState(Value.kForward));
    SmartDashboard.putData("Cargo folder Down", new SetCargoFolderState(Value.kReverse));
    SmartDashboard.putData("Hatch Eject Push", new SetHatchEject(Value.kForward));
    SmartDashboard.putData("Hatch Eject Pull", new SetHatchEject(Value.kReverse));
    SmartDashboard.putData("Eject hatch", new EjectHatch());

    SmartDashboard.putData(new SetHeightIndex(LiftHeight.kOneEightySafety));

    SmartDashboard.putData("Stop Compressor", new CompressorStop());
    SmartDashboard.putData("Start Compressor", new CompressorStart());
    
    SmartDashboard.putData("Collect Cargo", new CollectCargo(0.85, 0.5));
    SmartDashboard.putData("Push Cargo", new PushCargo());

    SmartDashboard.putData(new TestPID());

    // Auto command tests
    SmartDashboard.putData("Test auto", new testAuto());

    // Robot data to be periodically published to SmartDashboard
    dbc.addNumber("Gyro", RobotComponents.DriveTrain.GYRO::getAngleX);
    dbc.addNumber("Right encoder", RobotComponents.DriveTrain.RIGHT_ENCODER::getDistance);
    dbc.addNumber("Left encoder", RobotComponents.DriveTrain.LEFT_ENCODER::getDistance);
    dbc.addNumber("180 potentiometer", Robot.oneEighty::getAngle);
    dbc.addNumber("Lift encoder", Robot.lift::getHeight);

    // Robot states to be periodically published to SmartDashboard
    dbc.addString("One Eighty angle", () -> RobotStates.getDesireOneEightyAngle().toString());
    dbc.addString("Lift Height", () -> RobotStates.getLiftHeight().toString());
    dbc.addNumber("Height index", RobotStates::getHeightIndex);
    dbc.addBoolean("One Eighty Override", RobotStates::isOneEightyOverride);
    dbc.addBoolean("Lift Override", RobotStates::isLiftOverride);
    dbc.addBoolean("Is Has Cargo", RobotStates::isHasCargo);
    dbc.addBoolean("Inverted Drive", RobotStates::isDriveInverted);
    dbc.addBoolean("Is collected", RobotStates::isCollected);

    addTests();

    SmartDashboard.putData(new ChangeCam());
    RobotStates.oneEightyOverride = false;
    RobotStates.LiftOverride = false;

    Scheduler.getInstance().add(new SetHatchLock(Value.kReverse));
    Scheduler.getInstance().add(new SetHeightIndex(LiftHeight.kCargoCollection));
    Scheduler.getInstance().add(new SetOneEightyDesireAngle(OneEightyAngle.kCargoCollection));
    
  }

  @Override
  public void robotPeriodic() {
    Robot.dbc.update();
    if (Robot.lift.isAtBottom())
      RobotComponents.Lift.ENCODER.reset();
    RobotStates.setHasCargo(Robot.cargoCollector.isHoldingBall());
    SmartDashboard.putNumber("lift left motor current (A)", RobotComponents.Lift.LIFT_LEFT_M.getOutputCurrent());
    SmartDashboard.putNumber("lift right motor current (A)", RobotComponents.Lift.LIFT_RIGHT_M.getOutputCurrent());

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void disabledInit() {
    RobotStates.setLiftHeight(LiftHeight.kCargoCollection);
    RobotComponents.Lift.ENCODER.reset();
  }

  @Override
  public void autonomousInit() {
    Scheduler.getInstance().add(new SetHatchLock(Value.kForward));

    m_autoSelected = auto_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);
    RobotComponents.DriveTrain.RIGHT_ENCODER.reset();
    RobotComponents.DriveTrain.LEFT_ENCODER.reset();
    RobotComponents.DriveTrain.GYRO.reset();

    switch (m_autoSelected) {
    case right:
      // this.autoCommand = new ScoreCargoSide(true);
      break;
    case left:
    default:
      break;
    }
    Scheduler.getInstance().add(autoCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    for(int i = 15; i >= 0; i--)
    {
      SmartDashboard.putNumber("Sandstorm time left:", i);
      new WaitCommand(1);
    }
  }

  @Override
  public void teleopInit() {
    if (autoCommand != null)
      autoCommand.close();

    Scheduler.getInstance().add(new SetHatchEject(Value.kForward));
    Scheduler.getInstance().add(new SetHatchLock(Value.kForward));
    Scheduler.getInstance().add(new SetCargoFolderState(Value.kForward));
    Scheduler.getInstance().add(new SetOneEightyDesireAngle(OneEightyAngle.kCargoCollection));

    testCommand = testsChooser.getSelected();
    SmartDashboard.putData("Test Command", testCommand);
    SmartDashboard.putData("move selected subsystem",
        new MoveSubsystemWithJoystick(MoveWithJoystickChooser.getSelected(), oi.operatorXbox));
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putData("Scheduler", Scheduler.getInstance());
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putData("selected test command", this.testsChooser.getSelected());
  }

  private void addTests() {
    testsChooser.addDefault("Hatch Unlock Default", new SetHatchLock(Value.kReverse));
    testsChooser.addOption("cargoCollection", new CollectCargo(0.8, 0.8));

    testsChooser.addOption("Lift", new SetHeightIndex(LiftHeight.kRocketMiddleCargo));
    testsChooser.addOption("One Eighty", new SetOneEightyDesireAngle(OneEightyAngle.kStraight));

    testsChooser.addOption("hatchEjectOn", new SetHatchEject(Value.kForward));
    testsChooser.addOption("hatchEjectOff", new SetHatchEject(Value.kReverse));

    testsChooser.addOption("hatchLockOn", new SetHatchLock(Value.kForward));
    testsChooser.addOption("hatchLockOff", new SetHatchLock(Value.kReverse));

    testsChooser.addOption("hatchCollectorOn", new SetHatchCollectorState(Value.kForward));
    testsChooser.addOption("hatchCollectorOff", new SetHatchCollectorState(Value.kReverse));

    MoveWithJoystickChooser.addDefault("Cargo Collector", Robot.cargoCollector);
    MoveWithJoystickChooser.addOption("Lift", Robot.lift);
    MoveWithJoystickChooser.addOption("Cargo Holder", Robot.cargoCollector);
    MoveWithJoystickChooser.addOption("One Eighty", Robot.oneEighty);
  }
}
