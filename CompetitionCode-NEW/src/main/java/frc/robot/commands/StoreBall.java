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
  private boolean movedOn;
  private Storage storage;
  private double startTime;

  public StoreBall(Storage s) {
    // Use addRequirements() here to declare subsystem dependencies.
    storage = s;

    this.startTime = System.currentTimeMillis();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!storage.isOverridden())
      storage.startFeeding();
    movedOn = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (storage.hasBall())
      movedOn = true;

    // ignores the first 350 ms otherwise this will end immediately (ball hasnt gone
    // far enough).
    if (System.currentTimeMillis() - startTime < 350)
      movedOn = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    storage.stopFeeding();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (movedOn && storage.hasBall()) || storage.isOverridden();
  }
}
