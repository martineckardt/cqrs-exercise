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
package org.lhind.reservation_mgmt.query.AirportScheduleProjection.eventHandlers;

import java.time.LocalDateTime;

import org.lhind.reservation_mgmt.common.model.flight.events.FlightScheduledEvent;
import org.lhind.reservation_mgmt.query.AbstractQueryEventHandler;
import org.lhind.reservation_mgmt.query.AirportScheduleProjection.dao.AirportScheduleDAO;
import org.lhind.reservation_mgmt.query.AirportScheduleProjection.model.AirportSchedule;
import org.lhind.reservation_mgmt.query.AirportScheduleProjection.model.IncomingFlight;
import org.lhind.reservation_mgmt.query.AirportScheduleProjection.model.OutgoingFlight;

/**
 * @author U519643
 *
 */
public class FlightScheduledEventHandler extends
    AbstractQueryEventHandler<FlightScheduledEvent> {

  private AirportScheduleDAO airportScheduleDAO;

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

  protected void handleEvent(FlightScheduledEvent event) {
    String originCode = event.getRoute().getOriginCode();
    String destinationCode = event.getRoute().getDestinationCode();

    // Add to outgoing flights of Origin
    AirportSchedule scheduleOfOrigin = airportScheduleDAO
        .findByAirportCodeAndDate(event.getRoute().getOriginCode(), event
            .getDepartureTime().toLocalDate());

    OutgoingFlight outgoingFlight = new OutgoingFlight(event.getEntityId(),
        event.getDepartureTime(), destinationCode);
    scheduleOfOrigin.addOutgoingFlight(outgoingFlight);

    airportScheduleDAO.updateSchedule(scheduleOfOrigin);

    // Add to incoming flights of destination
    LocalDateTime arrivalTime = event.getDepartureTime().plus(
        event.getDuration());

    AirportSchedule scheduleOfDestination = airportScheduleDAO
        .findByAirportCodeAndDate(event.getRoute().getOriginCode(),
            arrivalTime.toLocalDate());

    IncomingFlight incomingFlight = new IncomingFlight(event.getEntityId(),
        arrivalTime, originCode);
    scheduleOfDestination.addIncomingFlight(incomingFlight);

    airportScheduleDAO.updateSchedule(scheduleOfDestination);

  }
}
