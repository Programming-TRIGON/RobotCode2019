package frc.robot.DrivingCommands;

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
 * @author Simon "C" Kharmatsky
 */
public class DriveArcadeWithPID extends Command {

	protected TankDrivetrain drivetrain;
	protected PIDSource PIDSource;
	protected PIDSettings PIDSettings;
	protected final Supplier<Double> setpointSupplier;
	protected final Supplier<Double> movementSupplier;
	protected Supplier<Boolean> isFinishedSupplier;

	protected double inputRange;
	protected boolean continuous;

	protected PIDController rotationController;

	/**
	 * This constructs a new {@link DriveArcadeWithPID} using <a href=
	 * "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>,
	 * {@link Supplier<Double>}s for the setpoint and the movement, and the
	 * {@link PIDSettings} for this command
	 * 
	 * @param drivetrain         the {@link DriveArcadeWithPID} this command
	 *                           operates on
	 * @param PIDSource          the <a href=
	 *                           "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
	 *                           that this command uses to get feedback about the
	 *                           {@link TankDrivetrain}'s current state
	 * @param setpointSupplier   a supplier supplying the target point of this
	 *                           command.
	 *                           <p>
	 *                           This command will try to move
	 *                           {@link TankDrivetrain} to the latest value supplied
	 *                           by setpoint. setpoint should supply values using
	 *                           the same units as source.
	 *                           </p>
	 * @param movementSupplier   {@link Supplier<Double>} supplier of the movement
	 *                           for {@link TankDrivetrain#arcadeDrive}
	 * @param isFinishedSupplier a condition upon returning true stops this command
	 * @param PIDSettings        {@link PIDSettings} for this command
	 * @param inputRange         the range of the source's input. For example,
	 *                           gyro's is 360. Camera that has 640 px on the wanted
	 *                           axis has output range of 640, and one that its
	 *                           values range was scaled between -1 and 1 has output
	 *                           range of 2 and so on.
	 * @param continuous         true to make the PID controller consider the input
	 *                           to be continuous, Rather then using the max and min
	 *                           input range as constraints, it considers them to be
	 *                           the same point and automatically calculates the
	 *                           shortest route to the setpoint.
	 */
	public DriveArcadeWithPID(TankDrivetrain drivetrain, PIDSource PIDSource, Supplier<Double> setpointSupplier,
			Supplier<Double> movementSupplier, Supplier<Boolean> isFinishedSupplier, PIDSettings PIDSettings,
			double inputRange, boolean continuous) {
		requires(drivetrain);
		this.drivetrain = drivetrain;
		this.PIDSource = PIDSource;
		this.PIDSettings = PIDSettings;
		this.setpointSupplier = setpointSupplier;
		this.movementSupplier = movementSupplier;
		this.isFinishedSupplier = isFinishedSupplier;
		this.inputRange = inputRange;
		this.continuous = continuous;
	}

	/**
	 * This constructs a new {@link DriveArcadeWithPID} using static values for
	 * {@link DriveArcadeWithPID#setpointSupplier} and
	 * {@link DriveArcadeWithPID#movementSupplier} instead of
	 * {@link Supplier<Double>}s
	 * 
	 * @param drivetrain         the {@link DriveArcadeWithPID} this command
	 *                           operates on
	 * @param PIDSource          the <a href=
	 *                           "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
	 *                           that this command uses to get feedback about the
	 *                           {@link TankDrivetrain}'s current state
	 * @param setpoint           the target point of this command.
	 *                           <p>
	 *                           This command will try to move
	 *                           {@link TankDrivetrain} to the setpoint. setpoint
	 *                           should supply values using the same units as
	 *                           source.
	 *                           </p>
	 * @param movement           static value for
	 *                           {@link DriveArcadeWithPID#movementSupplier}
	 * @param isFinishedSupplier a condition upon returning true stops this command
	 * @param PIDSettings        {@link PIDSettings} for this command
	 * @param inputRange         the range of the source's input. For example,
	 *                           gyro's is 360. Camera that has 640 px on the wanted
	 *                           axis has output range of 640, and one that its
	 *                           values range was scaled between -1 and 1 has output
	 *                           range of 2 and so on.
	 * @param continuous         true to make the PID controller consider the input
	 *                           to be continuous, Rather then using the max and min
	 *                           input range as constraints, it considers them to be
	 *                           the same point and automatically calculates the
	 *                           shortest route to the setpoint.
	 */
	public DriveArcadeWithPID(TankDrivetrain drivetrain, PIDSource PIDSource, double setpoint, double movement,
			Supplier<Boolean> isFinishedSupplier, PIDSettings PIDSettings, double inputRange, boolean continuous) {
		this(drivetrain, PIDSource, () -> setpoint, () -> movement, isFinishedSupplier, PIDSettings, inputRange,
				continuous);
	}

