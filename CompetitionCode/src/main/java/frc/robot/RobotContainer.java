
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


import frc.robot.subsystems.*;

public class RobotContainer {

    // IMPORTING STUFF AND STUFF
    private final RobotCommands robotCommands = new RobotCommands();


    // == JOYSTICK & BUTTON BINDINGS == //


    // NEW JOYSTICK
    public final Joystick opController = new Joystick(OPERATOR_CONTROLLER);

    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)
    // CLIMBER BUTTONS
    private final JoystickButton 
                                 // CONTROL PANEL BUTTONS
                                 controlSpinButton = new JoystickButton(opController, RIGHT_STICK_BUTTON),
                                 controlLiftButton = new JoystickButton(opController, Y),
                                 controlMatchButton = new JoystickButton(opController, MATCH_CONTROL);

                              
                                 

   
   

    // ROBOT CONTAINER
    public RobotContainer() {
        configureButtonActions();
    }

    // CONFIG BUTTON ACTIONS
    private void configureButtonActions() {

        
        // TODO: make a control spin times counter?

        controlSpinButton.whenHeld(robotCommands.spinHalfEitherEight);
        controlMatchButton.whenHeld(robotCommands.controlSpinIfNoMatch);
        controlLiftButton.whenPressed(robotCommands.liftControlMaybe);

       
        
    }

    
   
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
      // random commmand cos i need something here
      return robotCommands.controlStop;
    }
}