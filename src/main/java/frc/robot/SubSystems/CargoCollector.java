package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoCollector extends Subsystem {
  private SpeedController cargoCollectorMotor;
  public CargoCollector(SpeedController cargoCollectorMotor){
    this.cargoCollectorMotor = cargoCollectorMotor;
  }
  public void setCollectorMotor(Double speed){
    this.cargoCollectorMotor.set(speed);
  }

  @Override
  public void initDefaultCommand() {

  }
}