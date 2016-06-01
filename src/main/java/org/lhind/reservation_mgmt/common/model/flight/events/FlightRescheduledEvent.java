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
import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * @author U519643
 *
 */
public class FlightRescheduledEvent extends AbstractDomainEvent<FlightId> {

  /** Unique name of this event. */
  public static final EventType EVENT_TYPE = new EventType(
      "FlightRescheduledEvent");

  private LocalDateTime newDepartureTime;
  private Duration newDuration;

  public FlightRescheduledEvent(FlightId flightId, LocalDateTime date,
      Duration newDuration) {
    super(new EntityIdPath(flightId));
    this.newDepartureTime = date;
    this.newDuration = newDuration;
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
   * @return the newDepartureTime
   */
  public LocalDateTime getNewDepartureTime() {
    return newDepartureTime;
  }

  /**
   * @return the newDuration
   */
  public Duration getNewDuration() {
    return newDuration;
  }

}