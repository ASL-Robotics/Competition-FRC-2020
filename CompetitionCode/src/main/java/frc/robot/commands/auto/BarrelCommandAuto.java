package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import static frc.robot.Constants.*;
// TestAutoCommandGroup: For testing
public class BarrelCommandAuto extends SequentialCommandGroup {

    public BarrelCommandAuto(Drivetrain drivetrain) {
        addCommands
        (
            new MoveCommand(drivetrain, 150, MOVE_SPEED), //Move in position to turn for our first obstacle
            new TurnCommand(drivetrain, 135, TURN_SPEED), //Turn so that we are at a perfectly diagonal slope                                         
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED), //Here we move across two sides of the "square" that we created around the first obstacle
            new TurnCommand(drivetrain, 90, TURN_SPEED),
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED),
            new TurnCommand(drivetrain, 90, TURN_SPEED),
              
            new MoveCommand(drivetrain, Math.sqrt(150),  MOVE_SPEED),//Move to the final turn of the square
            new TurnCommand(drivetrain, 59, TURN_SPEED), //Turn to position ourselves for the second obstacle
            new MoveCommand(drivetrain, (Math.sqrt(1275)+Math.sqrt(150)),  MOVE_SPEED), //Move all the way to the start of the second obstacle

            new TurnCommand(drivetrain, 90, TURN_SPEED), //Here we move across the two sides of the second "square" that we created around the second obstacle
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED),
            new TurnCommand(drivetrain, 90, TURN_SPEED),
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED),

            new TurnCommand(drivetrain, 90, TURN_SPEED), //Turn to position the robot to move to the third obstacle
            new MoveCommand(drivetrain, (90 * Math.sqrt(2)), MOVE_SPEED), //Move all the way to start the third obstacle

            new TurnCommand(drivetrain, 90, TURN_SPEED), //Here we move across the two sides of the third "square" that we created around the third obstacle
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED),
            new TurnCommand(drivetrain, 90, TURN_SPEED),
            new MoveCommand(drivetrain, Math.sqrt(150), MOVE_SPEED), 

            new TurnCommand(drivetrain, -135, TURN_SPEED), //Here we turn to position ourselves so that we can drive to the finish zone
            new MoveCommand(drivetrain, 300, MOVE_SPEED) //Here we drive to the finish zone and end this auto
        );
        
    }
}
