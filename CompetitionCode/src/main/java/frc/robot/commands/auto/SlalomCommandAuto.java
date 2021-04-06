package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static java.lang.Math.sqrt;
import static frc.robot.Constants.*;

// TestAutoCommandGroup: For testing
public class SlalomCommandAuto extends SequentialCommandGroup {
    public SlalomCommandAuto(Drivetrain drivetrain) {
        addCommands(
            new TurnCommand(drivetrain, -45,TURN_SPEED),//Initial turn out of base
            new MoveCommand(drivetrain, sqrt(4896),MOVE_SPEED), //Moving above the 5ft slaloms
            new TurnCommand(drivetrain, 45, TURN_SPEED), //Adjusting to straight line parallel to original start
            new MoveCommand(drivetrain, 144, MOVE_SPEED), // Moving from D4 - D8
            new TurnCommand(drivetrain, 45, TURN_SPEED), //Turning to start forming circle at D10
            new MoveCommand(drivetrain, sqrt(4212),MOVE_SPEED), // Moving below D10
            new TurnCommand(drivetrain, -90, TURN_SPEED), //  square
            new MoveCommand(drivetrain, sqrt(4212), MOVE_SPEED),
            new TurnCommand(drivetrain, -90, TURN_SPEED),
            new MoveCommand(drivetrain, sqrt(4212), MOVE_SPEED),
            
            new TurnCommand(drivetrain, -55, TURN_SPEED), // smaller angle to compensate
            new MoveCommand(drivetrain, sqrt(4212)+6, MOVE_SPEED), // move down to lower level
            new TurnCommand(drivetrain, -35, TURN_SPEED), // turn in line with the obstackes
            new MoveCommand(drivetrain, 144, MOVE_SPEED), // move across long length
            new TurnCommand(drivetrain, 45, TURN_SPEED), // line up with end point
            new MoveCommand(drivetrain, sqrt(4896),MOVE_SPEED), // move towards end
            new TurnCommand(drivetrain, -45,TURN_SPEED), // turn to angle towards base
            new MoveCommand(drivetrain, 36, MOVE_SPEED)  // drive into base
        ); 

    }
}
