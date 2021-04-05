/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

/**
 * COMPETITION READY
 */
public class ShootCommand extends CommandBase {

  private Shooter shooter;
  private Storage storage;
  private double targetRPM;

  private long startTime;
  private boolean isClose;

  /**
   * Creates a new ShootCommand.
   */
  public ShootCommand(Shooter shooter, Storage storage, int targetRPM, boolean isClose) {
    this.shooter = shooter;
    this.storage = storage;
    this.targetRPM = targetRPM;
    addRequirements(shooter, storage);

    this.isClose = isClose;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(isClose) shooter.setAngleBack();
    else shooter.setAngleForward();
    storage.resetBalls();
    this.startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // shooter.setSpeedWithRPM(targetRPM); // Sets target RPM (must be called each frame to update)

    shooter.setSpeed(targetRPM / 6500f);
    // Output stats
    SmartDashboard.putNumber("Target RPM", targetRPM);
    SmartDashboard.putNumber("Current RPM", shooter.getMotorSpeed());

    // After 3 seconds, begin shooting.
    // This is because generally it takes 3 seconds to be safe to shoot.
    if (System.currentTimeMillis() - startTime > Constants.SHOOTER_REV_TIME) {
      storage.startFeeding();
    }

    // turn pistons off
    System.out.println(System.currentTimeMillis() - startTime > 1000);
    if (System.currentTimeMillis() - startTime > 1000) {
      shooter.setPistonsOff();
      System.out.println("Disabled Shooter pistons.");
    }

    // TODO: de-incremembing balls

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    storage.stopFeeding();
    System.out.println("finished shoot");
    shooter.setPistonsOff();
    shooter.setSystemSpeed(0);
    shooter.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
