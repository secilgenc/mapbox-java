package com.mapbox.services.api.mapmatching.v5.models;

import com.google.gson.annotations.SerializedName;
import com.mapbox.services.api.directions.v5.models.DirectionsWaypoint;

/**
 * A tracepoint object is {@link DirectionsWaypoint} object with two additional fields.
 */
public class MapMatchingTracepoint extends DirectionsWaypoint {

  @SerializedName("matchings_index")
  private int matchingsIndex;

  @SerializedName("waypoint_index")
  private int waypointIndex;

  public MapMatchingTracepoint() {
  }

  /**
   * Index to the match object in matchings the sub-trace was matched to.
   *
   * @return index value
   */
  public int getMatchingsIndex() {
    return matchingsIndex;
  }

  /**
   * Index value
   *
   * @param matchingsIndex value
   */
  public void setMatchingsIndex(int matchingsIndex) {
    this.matchingsIndex = matchingsIndex;
  }

  /**
   * Index of the waypoint inside the matched route.
   *
   * @return index value
   */
  public int getWaypointIndex() {
    return waypointIndex;
  }

  /**
   * Index value
   *
   * @param waypointIndex value
   */
  public void setWaypointIndex(int waypointIndex) {
    this.waypointIndex = waypointIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    MapMatchingTracepoint that = (MapMatchingTracepoint) o;

    if (getMatchingsIndex() != that.getMatchingsIndex()) {
      return false;
    }
    return getWaypointIndex() == that.getWaypointIndex();

  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + getMatchingsIndex();
    result = 31 * result + getWaypointIndex();
    return result;
  }

  @Override
  public String toString() {
    return "MapMatchingTracepoint{"
      + "matchingsIndex=" + matchingsIndex
      + ", waypointIndex=" + waypointIndex
      + '}';
  }
}
