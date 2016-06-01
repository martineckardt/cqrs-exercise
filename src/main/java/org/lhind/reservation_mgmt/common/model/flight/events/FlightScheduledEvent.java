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
package org.lhind.reservation_mgmt.common.model.flight.events;

import java.time.Duration;
import java.time.LocalDateTime;

import org.fuin.ddd4j.ddd.AbstractDomainEvent;
import org.fuin.ddd4j.ddd.EntityIdPath;
import org.fuin.ddd4j.ddd.EventType;
import org.lhind.reservation_mgmt.common.model.flight.Capacity;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;

/**
 * A flight with a DEPARTURE_TIME, a route and a CAPACITY has been scheduled
 */
public class FlightScheduledEvent extends AbstractDomainEvent<FlightId> {

  /** Unique name of this event. */
  public static final EventType EVENT_TYPE = new EventType(
      "PassengerRegisteredEvent");

  private LocalDateTime departureTime;
  private Duration duration;
  private Route route;
  private Capacity capacity;

  public FlightScheduledEvent(FlightId flightId, LocalDateTime departureTime,
      Duration duration, Route route, Capacity capacity) {
    super(new EntityIdPath(flightId));
    this.departureTime = departureTime;
    this.duration = duration;
    this.route = route;
    this.capacity = capacity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Event#getEventType()
   */
  @Override
  public EventType getEventType() {
    return EVENT_TYPE;
  }

  /**
   * @return the DEPARTURE_TIME
   */
  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * @return the route
   */
  public Route getRoute() {
    return route;
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
