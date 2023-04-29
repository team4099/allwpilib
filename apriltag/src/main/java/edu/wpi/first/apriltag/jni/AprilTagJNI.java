// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.apriltag.jni;

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagDetector;
import edu.wpi.first.apriltag.AprilTagPoseEstimate;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.util.RuntimeLoader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AprilTagJNI {
  static boolean libraryLoaded = false;

  static RuntimeLoader<AprilTagJNI> loader = null;

  public static class Helper {
    private static AtomicBoolean extractOnStaticLoad = new AtomicBoolean(true);

    public static boolean getExtractOnStaticLoad() {
      return extractOnStaticLoad.get();
    }

    public static void setExtractOnStaticLoad(boolean load) {
      extractOnStaticLoad.set(load);
    }
  }

  static {
    if (Helper.getExtractOnStaticLoad()) {
      try {
        loader =
            new RuntimeLoader<>(
                "apriltagjni", RuntimeLoader.getDefaultExtractionRoot(), AprilTagJNI.class);
        loader.loadLibrary();
      } catch (IOException ex) {
        ex.printStackTrace();
        System.exit(1);
      }
      libraryLoaded = true;
    }
  }

  public static native long createDetector();

  public static native void destroyDetector(long det);

  public static native void setDetectorConfig(long det, AprilTagDetector.Config config);

  public static native AprilTagDetector.Config getDetectorConfig(long det);

  public static native void setDetectorQTP(
      long det, AprilTagDetector.QuadThresholdParameters params);

  public static native AprilTagDetector.QuadThresholdParameters getDetectorQTP(long det);

  public static native boolean addFamily(long det, String fam, int bitsCorrected);

  public static native void removeFamily(long det, String fam);

  public static native void clearFamilies(long det);

  public static native AprilTagDetection[] detect(
      long det, int width, int height, int stride, long bufAddr);

  public static native Transform3d estimatePoseHomography(
      double[] homography, double tagSize, double fx, double fy, double cx, double cy);

  public static native AprilTagPoseEstimate estimatePoseOrthogonalIteration(
      double[] homography,
      double[] corners,
      double tagSize,
      double fx,
      double fy,
      double cx,
      double cy,
      int nIters);

  public static native Transform3d estimatePose(
      double[] homography,
      double[] corners,
      double tagSize,
      double fx,
      double fy,
      double cx,
      double cy);
}
