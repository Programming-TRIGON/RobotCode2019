package frc.robot.Autonomous;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.PIDSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Commands.ReachOneEightyAngle;
import frc.robot.Commands.StabilizeOneEightyAngle;

public class TestPID extends Command {
  Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", 0.01);
  Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 0);
  Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 0.00);
  Supplier<Double> tolerance = ConstantHandler.addConstantDouble("tolerance", 0.1);
  Supplier<Double> WAIT_TIME = ConstantHandler.addConstantDouble("WAIT_TIME", 1);
  double movmentPidOutput;
  Supplier<Double> movmentSupplier = () -> movmentPidOutput; 

  PIDController movmentPidController;
  Supplier<Double> Setpoint = ConstantHandler.addConstantDouble("Setpoint", 0);

  PIDSettings pidSettings;
  Command command;

  public TestPID() {
  }

  @Override

  protected void initialize() {
    updatePID();
    double angle=-2;
    if(Robot.oneEighty.getAngle()<90)
      angle=208;
    command = new ReachOneEightyAngle(angle);
    command.start();
    command = new StabilizeOneEightyAngle(angle, pidSettings);
    command.start();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  public void updatePID(){

    this.pidSettings = new PIDSettings(KP.get(), KI.get(), KD.get(), tolerance.get(), WAIT_TIME.get());
    SmartDashboard.putString("PID setting", "" + KP.get() + KI.get() + KD.get() + tolerance.get() + WAIT_TIME.get());
  }

  @Override
  protected void interrupted() {
    
  }
}

