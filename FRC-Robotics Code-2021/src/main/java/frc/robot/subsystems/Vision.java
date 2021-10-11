/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Video;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

import static frc.robot.Constants.*;

/**
 * COMPETITION READY
 * 
 * All vision code for PixyCam2.
 */
public class Vision extends SubsystemBase {

  // FIELDS
  Pixy2 pixy;
  Pixy2CCC pixyCCC;
  Pixy2Video pixyVideo;

  /**
   * Used for goal speeds to smooth.
   */
  double[] avg = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

  public Vision() {
    pixy = Pixy2.createInstance(new SPILink());
    pixy.init();
    pixyCCC = pixy.getCCC();
    pixyVideo = pixy.getVideo();

    // 0- off, 1- on
    // First param is headlamps (white), second is RGB LED
    pixy.setLamp((byte) 0, (byte) 1);
  }

  /**
   * Gets blocks of type [Constants.Signature]
   */
  public List<Block> getBlocksOfType(int signature) {
    List<Block> output = new ArrayList<>();
    pixyCCC.getBlocks(false, signature, 25);

    for (Block b : pixyCCC.getBlocks()) {
      output.add(b);
    }

    return output;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Returns % voltage output to directly plug into tankDrive() to align with
   * largest object. Please ensure you check for -1000, as this is the error value
   * for not detecting anything.
   * 
   * @param sig        Targeted object of type [Constants.Signature]
   * @param isVertical Returns either the horizontal or vertical speed.
   * 
   * @return % voltage [-0.5, 0.5]
   */
  public double getPIDOfBlock(int sig, boolean isVertical) {
    double coordinateX;
    double coordinateY;
    List<Block> blocks = getBlocksOfType(sig);

    if (sig == Signature.POWER_CELL.value())
      pixy.setLED(Color.YELLOW);
    else if (sig == Signature.GOAL_BOTTOM_LINE.value())
      pixy.setLED(Color.GREEN);
    else
      pixy.setLED(255, 255, 255);

    if (blocks.size() > 0) {
      Block block = null;

      block = blocks.get(0);
      for (Block b : blocks) {
        if ((b.getWidth() * b.getHeight()) > (block.getWidth() * block.getHeight()))
          block = b;
      }

      coordinateX = block.getX();
      coordinateY = block.getY();

      return isVertical ? ((2 * coordinateY / pixy.getFrameHeight()) - 1) / 2
          : ((2 * coordinateX / pixy.getFrameWidth()) - 1) / 2;
    }

    return -1000;
  }

  /**
   * Finds the position of the goal (center vertical line).
   * 
   * @return Speed for drivetrain [-0.5, 0.5]
   */
  public double getPIDOfGoal() {
    for (int i = 9; i > avg.length; i--) {
      avg[i] = avg[i - 1];
    }

    double newSpeed = getPIDOfBlock(Signature.GOAL_BOTTOM_LINE.value(), false);
    avg[0] = (newSpeed == -1000 ? 0 : newSpeed);

    double average = 0;
    for (int i = 0; i < avg.length; i++) {
      if (avg[i] == 0)
        continue;

      average += avg[i];
    }

    return average / avg.length;
  }

}
