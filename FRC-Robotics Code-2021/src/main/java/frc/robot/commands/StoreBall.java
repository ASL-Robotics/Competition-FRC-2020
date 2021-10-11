/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

/**
 * COMPETITION READY
 * 
 * Triggered when intake switch is pressed.
 */
public class StoreBall extends CommandBase {
  /**
   * Creates a new StorageCommand.
   */
  private Storage storage;

  public StoreBall(Storage s) {
    // Use addRequirements() here to declare subsystem dependencies.
    storage = s;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!storage.isOverridden())
      storage.startFeeding();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    storage.stopFeeding();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (storage.getTopSwitch() || storage.isOverridden());
  }
}
