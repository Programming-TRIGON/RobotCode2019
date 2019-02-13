package frc.robot;

import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.OneEighty;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.followers.EncoderFollower;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;

import frc.robot.Commands.MoveSubsystemWithJoystick;

import frc.robot.Commands.SetOneEightyAngle;
import frc.robot.Subsystems.CargoCollector;
import frc.robot.Subsystems.HatchHolder;
import frc.robot.Subsystems.CargoFolder;
import frc.robot.Subsystems.HatchCollector;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
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
  public static TankDrivetrain DriveTrain;

  public static DashBoardController dbc;
  public static OI oi;

  public static Compressor compressor;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);

    Robot.oi = new OI();

    Robot.dbc = new DashBoardController();

    compressor = new Compressor(1);
    compressor.stop();
    //compressor.start();

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
    RobotComponents.DriveTrain.FRONT_LEFT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_LEFT_M.getDeviceID()); // now front and rear motors are moving toghether
    RobotComponents.DriveTrain.FRONT_RIGHT_M.set(ControlMode.Follower,
        RobotComponents.DriveTrain.REAR_RIGHT_M.getDeviceID()); // ditto
    // made functions that set speed to the motors on the drive train by double
    // insted of ControlMode and double
    Robot.DriveTrain = new TankDrivetrain(
        (Double speed) -> RobotComponents.DriveTrain.REAR_LEFT_M.set(ControlMode.PercentOutput, speed),
        (Double speed) -> RobotComponents.DriveTrain.REAR_RIGHT_M.set(ControlMode.PercentOutput, speed));

    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.lift, Robot.oi.operatorXbox, "lift"));
    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.oneEighty, Robot.oi.operatorXbox, "180"));
    SmartDashboard.putData(new MoveSubsystemWithJoystick(Robot.cargoCollector, Robot.oi.operatorXbox, "cargo holder"));
    SmartDashboard.putData(new com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade(Robot.DriveTrain,
        Robot.oi.operatorXbox::getY, Robot.oi.operatorXbox::getX));
    SmartDashboard.putData(new SetOneEightyAngle(180));

    dbc.addNumber("180 potentiometer angle", Robot.oneEighty::getAngle);
    dbc.addNumber("Right encoder", RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT::getDistance);
    dbc.addNumber("Left encoder", RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT::getDistance);
  }

  @Override
  public void robotPeriodic() {
    Robot.dbc.update();
    SmartDashboard.putData(Scheduler.getInstance());
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);
             /** reseting sensors */
             RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.reset();
             RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.reset();
             RobotComponents.DriveTrain.DRIVETRAIN_GYRO.reset();
             /**
              * The tarjectory is the path(each side of the drivetrain has one) this is like
              * the "error" in PID
              */
             // TODO: check when this bug is fixed (wpilibs side)
             PathFinderClass.leftTrajectory = PathfinderFRC.getTrajectory(PathFinderClass.pathName + ".right");
             PathFinderClass.rightTrajectory = PathfinderFRC.getTrajectory(PathFinderClass.pathName + ".left");
             /**
              * The encoder follower does PID using encoder input to find the motor power
              */
              PathFinderClass.encoderFollowerLeft = new EncoderFollower(PathFinderClass.leftTrajectory);
              PathFinderClass.encoderFollowerRight = new EncoderFollower(PathFinderClass.rightTrajectory);
             /**
              * giving the encoderFollowers the current ticks (starting point) and the CPR
              * and wheelDiamter as we don't want it to be in ticks we want a usable
              * measurment
              */
              PathFinderClass.encoderFollowerLeft.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_LEFT.get(),
                 RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
                 PathFinderClass.encoderFollowerRight.configureEncoder(RobotComponents.DriveTrain.DRIVETRAIN_ENCODER_RIGHT.get(),
                 RobotConstants.Sensors.TicksPerRevolution, RobotConstants.RobotDimensions.WheelDiameter);
             /** setting the PID values: kp, ki, kd, kv, ka */
             PathFinderClass.encoderFollowerLeft.configurePIDVA(PathFinderClass.kP, PathFinderClass.kI, PathFinderClass.kD, 1 / PathFinderClass.maxVelocity, PathFinderClass.kA);
             PathFinderClass.encoderFollowerRight.configurePIDVA(PathFinderClass.kP, PathFinderClass.kI, PathFinderClass.kD, 1 / PathFinderClass.maxVelocity, PathFinderClass.kA);
    Notifier pathNotifier;
        pathNotifier = new Notifier(PathFinderClass::followPath);
        pathNotifier.startPeriodic(PathFinderClass.leftTrajectory.get(0).dt);
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
    if (PathFinderClass.pathNotifier != null){
      PathFinderClass.pathNotifier.stop();
      PathFinderClass.pathNotifier.close();
    }
    Robot.DriveTrain.tankDrive(0, 0);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}