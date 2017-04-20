package com.mapbox.services.api.directions.v5.models;

import java.util.Arrays;

/**
 * An annotations object that contains additional details about each line segment along the route geometry. Each entry
 * in an annotations field corresponds to a coordinate along the route geometry.
 *
 * @since 2.1.0
 */
public class LegAnnotation {

  private double[] distance;
  private double[] duration;
  private double[] speed;

  public LegAnnotation() {
  }

  /**
   * The distance, in meters, between each pair of coordinates.
   *
   * @return a double array with each entry being a distance value between two of the routeLeg geometry coordinates.
   * @since 2.1.0
   */
  public double[] getDistance() {
    return distance;
  }

  /**
   * The duration, in seconds, between each pair of coordinates.
   *
   * @return a double array with each entry being a duration value between two of the routeLeg geometry coordinates.
   * @since 2.1.0
   */
  public double[] getDuration() {
    return duration;
  }

  /**
   * The speed, in km/h, between each pair of coordinates.
   *
   * @return a double array with each entry being a speed value between two of the routeLeg geometry coordinates.
   * @since 2.1.0
   */
  public double[] getSpeed() {
    return speed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LegAnnotation that = (LegAnnotation) o;

    if (!Arrays.equals(getDistance(), that.getDistance())) {
      return false;
    }
    if (!Arrays.equals(getDuration(), that.getDuration())) {
      return false;
    }
    return Arrays.equals(getSpeed(), that.getSpeed());
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + Arrays.hashCode(getDistance());
    result = 31 * result + Arrays.hashCode(getDuration());
    result = 31 * result + Arrays.hashCode(getSpeed());
    return result;
  }

  @Override
  public String toString() {
    return "LegAnnotation{"
      + "distance=" + Arrays.toString(distance)
      + ", duration=" + Arrays.toString(duration)
      + ", speed=" + Arrays.toString(speed)
      + '}';
  }
}
