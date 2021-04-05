/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {

  private Intake INTAKE;
  private long startTime;

  /**
   * Creates a new IntakeCommand.
   */
  public IntakeCommand(Intake intake) {
    addRequirements(intake);
    this.INTAKE = intake;
    this.startTime = System.currentTimeMillis();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    INTAKE.setSpeed(Constants.WHEEL_INTAKE_SPEED);
    INTAKE.deployPistons();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(System.currentTimeMillis() - startTime > 1) INTAKE.pistonOff();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    INTAKE.setSpeed(0);
    INTAKE.pistonOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
