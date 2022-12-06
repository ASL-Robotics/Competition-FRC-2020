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
  private final DigitalInput topSwitch;
  private boolean isOverridden = false;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR_PORT);

    topSwitch = new DigitalInput(STORAGE_TOP_SWITCH_PORT);
  }

  /**
   * intakeSwitchSwitch getter method
   */
  public boolean getTopSwitch() {
    return topSwitch.get();
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
    return (isOverridden || topSwitch.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Top Switch", topSwitch.get());
  }

}
