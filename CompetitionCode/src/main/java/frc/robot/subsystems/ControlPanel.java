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

import java.util.function.BooleanSupplier;

public class ControlPanel extends SubsystemBase {
  // Init fields: liftPiston to lift the Control Panel mech into place, spinMotor to spin to correct colour
  // isUp is a boolean that is true when cp is in place and is false when cp is not
  // IE: when liftPiston is in off pos. then isUp is false & vice versa
  private final DoubleSolenoid liftPiston;
  private VictorSP spinMotor;
  private boolean isUp = false;

  public ControlPanel() {
    liftPiston = new DoubleSolenoid(LIFT_PISTON_1, LIFT_PISTON_2);
    spinMotor = new VictorSP(SPIN_MOTOR);
  }

  /** 
   * Lift the control panel into position
   */ 
  public void lifterOnUp() {
    liftPiston.set(DoubleSolenoid.Value.kForward);
    isUp = true;
  }

  /** 
   * Turn on the spinner
   */
  public void spinnerOn() {
    spinMotor.set(0.5);
  }
  /** 
  * Stop raising the CP
  */
  public void lifterOff() {
    liftPiston.set(DoubleSolenoid.Value.kOff);
  }

  /**
   * Turn off the spinnter
   */
  public void spinnerOff() {
    spinMotor.set(0);
  }

  /**
   * Put the CP back into rest position
   */
  public void lifterOnDown() {
    liftPiston.set(DoubleSolenoid.Value.kReverse);
    isUp = false;
  }

  // Boolean supplier: basically the same as a boolean, this converts isUp into BoolSup
  // BoolSup is for conditionalcommand.
  public BooleanSupplier controlUpSupplier = () -> isUp;


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
