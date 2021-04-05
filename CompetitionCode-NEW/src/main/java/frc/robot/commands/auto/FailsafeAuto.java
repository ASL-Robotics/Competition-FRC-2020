/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.DisableShooter;
import frc.robot.commands.EnableShooter;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

/**
 * NOT TESTED
 */
public class FailsafeAuto extends SequentialCommandGroup {
  /**
   * Creates a new FailsafeAuto.
   */
  public FailsafeAuto(Drivetrain drivetrain, Shooter shooter, Storage storage) {
    // START SHOOTER AND SET TO WALL \/
    // MOVE FORWARD 120 INCHES \/
    // shoot \/
    // party

    super(
      new EnableShooter(shooter, storage),
      new MoveCommand(drivetrain, 120, 0.65),
      new ShootCommand(shooter, storage, 5500, true).withTimeout(3 + 3 + 2), // 3 to spool, 1 per ball, 2 for safety
      new DisableShooter(shooter)
    );
  }
}
