/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

/**
 * COMPETITION READY
 * 
 * Controls the gate opening for Shooter consumption
 */
public class Storage extends SubsystemBase {

  // FIELDS
  private final VictorSP motor;
  private final DigitalInput intakeSwitch, hasBallSwitch, overrideSwitch;
  private boolean isOverridden = false;
  private int numBalls;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR_PORT);

    intakeSwitch = new DigitalInput(STORAGE_INTAKE_SWITCH_PORT);
    hasBallSwitch = new DigitalInput(STORAGE_BALL_SWITCH_PORT);
    overrideSwitch = new DigitalInput(STORAGE_OVERRIDE_SWITCH_PORT);

    numBalls = 0;
  }

  /**
   * incrememts numBalls by 1
   */
  public void addBall(){ numBalls++; }

  /**
   * subtracts 1 from numBalls
   */
  public void loseBall(){ numBalls--; }

  /**
   * resets numBalls to 0
   */
  public void resetBalls(){ numBalls = 0; }

  /**
   * numBalls getter method
   */
  public int getNumBalls(){ return numBalls;}

  /**
   * intakeSwitchSwitch getter method
   */
  public boolean getIntakeSwitch() {
    return intakeSwitch.get();
  }

  /**
   * hasBallSwitch getter method
   */
  public boolean hasBall() {
    return hasBallSwitch.get();
  }

  /**
   * Sets the storage motor (feed wheels) to 0.3 (FEED_SPEED in constants).
   */
  public void startFeeding() {
    motor.set(FEED_SPEED);
  }

  /**
   * Sets the speed of the storage motor (feed wheels) to 0 for stopping.
   */
  public void stopFeeding() {
    motor.set(0);
  }

  /**
   * Manually override the storage system.
   */
  public void override() {
    isOverridden = true;
  }

  /**
   * Whether or not the storage system is overriden either by the switch or human
   * input.
   */
  public boolean isOverridden() {
    return isOverridden || overrideSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake Switch", intakeSwitch.get());
    SmartDashboard.putBoolean("Has Ball Switch", hasBallSwitch.get());
  }

}
