package frc.robot;

import java.util.function.Consumer;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/** this class sets what all of the robot components are */
public class RobotComponents {
    static class CargoCollector {
        public static final TalonSRX COLECTOR_MOTOR = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_MOTOR);
        public static final TalonSRX RIGHT_HOLDER = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_HOLDER_RIGHT_MOTOR);
        public static final TalonSRX LEFT_HOLDER = new TalonSRX(RobotMap.CAN.CARGO_COLLECTOR_HOLDER_LEFT_MOTOR);
        public static final DigitalInput SWITCH = new DigitalInput(RobotMap.DIO.CARGO_COLLECTOR_SWITCH);
    }

    static class CargoFolder{
        public static final DoubleSolenoid SOLENOID = new DoubleSolenoid(RobotMap.CAN.PCM, RobotMap.PCM0.CARGO_FOLDER_SOLENOID_A, RobotMap.PCM0.CARGO_FOLDER_SOLENOID_B);
        public static final DigitalInput TOP_SWITCH = new DigitalInput(RobotMap.DIO.CARGO_FOLDER_TOP_SWITCH);
        public static final DigitalInput BOTTOM_SWITCH = new DigitalInput(RobotMap.DIO.CARGO_FOLDER_BOTTOM_SWITCH);
    }

    static class OneEighty {
        public static final TalonSRX MOTOR = new TalonSRX(RobotMap.CAN.ONE_EIGHTY_MOTOR);
        public static final AnalogPotentiometer POT = new AnalogPotentiometer(
                RobotMap.ANALOG_INPUT.ONE_EIGHTY_POTENTIOMETER,
                RobotConstants.ONE_EIGHTY_POTENTIOMETER_ANGLE_MULTIPLIER,
                RobotConstants.ONE_EIGHTY_POTENTIOMETER_OFFSET);
    }

    static class HatchHolder{
        public static final DoubleSolenoid PVC = new DoubleSolenoid(RobotMap.CAN.PCM, RobotMap.PCM0.HATCH_HOLDER_PVC_SOLENOID_A, RobotMap.PCM0.HATCH_HOLDER_PVC_SOLENOID_B);
        public static final DoubleSolenoid PUSH_SOLENOID = new DoubleSolenoid(RobotMap.CAN.PCM, RobotMap.PCM0.HATCH_HOLDER_PUSH_SOLENOID_A, 
        RobotMap.PCM0.HATCH_HOLDER_PUSH_SOLENOID_B);
    }

    static class HatchCollector{
        public static final DoubleSolenoid SOLENOID = new DoubleSolenoid(RobotMap.CAN.PCM, RobotMap.PCM0.HATCH_COLLECTOR_SOLENOID_A, RobotMap.PCM0.HATCH_COLLECTOR_SOLENOID_B);

    }

    static class Lift {
        public static final TalonSRX LIFT_LEFT_M = new TalonSRX(RobotMap.CAN.LIFT_LEFT_MOTOR);
        public static final TalonSRX LIFT_RIGHT_M = new TalonSRX(RobotMap.CAN.LIFT_RIGHT_MOTOR);
        public static final DigitalInput BOTTOM_SWITCH = new DigitalInput(RobotMap.DIO.LIFT_BOTTOM_MICRO_SWITCH);
        public static final DigitalInput TOP_SWITCH = new DigitalInput(RobotMap.DIO.LIFT_TOP_MICRO_SWITCH);
        public static final AnalogPotentiometer POT = new AnalogPotentiometer(RobotMap.ANALOG_INPUT.LIFT_POTENTIOMETER,
                RobotConstants.Sensors.LIFT_POTENTIOMETER_SCALE_FACTOR,
                RobotConstants.Sensors.LIFT_POTENTOIMETER_OFFSET);
    }
    
    static class DriveTrain{
        public static final TalonSRX REAR_LEFT_M = new TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
        public static final TalonSRX FRONT_LEFT_M = new TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
        public static final TalonSRX REAR_RIGHT_M = new TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
        public static final TalonSRX FRONT_RIGHT_M = new TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);
        public static final ADXRS450_Gyro GYRO = new ADXRS450_Gyro();
    }    
}