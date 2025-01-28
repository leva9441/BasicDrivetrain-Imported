// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;
  private final DoubleSupplier speedSupFB;
  private final DoubleSupplier speedSupT;
  private final DoubleSupplier speedOutputPercentage;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DrivetrainCommand(DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier speedSupFB, DoubleSupplier speedSupT, DoubleSupplier speedOutputPercentage) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.speedSupFB = speedSupFB;
    this.speedSupT = speedSupT;
    this.speedOutputPercentage = speedOutputPercentage;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double currentSpeedFB = MathUtil.applyDeadband(speedSupFB.getAsDouble(), Constants.MotorConstants.joystickDeadband);
    double currentSpeedT = MathUtil.applyDeadband(speedSupT.getAsDouble(), Constants.MotorConstants.joystickDeadband);
    double currentSpeedOP = -0.5 * (MathUtil.applyDeadband(speedOutputPercentage.getAsDouble(), Constants.MotorConstants.joystickDeadband)) + Constants.MotorConstants.drivetrainOutput;

    drivetrainSubsystem.arcadeDrive(currentSpeedFB * currentSpeedOP, currentSpeedT * currentSpeedOP, currentSpeedOP);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
