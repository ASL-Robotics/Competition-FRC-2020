/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.*;
import frc.robot.commands.DisableShooter;
import frc.robot.commands.EnableShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Vision;

import static frc.robot.Constants.*;

/**
 * NOT TESTED
 */
public class TrenchAuto extends SequentialCommandGroup {
  /**
   * Creates a new TrenchAuto.
   */
  public TrenchAuto(Drivetrain drivetrain, Shooter shooter, Storage storage, Intake intake, Vision vision) {

    // move forward 160 inches and intake
    // enable shooter
    // turn 165 right
    // shoot
    // party

    super(
      new MoveCommand(drivetrain, 160, 0.75).alongWith(
        new RunCommand(() -> {
        intake.setSpeed(WHEEL_INTAKE_SPEED);
        intake.deployPistons();
        }, intake)
    ), 
    new EnableShooter(shooter, storage), 
    new TurnCommand(drivetrain, 165, 0.75),
    new GoalTrack(drivetrain, vision).withTimeout(3),
    new ShootCommand(shooter, storage, 5500, false).withTimeout(3 + 5),
    new DisableShooter(shooter));
  }
}
