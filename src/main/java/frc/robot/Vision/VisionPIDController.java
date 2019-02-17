package frc.robot.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import frc.robot.Vision.VisionPIDSource;

public class VisionPIDController extends PIDController{
    //overrides the pidInput to be VisionPIDSource type

    public VisionPIDController(double Kp, double Ki, double Kd, VisionPIDSource source, PIDOutput output) {
        super(Kp, Ki, Kd, source, output, kDefaultPeriod);
    }

    public VisionPIDController(double Kp, double Ki, double Kd, VisionPIDSource source, PIDOutput output,
            double period) {
        super(Kp, Ki, Kd, source, output, period);
    }


    /**
     * calculates only if the direction has been found. If the direction is not found it
     * will write to the pidOutput 9999.
     */
    @Override
    protected void calculate() {
        boolean isUpdated;
        //checks if the direction is found
        this.m_thisMutex.lock();
        try {
            isUpdated = m_pidInput.pidGet()!=9999;
        } finally {
            this.m_thisMutex.unlock();
        }
        if (isUpdated)
            //the direction was found
            super.calculate();
        else {
            //the direction wasn't found
            //this code snippet was taken from PIDbase(original calculate() code)
            m_pidWriteMutex.lock();
            try {
              m_thisMutex.lock();
              try {
                if (m_enabled) {
                  // Don't block other PIDController operations on pidWrite()
                  m_thisMutex.unlock();
                  m_pidOutput.pidWrite(9999);
                }
              } finally {
                if (m_thisMutex.isHeldByCurrentThread()) {
                  m_thisMutex.unlock();
                }
              }
            } finally {
              m_pidWriteMutex.unlock();
            }
        }
    }
}