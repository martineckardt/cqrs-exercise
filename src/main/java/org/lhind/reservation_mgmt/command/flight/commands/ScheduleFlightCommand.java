/**
 * Copyright (C) 2015 Lufthansa Industry Solutions AS GmbH. All rights reserved. 
 * <http://www.lhind.org/>
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.lhind.reservation_mgmt.command.flight.commands;

import java.time.Duration;
import java.time.LocalDateTime;

import org.fuin.cqrs4j.AbstractCommand;
import org.lhind.reservation_mgmt.common.model.flight.Capacity;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;

/**
 * Schedule a flight at the given departure time on the given route with the
 * capacity
 *
 */
public class ScheduleFlightCommand extends AbstractCommand {

  private FlightId flightId;
  private Route route;
  private LocalDateTime departureTime;
  private Duration duration;
  private Capacity capacity;

  public ScheduleFlightCommand(FlightId flightId, Route route,
      LocalDateTime departureTime, Duration duration, Capacity capacity) {
    super();
    this.flightId = flightId;
    this.route = route;
    this.departureTime = departureTime;
    this.duration = duration;
    this.capacity = capacity;
  }

  /**
   * @return the FLIGHT_ID
   */
  public FlightId getFlightId() {
    return flightId;
  }

  /**
   * @return the route
   */
  public Route getRoute() {
    return route;
  }

  /**
   * @return the DEPARTURE_TIME
   */
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * @return the CAPACITY
   */
  public Capacity getCapacity() {
    return capacity;
  }

  /**
   * @return the DURATION
   */
  public Duration getDuration() {
    return duration;
  }

}
