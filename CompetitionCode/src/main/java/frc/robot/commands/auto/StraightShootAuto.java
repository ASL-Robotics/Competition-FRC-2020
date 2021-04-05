/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.EnableShooter;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.*;

public class StraightShootAuto extends SequentialCommandGroup {

    /**
     * NOT TESTED
     */
    public StraightShootAuto(Drivetrain drivetrain, Vision vision, Shooter shooter, Storage storage) {
        super(
                // Shoots from starting line then goes backwards
                parallel(new EnableShooter(shooter, storage), new MoveCommand(drivetrain, -20, 0.5)),
                new ShootCommand(shooter, storage, 5500, false).withTimeout(3 + 3 + 2));
    }
}
