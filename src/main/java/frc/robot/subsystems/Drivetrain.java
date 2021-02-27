// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  
  private CANSparkMax leftLeader;
  private CANSparkMax rightLeader;
  private CANSparkMax [] leftFollowers;
  private CANSparkMax [] rightFollowers;
  SpeedControllerGroup leftMotors;
  SpeedControllerGroup rightMotors;
  DifferentialDrive differentialDrive;

  public Drivetrain() {
    // Init Left Leader
    leftLeader  = new CANSparkMax(Constants.kLeftLeader, MotorType.kBrushed);
    // Init Right Leader
    rightLeader  = new CANSparkMax(Constants.kRightLeader, MotorType.kBrushed);

    // init left followers
    leftFollowers = new CANSparkMax[Constants.kLeftFollowers.length];
    for(int i = 0; i < leftFollowers.length; ++i) {
        leftFollowers[i] = new CANSparkMax(Constants.kLeftFollowers[i], MotorType.kBrushed);
    }
    //init right followers
    rightFollowers = new CANSparkMax[Constants.kRightFollowers.length];
    for(int i = 0; i < rightFollowers.length; ++i) {
        rightFollowers[i] = new CANSparkMax(Constants.kRightFollowers[i], MotorType.kBrushed);
    }
    leftMotors = new SpeedControllerGroup(leftLeader, leftFollowers);
    rightMotors = new SpeedControllerGroup(rightLeader, rightFollowers);
    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  public void set(double leftspeed, double rightspeed){
    leftMotors.set(leftspeed);
    rightMotors.set(rightspeed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
