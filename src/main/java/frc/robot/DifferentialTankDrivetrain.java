/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public class DifferentialTankDrivetrain extends TankDrivetrain{
    SpeedController leftGroup, rightGroup;
    DifferentialDrive differentialDrive;
    final double CURVATURE_THRESHOLD = 0.5;


    public DifferentialTankDrivetrain(SpeedController left, SpeedController right){
        super((output) -> left.set(output), (output) -> right.set(output));
        leftGroup = left;
        rightGroup = right;
        differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
    }

    @Override
    public void arcadeDrive(double movement, double rotation){
        differentialDrive.curvatureDrive(movement, rotation, movement <= CURVATURE_THRESHOLD);
    }
}
