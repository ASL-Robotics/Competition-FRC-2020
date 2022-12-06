/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;

/**
 * COMPETITION READY
 */
public class EnableShooter extends ParallelCommandGroup {
  
  public EnableShooter(Shooter shooter, Storage storage) {
    super(
      new InstantCommand(() -> { shooter.setSystemSpeed(0.5); }, shooter),
      new ReadyBalls(storage)
    );
  }
}
