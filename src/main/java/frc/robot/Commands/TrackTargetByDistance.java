package frc.robot.Commands;

import java.util.function.Supplier;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command turns an instance of {@link TankDrivetrain} with wpilib's
 * <a href =
 * "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDController.html">PIDController</a>
 * using the output from <a href=
 * "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>.
 * and moves it forward using {@link Supplier} to supply the movement speed to
 * the {@link TankDrivetrain#arcadeDrive}. <br />
 * This class can be used to force the instance of {@link TankDrivetrain} move
 * straight by giving its starting state as the setpoint.
 *
 * @see PIDController
 * @see TankDrivetrain
 */
public class TrackTargetByDistance extends Command {
  protected TankDrivetrain drivetrain;
  protected PIDSource rotatePIDSource;
  protected PIDSettings rotatePIDSettings;
  protected final Supplier<Double> rotateSetpointSupplier;
  protected PIDSource movementPIDSource;
  protected PIDSettings movementPIDSettings;
  protected final Supplier<Double> movementSetpointSupplier;
  protected final int INPUT_RANGE = 2;
  protected PIDController rotationController;

  /**
   * This constructs a new {@link TrackTargetByDistance} using <a href=
   * "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>,
   * {@link Supplier<Double>}s for the setpoint and the movement, and the
   * {@link PIDSettings} for this command
   * 
   * @param drivetrain      the {@link TrackTargetByDistance} this command
   *                        operates on
   * @param rotatePIDSource the <a href=
   *                        "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
   *                        that this command uses to get feedback about the
   *                        {@link TankDrivetrain}'s current state
   * @param PIDSettings     {@link PIDSettings} for this command
   */
  public TrackTargetByDistance(TankDrivetrain drivetrain, PIDSource rotatePIDSource,
      Supplier<Double> rotateSetpointSupplier, PIDSettings rotatePIDSettings, PIDSource movementPIDSource,
      Supplier<Double> movementSetpointSupplier, PIDSettings movementPIDSettings) {
    requires(drivetrain);
    this.drivetrain = drivetrain;
    this.rotatePIDSource = rotatePIDSource;
    this.rotatePIDSettings = rotatePIDSettings;
    this.rotateSetpointSupplier = rotateSetpointSupplier;
    this.movementPIDSource = movementPIDSource;
    this.movementPIDSettings = movementPIDSettings;
    this.movementSetpointSupplier = movementSetpointSupplier;
  }

  /**
   * This constructs a new {@link TrackTargetByDistance} using static values for
   * {@link TrackTargetByDistance#setpointSupplier} and
   * {@link TrackTargetByDistance#movementSupplier} instead of
   * {@link Supplier<Double>}s
   * 
   * @param drivetrain         the {@link TrackTargetByDistance} this command
   *                           operates on
   * @param rotatePIDSource          the <a href=
   *                           "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
   *                           that this command uses to get feedback about the
   *                           {@link TankDrivetrain}'s current state
   * @param rotateSetpoint           the target point of this command.
   *                           <p>
   *                           This command will try to move
   *                           {@link TankDrivetrain} to the setpoint. setpoint
   *                           should supply values using the same units as
   *                           source.
   *                           </p>
   * @param rotatePIDSettings        {@link PIDSettings} for this command
  
   */
  public TrackTargetByDistance(TankDrivetrain drivetrain, PIDSource rotatePIDSource,
  Double rotateSetpoint, PIDSettings rotatePIDSettings, PIDSource movementPIDSource,
  Double movementSetpoint, PIDSettings movementPIDSettings) {

    this(drivetrain, rotatePIDSource, () -> rotateSetpoint, rotatePIDSettings, movementPIDSource, () -> movementSetpoint, movementPIDSettings);
  }

  @Override
  protected void initialize() {
    this.rotationController = new PIDController(rotatePIDSettings.getKP(), rotatePIDSettings.getKI(), rotatePIDSettings.getKD(),
        rotatePIDSource, (rotate) -> drivetrain.arcadeDrive(movementSupplier.get(), rotate));
    rotationController.setAbsoluteTolerance(PIDSettings.getTolerance());
    rotationController.setSetpoint(setpointSupplier.get());
    rotationController.setOutputRange(-1, 1);
    rotationController.setInputRange(-inputRange / 2, inputRange / 2);
    rotationController.setContinuous(continuous);
    rotationController.enable();
  }

  @Override
  protected void execute() {
    double newSetpoint = setpointSupplier.get();
    if (rotationController.getSetpoint() != newSetpoint)
      rotationController.setSetpoint(newSetpoint);
  }

  @Override

  protected boolean isFinished() {
    return isTimedOut() || isFinishedSupplier.get();
  }

  @Override

  protected void end() {
    rotationController.disable();
    drivetrain.stop();
  }

  @Override

  protected void interrupted() {
    end();
  }
}