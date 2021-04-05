package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static java.lang.Math.sqrt;

// TestAutoCommandGroup: For testing
public class SlalomCommandAuto extends SequentialCommandGroup {
    public SlalomCommandAuto(Drivetrain drivetrain) {
        addCommands(
            new TurnCommand(drivetrain, -45, 0.4),//Initial turn out of base
            new MoveCommand(drivetrain, sqrt(4896), 0.8), //Moving above the 5ft slaloms
            new TurnCommand(drivetrain, 45, 0.4), //Adjusting to straight line parallel to original start
            new MoveCommand(drivetrain, 144, 0.9), // Moving from D4 - D8
            new TurnCommand(drivetrain, 45, 0.4), //Turning to start forming circle at D10
            new MoveCommand(drivetrain, sqrt(4212),0.8), // Moving below D10
            new TurnCommand(drivetrain, -90, 0.4), //  square
            new MoveCommand(drivetrain, sqrt(4212), 0.4),
            new TurnCommand(drivetrain, -90, 0.4),
            new MoveCommand(drivetrain, sqrt(4212), 0.4),
            
            new TurnCommand(drivetrain, -55, 0.4), // smaller angle to compensate
            new MoveCommand(drivetrain, sqrt(4212)+6, 0.8), // move down to lower level
            new TurnCommand(drivetrain, -35, 0.4), // turn in line with the obstackes
            new MoveCommand(drivetrain, 144, 0.9), // move across long length
            new TurnCommand(drivetrain, 45, 0.5), // line up with end point
            new MoveCommand(drivetrain, sqrt(4896),0.8), // move towards end
            new TurnCommand(drivetrain, -45, 0.4), // turn to angle towards base
            new MoveCommand(drivetrain, 36, 0.4)  // drive into base
        ); 

    }
}
