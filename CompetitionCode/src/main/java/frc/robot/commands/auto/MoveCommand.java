package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

// COMMAND to move the robot by a specified amount
public class MoveCommand extends CommandBase {

    public static final double U_TO_IN = 512 / Math.PI;

    // Define variables
    private final Drivetrain drivetrain;
    private double goal; // GOAL AND START IN ENCODER UNITS
    private final double speed, start;
    private final boolean isReversed;

    public MoveCommand(Drivetrain drivetrain, double goalInInches, double speed) {

        // Intialise variables as per constructor
        this.drivetrain = drivetrain;

        this.goal = goalInInches / U_TO_IN; // INCHES TO UNITS
        this.start = drivetrain.getAverageDistance(); // IN UNITS
        goal += start;

        this.speed = speed;
        this.isReversed = goal < start;

        // Requires a drivetrain to work
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.tankDrive(speed, speed);
    }

    @Override
    public void execute() {
        System.out.println("Amount to goal (in): " + ((goal - start) * U_TO_IN));
    }

    @Override
    public boolean isFinished() {

        if (isReversed) {
            // If the average of the encoders is less than the goal, stop.
            return drivetrain.getAverageDistance() <= goal;
        } else {
            // If the average of the encoders is greater than the goal, stop.
            return drivetrain.getAverageDistance() >= goal;
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }
}