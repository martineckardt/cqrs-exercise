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

import org.lhind.reservation_mgmt.common.model.flight.events.FlightRescheduledEvent;
import org.lhind.reservation_mgmt.query.AbstractQueryEventHandler;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class FlightRescheduledEventHandler extends
    AbstractQueryEventHandler<FlightRescheduledEvent> {

  private QueryFlightDAO queryFlightDAO;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.AbstractQueryEventHandler#getEventClass()
   */
  @Override
  protected Class<FlightRescheduledEvent> getEventClass() {
    return FlightRescheduledEvent.class;
  }

  protected void handleEvent(FlightRescheduledEvent event) {

    QueryFlight queryFlight = queryFlightDAO.findById(event.getEntityId());

    queryFlight.reschedule(event.getNewDepartureTime(), event.getNewDuration());

    queryFlightDAO.updateFlight(queryFlight);
  }
}
