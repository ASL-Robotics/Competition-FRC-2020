/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

/**
 * COMPETITION READY
 * 
 * Two motors in a gearbox (winch) to lift us, and one motor to initally raise the arm.
 */
public class Climber extends SubsystemBase {

    // Initialize fields
    private VictorSP hookMotor;
    private VictorSP gearBoxMotor1, gearBoxMotor2;

    public Climber() {
        hookMotor = new VictorSP(LIFTER_MOTOR);
        gearBoxMotor1 = new VictorSP(GEAR_MOTOR1);
        gearBoxMotor2 = new VictorSP(GEAR_MOTOR2);
    }

    /**
     * Sets the speed for the initial climbing hook
     * @param speed [-1.0, 1.0]
     */
    public void setHookSpeed(double speed) {
        hookMotor.set(speed);
    }

    /**
     * Sets the speed of the winch to the desired speed
     * 
     * @param speed [0.0, 1.0]
     */
    public void setWinchSpeed(double speed) {
        setGearboxSpeed(Math.max(speed, 0));
    }

    /**
     * Sets the speed of the winch motors to the desired speed
     */
    private void setGearboxSpeed(double speed) {
        gearBoxMotor1.set(speed);
        gearBoxMotor2.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

}