	/**
	 * This constructs a new {@link DriveArcadeWithPID} ignoring the
	 * {@link DriveArcadeWithPID#isFinishedSupplier}
	 * 
	 * @param drivetrain       the {@link DriveArcadeWithPID} this command operates
	 *                         on
	 * @param PIDSource        the <a href=
	 *                         "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
	 *                         that this command uses to get feedback about the
	 *                         {@link TankDrivetrain}'s current state
	 * @param setpointSupplier a supplier supplying the target point of this
	 *                         command.
	 *                         <p>
	 *                         This command will try to move {@link TankDrivetrain}
	 *                         to the latest value supplied by setpoint. setpoint
	 *                         should supply values using the same units as source.
	 *                         </p>
	 * @param movementSupplier supplier of the movement for
	 *                         {@link TankDrivetrain#arcadeDrive}
	 * @param PIDSettings      {@link PIDSettings} for this command
	 * @param inputRange       the range of the source's input. For example, gyro's
	 *                         is 360. Camera that has 640 px on the wanted axis has
	 *                         output range of 640, and one that its values range
	 *                         was scaled between -1 and 1 has output range of 2 and
	 *                         so on.
	 * @param continuous       true to make the PID controller consider the input to
	 *                         be continuous, Rather then using the max and min
	 *                         input range as constraints, it considers them to be
	 *                         the same point and automatically calculates the
	 *                         shortest route to the setpoint.
	 */
	public DriveArcadeWithPID(TankDrivetrain drivetrain, PIDSource PIDSource, Supplier<Double> setpointSupplier,
			Supplier<Double> movementSupplier, PIDSettings PIDSettings, double inputRange, boolean continuous) {
		this(drivetrain, PIDSource, setpointSupplier, movementSupplier, () -> false, PIDSettings, inputRange,
				continuous);

	}

	/**
	 * This constructs a new {@link DriveArcadeWithPID} ignoring the
	 * {@link DriveArcadeWithPID#isFinishedSupplier} and uses constant values for
	 * {@link DriveArcadeWithPID#setpointSupplier} and
	 * {@link DriveArcadeWithPID#movementSupplier}
	 * 
	 * @param drivetrain  the {@link DriveArcadeWithPID} this command operates on
	 * @param PIDSource   the <a href=
	 *                    "http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PIDSource.html">PIDSources</a>
	 *                    that this command uses to get feedback about the
	 *                    {@link DriveArcadeWithPID#drivetrain}'s current state
	 * @param setpoint    the target point of this command.
	 *                    <p>
	 *                    This command will try to move {@link TankDrivetrain} to
	 *                    the setpoint. setpoint should supply values in raw sensor
	 *                    units
	 *                    </p>
	 * @param movement    constant value for
	 *                    {@link DriveArcadeWithPID#movementSupplier}
	 * @param PIDSettings {@link PIDSettings} for this command
	 * @param inputRange  the range of the source's input. For example, gyro's is
	 *                    360. Camera that has 640 px on the wanted axis has output
	 *                    range of 640, and one that its values range was scaled
	 *                    between -1 and 1 has output range of 2 and so on.
	 * @param continuous  true to make the PID controller consider the input to be
	 *                    continuous, Rather then using the max and min input range
	 *                    as constraints, it considers them to be the same point and
	 *                    automatically calculates the shortest route to the
	 *                    setpoint.
	 */
	public DriveArcadeWithPID(TankDrivetrain drivetrain, PIDSource PIDSource, double setpoint, double movement,
			PIDSettings PIDSettings, double inputRange, boolean continuous) {
		this(drivetrain, PIDSource, () -> setpoint, () -> movement, PIDSettings, inputRange, continuous);
	}

	@Override
	protected void initialize() {
		this.rotationController = new PIDController(PIDSettings.getKP(), PIDSettings.getKI(), PIDSettings.getKD(),
				PIDSource, (rotate) -> drivetrain.arcadeDrive(movementSupplier.get(), rotate));
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
