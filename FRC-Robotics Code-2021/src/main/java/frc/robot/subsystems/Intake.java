/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

/**
 * COMPETITION READY
 * 
 * Our intake class, which consists of a roller and a piston to deploy it.
 */
public class Intake extends SubsystemBase {

  private VictorSP wheelMotor;
  private DoubleSolenoid intakePiston;

  public Intake() {
    wheelMotor = new VictorSP(WHEEL_INTAKE_PORT);
    intakePiston = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
  }

  // MOTORS

  /**
   * Sets the speed of the motor.
   * 
   * @param speed [-1.0, 1.0]
   */
  public void setSpeed(double speed) {
    wheelMotor.set(speed);
  }

  // PISTONS

  /**
   * Deploy the intake (and inherently the pistons).
   * 
   * Make sure you run {@link #pistonOff() pistonOff()} to turn off the pistons.
   */
  public void deployPistons() {
    intakePiston.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Raise the intake (and pistons)
   * 
   * Make sure you run {@link #pistonOff() pistonOff()} to turn off the pistons.
   */
  public void retractPistons() {
    intakePiston.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Disables the pistons.
   */
  public void pistonOff() {
    intakePiston.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
