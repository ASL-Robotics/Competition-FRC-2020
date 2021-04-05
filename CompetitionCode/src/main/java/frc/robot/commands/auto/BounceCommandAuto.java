package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import static java.lang.Math.sqrt;

// TestAutoCommandGroup: For testing
public class BounceCommandAuto extends SequentialCommandGroup {
    public BounceCommandAuto(Drivetrain drivetrain) {
        addCommands(
            new MoveCommand(drivetrain, 45, 0.4), //Moves out of start zone
            new TurnCommand(drivetrain, -84, 0.2), //Turns to face first star
            new MoveCommand(drivetrain, sqrt(243), 0.4), //Moves to first star
            new TurnCommand(drivetrain, -22, 0.2), //Turns with back facing peak of next bounce
            new MoveCommand(drivetrain, -sqrt(1308), 0.4), //Moves to peak of first bounce
            new TurnCommand(drivetrain, 105, 0.2), //
            new MoveCommand(drivetrain, 54, 0.4),
            new TurnCommand(drivetrain, -90, 0.2),
            new MoveCommand(drivetrain, 120, 0.4),
            new MoveCommand(drivetrain, -126, 0.4),
            new TurnCommand(drivetrain, 90, 0.2),
            new MoveCommand(drivetrain, 90, 0.4),
            new TurnCommand(drivetrain, -90, 0.2),
            new MoveCommand(drivetrain, 126, 0.4),
            new TurnCommand(drivetrain, -6, 0.1),
            new MoveCommand(drivetrain, sqrt(243), 0.5),
            new TurnCommand(drivetrain, -84, 0.2),
            new MoveCommand(drivetrain, 69, 0.5)
        );   
    }
}
