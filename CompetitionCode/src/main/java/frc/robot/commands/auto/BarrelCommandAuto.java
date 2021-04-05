package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

// TestAutoCommandGroup: For testing
public class BarrelCommandAuto extends SequentialCommandGroup {

    public BarrelCommandAuto(Drivetrain drivetrain) {
        addCommands
        (
            new MoveCommand(drivetrain, 150, 0.8), //Move in position to turn for our first obstacle
            new TurnCommand(drivetrain, 135, 0.4), //Turn so that we are at a perfectly diagonal slope                                         
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6), //Here we move across two sides of the "square" that we created around the first obstacle
            new TurnCommand(drivetrain, 90, 0.4),
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6),
            new TurnCommand(drivetrain, 90, 0.4),
              
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6),//Move to the final turn of the square
            new TurnCommand(drivetrain, 59, 0.4), //Turn to position ourselves for the second obstacle
            new MoveCommand(drivetrain, (Math.sqrt(1275)+Math.sqrt(150)), 0.6), //Move all the way to the start of the second obstacle

            new TurnCommand(drivetrain, 90, 0.4), //Here we move across the two sides of the second "square" that we created around the second obstacle
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6),
            new TurnCommand(drivetrain, 90, 0.4),
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6),

            new TurnCommand(drivetrain, 90, 0.4), //Turn to position the robot to move to the third obstacle
            new MoveCommand(drivetrain, (90 * Math.sqrt(2)), 0.6), //Move all the way to start the third obstacle

            new TurnCommand(drivetrain, 90, 0.4), //Here we move across the two sides of the third "square" that we created around the third obstacle
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6),
            new TurnCommand(drivetrain, 90, 0.4),
            new MoveCommand(drivetrain, Math.sqrt(150), 0.6), 

            new TurnCommand(drivetrain, -135, 0.4), //Here we turn to position ourselves so that we can drive to the finish zone
            new MoveCommand(drivetrain, 300, 0.6) //Here we drive to the finish zone and end this auto
        );
        
    }
}