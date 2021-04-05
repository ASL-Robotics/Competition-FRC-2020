
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;
import frc.robot.triggers.*;

import static frc.robot.Constants.*;

public class RobotContainer {

  // JOYSTICKS
  public final Joystick driver = new Joystick(DRIVER_CONTROLLER);
  public final Joystick operator = new Joystick(OPERATOR_CONTROLLER);

  // == BUTTONS == //

  // Drivetrain
  public final JoystickButton modeSwitchButton = new JoystickButton(driver, RIGHT_BUMPER); // stray kids going FAST or SLOW

  // Vision
  public final JoystickButton visionAlignBall = new JoystickButton(driver, BUTTON_A);
  public final JoystickButton visionAlignGoal = new JoystickButton(driver, BUTTON_Y);

  // Intake
  public final JoystickButton intakeButton = new JoystickButton(operator, BUTTON_A),
      outttakeButton = new JoystickButton(operator, BUTTON_Y),
      raiseIntakeButton = new JoystickButton(operator, BUTTON_X);

  // Storage
  public final JoystickButton storageOverrideButton = new JoystickButton(operator, START_BUTTON);

  // Shooter
  public JoystickButton toggleShooterButton = new JoystickButton(operator, RIGHT_BUMPER);

  public JoystickTrigger shootCloseTrigger = new JoystickTrigger(operator, LEFT_TRIGGER_AXIS);
  public JoystickTrigger shootFarTrigger = new JoystickTrigger(operator, RIGHT_TRIGGER_AXIS);

  // SUBSYSTEMS
  public final Drivetrain DRIVETRAIN = new Drivetrain(driver);
  public final Intake INTAKE = new Intake();
  public final Storage STORAGE = new Storage();
  public final Shooter SHOOTER = new Shooter();
  public final Vision VISION = new Vision();
  public final Climber CLIMBER = new Climber();

  // COMMANDS

  // Shooter

  public final ShootCommand shootClose = new ShootCommand(SHOOTER, STORAGE, SHORT_DISTANCE_RPM, true);
  public final ShootCommand shootFar = new ShootCommand(SHOOTER, STORAGE, LONG_DIST_RPM, false);

  // Drivetrain
  public final StartEndCommand modeSwitch = new StartEndCommand(() -> DRIVETRAIN.modeSlow(),
      () -> DRIVETRAIN.modeFast(), DRIVETRAIN);

  // Intake

  public final StartEndCommand retractIntake = new StartEndCommand(() -> {
    INTAKE.retractPistons();
  }, () -> {
    INTAKE.pistonOff();
  }, INTAKE);

  public final StartEndCommand outtakeCommand = new StartEndCommand(() -> INTAKE.setSpeed(-WHEEL_INTAKE_SPEED),
      () -> INTAKE.setSpeed(0), INTAKE);

  // for storage trigger
  public boolean shouldStorageIntake() {
    return (!STORAGE.getTopSwitch() && !STORAGE.isOverridden());
  }

  // Storage
  public final StartEndCommand storageOverride = new StartEndCommand(() -> STORAGE.startFeeding(), () -> STORAGE.stopFeeding(),
      STORAGE);
  public final InstantCommand startStorageOverride = new InstantCommand(() -> STORAGE.override());

  public final StoreBall storeBall = new StoreBall(STORAGE);

  // STORAGE TRIGGER
  private final StorageLimitSwitchTrigger storageTrigger = new StorageLimitSwitchTrigger(this);

  // === AUTO === //
  private final InstantCommand doNothing = new InstantCommand();
  private final SlalomCommandAuto slalom = new SlalomCommandAuto(DRIVETRAIN);
  
  private final BounceCommandAuto bounce = new BounceCommandAuto(DRIVETRAIN);
  private final TestAutoCommandGroup testAuto = new TestAutoCommandGroup(DRIVETRAIN);
  private final SequentialCommandGroup testAuto2 = new SequentialCommandGroup(new MoveCommand(DRIVETRAIN, 24, 0.5));
  private final BarrelCommandAuto barrel = new BarrelCommandAuto(DRIVETRAIN);
  // private final Command moveForward = new MoveCommand(DRIVETRAIN, 20, .5);                     // TODO: FIX THIS
  // private final TestAutoCommandGroup debugAuto = new TestAutoCommandGroup(DRIVETRAIN, VISION);
  // private final FailsafeAuto failsafe = new FailsafeAuto(DRIVETRAIN, SHOOTER, STORAGE);
  // private final TrenchAuto trench = new TrenchAuto(DRIVETRAIN, SHOOTER, STORAGE, INTAKE, VISION);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Climber
    CLIMBER.setDefaultCommand(
      new RunCommand(
        () -> {
            CLIMBER.setWinchSpeed(-operator.getRawAxis(FORWARD_AXIS_LEFT));
            CLIMBER.setHookSpeed(operator.getRawAxis(FORWARD_AXIS_RIGHT));
            System.out.println(-operator.getRawAxis(FORWARD_AXIS_LEFT)+", "+operator.getRawAxis(FORWARD_AXIS_RIGHT));
        }, 
        CLIMBER
      )
  );
  DRIVETRAIN.setDefaultCommand(
    new RunCommand(
      () -> DRIVETRAIN.arcadeDrive(driver.getRawAxis(FORWARD_AXIS_LEFT), -driver.getRawAxis(HORIZ_AXIS_RIGHT)),
      DRIVETRAIN
  ));

    // Shooter
    toggleShooterButton.toggleWhenPressed(new EnableShooter(SHOOTER, STORAGE));

    shootCloseTrigger.whileActiveOnce(shootClose);
    shootFarTrigger.whileActiveOnce(shootFar);

    // Vision
    visionAlignBall.whenHeld(new BallTrack(DRIVETRAIN, VISION));
    visionAlignGoal.whenHeld(new GoalTrack(DRIVETRAIN, VISION));

    // Drivetrain
    modeSwitchButton.whenHeld(modeSwitch);

    // Intake
    intakeButton.whenHeld(new IntakeCommand(INTAKE));

    outttakeButton.whenHeld(new OuttakeCommand(INTAKE));

    raiseIntakeButton.whenPressed(retractIntake.withTimeout(1));

    // STORAGE
    storageTrigger.whenActive(storeBall);
    storageOverrideButton.whenPressed(startStorageOverride);
    storageOverrideButton.whenHeld(storageOverride);
  }

  public void addAutosToChooser(SendableChooser<Command> chooser) {
    chooser.setDefaultOption("Do Nothing", doNothing);
    chooser.addOption("Slalom Auto", slalom);
    chooser.addOption("Barrel Auto", barrel);
    chooser.addOption("Bounce Auto", bounce);
    chooser.addOption("Test Auto", testAuto);
    chooser.addOption("Test Auto 2", testAuto2);
    // chooser.addOption("Move 20\"", moveForward);
    // chooser.addOption("Failsafe (Ram and 3 in the hole)", failsafe);
    // chooser.addOption("Trench (5 balls)", trench);
    // chooser.addOption("Test Auto", debugAuto);
  }
}
