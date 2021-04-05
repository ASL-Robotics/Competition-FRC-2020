package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

// TestAutoCommandGroup: For testing
public class TestAutoCommandGroup extends SequentialCommandGroup {

    public TestAutoCommandGroup(Drivetrain drivetrain, Vision vision) {
        super();
        // new MoveCommand(drivetrain, 24, 0.5),
        // new TurnCommand(drivetrain, vision.getAnglesOfBlock(Constants.POWER_CELL_SIG,
        // false), 0.15));
        // new MoveCommand(drivetrain, 24, 0.15));
    }
}