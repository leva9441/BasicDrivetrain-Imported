// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
 import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  
  private final Joystick weapons = new Joystick(Constants.OperatorConstants.driveControllerPort);

  //NEW IDEA! make axisTDrive = kLeftY and make outPerc = left trigger axis
  private final int axisFBDrive = XboxController.Axis.kRightY.value;
  private final int axisTDrive = XboxController.Axis.kRightX.value;
  private final int outPerc = XboxController.Axis.kLeftY.value;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drivetrainSubsystem.setDefaultCommand(
      new DrivetrainCommand(drivetrainSubsystem, 
      () -> -weapons.getRawAxis(axisFBDrive),
      () -> -weapons.getRawAxis(axisTDrive),
      () -> -weapons.getRawAxis(outPerc)
      )
    );
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {

  }

  
  public Command getAutonomousCommand() {
    return null;
  }
}
