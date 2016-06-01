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
package org.lhind.reservation_mgmt.query.FlightSearchProjection.eventHandler;

import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.events.FlightScheduledEvent;
import org.lhind.reservation_mgmt.query.AbstractQueryEventHandler;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class FlightScheduledEventHandler extends
    AbstractQueryEventHandler<FlightScheduledEvent> {

  private QueryFlightDAO queryFlightDAO;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.AbstractQueryEventHandler#getEventClass()
   */
  @Override
  protected Class<FlightScheduledEvent> getEventClass() {
    return FlightScheduledEvent.class;
  }

  /**
   * Handle the event and update the query model
   */
  protected void handleEvent(FlightScheduledEvent event) {

    LocalDateTime arrivalTime = event.getDepartureTime().plus(
        event.getDuration());
    int availableSeats = event.getCapacity().getValue();

    QueryFlight queryFlight = new QueryFlight(event.getEntityId(),
        event.getRoute(), event.getDepartureTime(), arrivalTime, availableSeats);

    // insert the queryFlight
    queryFlightDAO.insertFlight(queryFlight);
  }
}
