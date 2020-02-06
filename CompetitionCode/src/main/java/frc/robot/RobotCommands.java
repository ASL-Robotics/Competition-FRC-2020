 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

/**
 * For robot commands because RobotContainer is frighteningly messy
 */
public class RobotCommands{
 
    public final ColorSensor COLOR_SENSOR = new ColorSensor();
    public final ControlPanel CONTROL_PANEL = new ControlPanel();


 
 // CONTROL PANEL COMMANDS
 public final StartEndCommand controlLift = new StartEndCommand(
    () -> CONTROL_PANEL.lifterOnUp(),
    () -> CONTROL_PANEL.lifterOff(),
    CONTROL_PANEL
);
public final StartEndCommand controlDrop = new StartEndCommand(
    () -> CONTROL_PANEL.lifterOnDown(),
    () -> CONTROL_PANEL.lifterOff(),
    CONTROL_PANEL
);
public final StartEndCommand controlSpin = new StartEndCommand(
    () -> CONTROL_PANEL.spinnerOn(),
    () -> CONTROL_PANEL.spinnerOff(),
    CONTROL_PANEL
);

public final InstantCommand controlStop = new InstantCommand(
    () -> CONTROL_PANEL.spinnerOff(),
    CONTROL_PANEL
);

// SECOND LEVEL CONTROL COMMANDS
public final ConditionalCommand liftControlMaybe = new ConditionalCommand(
    controlLift.withTimeout(3), controlDrop.withTimeout(3).andThen(), 
    CONTROL_PANEL.controlUpSupplier
);


// COLOR SENSOR (CONTROL PANEL) SECOND LEVEL COMMANDS
public final ConditionalCommand controlSpinIfNoMatch = new ConditionalCommand(
    controlStop,
    controlSpin,
    COLOR_SENSOR.colorMatchSupplier
);

public final InstantCommand spinHalfWhenEquals = new InstantCommand(
    () -> controlSpin.withInterrupt(COLOR_SENSOR.colorMatchSupplierReverse),
    COLOR_SENSOR
);

public final InstantCommand spinHalfWhenDoesNot = new InstantCommand(
    () -> controlSpin.withInterrupt(COLOR_SENSOR.colorMatchSupplier).andThen(spinHalfWhenEquals),
    COLOR_SENSOR
);

public final ConditionalCommand spinHalfEither = new ConditionalCommand(
    spinHalfWhenEquals,
    spinHalfWhenDoesNot,
    COLOR_SENSOR.colorMatchSupplier
);

public final SequentialCommandGroup spinHalfEitherEight = new SequentialCommandGroup(
    spinHalfEither.andThen(spinHalfEither).andThen(spinHalfEither).andThen(spinHalfEither).andThen(spinHalfEither).andThen(spinHalfEither).andThen(spinHalfEither).andThen(spinHalfEither)    );

public final InstantCommand plusTimesSpun = new InstantCommand(
    () -> COLOR_SENSOR.timesSpunIncrease(),
    COLOR_SENSOR
);

}