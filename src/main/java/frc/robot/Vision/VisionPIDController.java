package frc.robot.Vision;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import frc.robot.Vision.VisionPIDSource;

public class VisionPIDController extends PIDController implements Controller {
    Notifier m_controlLoop = new Notifier(this::calculate);
    VisionPIDSource m_pidInput;

    public VisionPIDController(double Kp, double Ki, double Kd, VisionPIDSource source, PIDOutput output) {
        this(Kp, Ki, Kd, source, output, kDefaultPeriod);
    }

    public VisionPIDController(double Kp, double Ki, double Kd, VisionPIDSource source, PIDOutput output,
            double period) {
        this(Kp, Ki, Kd, 0.0, source, output, period);
    }

    public VisionPIDController(double Kp, double Ki, double Kd, double Kf, VisionPIDSource source, PIDOutput output,
            double period) {
        super(Kp, Ki, Kd, Kf, source, output);
        m_pidInput = source;
    }

    public VisionPIDController(double Kp, double Ki, double Kd, double Kf, VisionPIDSource source, PIDOutput output) {
        this(Kp, Ki, Kd, Kf, source, output, kDefaultPeriod);
    }

    /**
     * calculates only if the direction has been found. If the direction is not found it
     * will write to the pidOutput 9999.
     */
    @Override
    protected void calculate() {
        boolean isUpdated;
        this.m_thisMutex.lock();
        try {
            isUpdated = m_pidInput.isUpdated();
        } finally {
            this.m_thisMutex.unlock();
        }
        if (isUpdated)
            super.calculate();
        else {
            //the direction wasn't found
            this.m_thisMutex.lock();
            try {
                m_pidOutput.pidWrite(9999);
            } finally {
                this.m_thisMutex.unlock();
            }
        }
    }
}