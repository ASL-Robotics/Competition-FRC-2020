/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

/**
 * COMPETITION READY
 */
public class BallTrack extends CommandBase {

  private Drivetrain drivetrain;
  private Vision vision;

  /**
   * Creates a new EnableShooterCommand.
   */
  public BallTrack(Drivetrain drivetrain, Vision vision) {
    this.drivetrain = drivetrain;
    this.vision = vision;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // gets speed for turning to largest ball.
    double speed = vision.getPIDOfBlock(Constants.Signature.POWER_CELL.value(), false);
    if (speed == -1000)
      return;

    // System.out.println("Speed: " + speed);
    drivetrain.tankDrive(speed, -speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
