/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
 * NOT TESTED
 */
public class TurnCommand extends CommandBase {

  private Drivetrain drivetrain;
  private double targetDist, speed, degrees;

  /**
   * Creates a new TurnCommand.
   */
  public TurnCommand(Drivetrain drivetrain, double degrees, double speed) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;

    this.targetDist = Drivetrain.WHEEL_TO_WHEEL_DIAMETER_INCHES * Math.PI * (degrees / 360)
        + (degrees < 0 ? drivetrain.getLeftDistance() : drivetrain.getRightDistance());

    this.speed = speed;
    this.degrees = degrees;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.tankDrive(speed, -speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isFinished = false;

    if (degrees < 0) { // left
      isFinished = drivetrain.getLeftDistance() > targetDist;
    } else { // right
      isFinished = drivetrain.getRightDistance() > targetDist;
    }

    return isFinished;
  }
}
