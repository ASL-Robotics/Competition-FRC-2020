package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import static java.lang.Math.sqrt;
import static frc.robot.Constants.*;

// TestAutoCommandGroup: For testing
public class BounceCommandAuto extends SequentialCommandGroup {
    public BounceCommandAuto(Drivetrain drivetrain) {
        addCommands(
            new MoveCommand(drivetrain, 45, MOVE_SPEED), //Moves out of start zone
            new TurnCommand(drivetrain, -84, TURN_SPEED), //Turns to face first star
            new MoveCommand(drivetrain, sqrt(243), MOVE_SPEED), //Moves to first star
            new TurnCommand(drivetrain, -22, TURN_SPEED), //Turns with back facing peak of next bounce
            new MoveCommand(drivetrain, -sqrt(1308), MOVE_SPEED), //Moves to peak of first bounce
            new TurnCommand(drivetrain, 105, TURN_SPEED), // Turns at peak
            new MoveCommand(drivetrain, 54, MOVE_SPEED), //  
            new TurnCommand(drivetrain, -90, TURN_SPEED),
            new MoveCommand(drivetrain, 120, MOVE_SPEED),
            new MoveCommand(drivetrain, -126, MOVE_SPEED),
            new TurnCommand(drivetrain, 90, TURN_SPEED),
            new MoveCommand(drivetrain, 90, MOVE_SPEED),
            new TurnCommand(drivetrain, -90, TURN_SPEED),
            new MoveCommand(drivetrain, 126, MOVE_SPEED),
            new TurnCommand(drivetrain, -6, TURN_SPEED),
            new MoveCommand(drivetrain, sqrt(243), MOVE_SPEED),
            new TurnCommand(drivetrain, -84, TURN_SPEED),
            new MoveCommand(drivetrain, 69, MOVE_SPEED)
        );   
    }
}
