package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RobotComponents {
    static class CargoCollector{
        public final static TalonSRX COLECTOR_MOTOR = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_MOTOR);
        public final static TalonSRX RIGHT_HOLDER = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR);
        public final static TalonSRX LEFT_HOLDER = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_HOLDER_LEFT_MOTOR);
        public final static DigitalInput SWITCH = new DigitalInput(RobotMap.DIO.CARGO_COLLECTOR_SWITCH);
    }

    static class CargoFolder{
        public final static DoubleSolenoid SOLENOID = new DoubleSolenoid(RobotMap.PCM0.CARGO_FOLDER_SOLENOID_A, RobotMap.PCM0.CARGO_FOLDER_SOLENOID_B);
        public final static DigitalInput TOP_SWITCH = new DigitalInput(RobotMap.DIO.CARGO_FOLDER_TOP_SWITCH);
        public final static DigitalInput BOTTOM_SWITCH = new DigitalInput(RobotMap.DIO.CARGO_FOLDER_BOTTOM_SWITCH);
    }

    static class OneEighty{
        public final static TalonSRX MOTOR = new TalonSRX(RobotMap.CAN.ONE_EIGHTY_MOTOR);
        public final static AnalogPotentiometer POT = new AnalogPotentiometer(RobotMap.AI.ONE_EIGHTY_POTENTIOMETER,
            RobotConstants.ONE_EIGHTY_POTENTIOMETER_ANGLE_MULTIPLIER, RobotConstants.ONE_EIGHTY_POTENTIOMETER_OFFSET);
    }

    static class HatchHolder{
        public final static DoubleSolenoid PVC = new DoubleSolenoid(RobotMap.PCM0.HATCH_HOLDER_PVC_SOLENOID_A, RobotMap.PCM0.HATCH_HOLDER_PVC_SOLENOID_B);
        public final static DoubleSolenoid PUSH_SOLENOID = new DoubleSolenoid(RobotMap.PCM0.HATCH_HOLDER_PUSH_SOLENOID_A, 
        RobotMap.PCM0.HATCH_HOLDER_PUSH_SOLENOID_B);
    }

    static class HatchCollector{
        public final static DoubleSolenoid SOLENOID = new DoubleSolenoid(RobotMap.PCM0.HATCH_COLLECTOR_SOLENOID_A, RobotMap.PCM0.HATCH_COLLECTOR_SOLENOID_B);
    }

    static class Lift{
        public final static TalonSRX LIFT_LEFT_M = new TalonSRX(RobotMap.CAN.LIFT_LEFT_MOTOR);
        public final static TalonSRX LIFT_RIGHT_M = new TalonSRX(RobotMap.CAN.LIFT_RIGHT_MOTOR);
        public final static DigitalInput BOTTOM_SWITCH = new DigitalInput(RobotMap.DIO.LIFT_BOTTOM_MICRO_SWITCH); 
        public final static DigitalInput TOP_SWITCH = new DigitalInput(RobotMap.DIO.LIFT_TOP_MICRO_SWITCH);
        public final static AnalogPotentiometer POT = new AnalogPotentiometer(RobotMap.AI.LIFT_POTENTIOMETER, 
        RobotConstants.Sensors.LIFT_POTENTIOMETER_SCALE_FACTOR, RobotConstants.Sensors.LIFT_POTENTOIMETER_OFFSET);
    }
}