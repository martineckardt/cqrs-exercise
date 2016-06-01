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

import org.lhind.reservation_mgmt.common.model.flight.events.FlightCancelledEvent;
import org.lhind.reservation_mgmt.query.AbstractQueryEventHandler;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class FlightCancelledEventHandler extends
    AbstractQueryEventHandler<FlightCancelledEvent> {

  private QueryFlightDAO queryFlightDAO;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.AbstractQueryEventHandler#getEventClass()
   */
  @Override
  protected Class<FlightCancelledEvent> getEventClass() {
    return FlightCancelledEvent.class;
  }

  /**
   * Handle the event and update the query model
   */
  protected void handleEvent(FlightCancelledEvent event) {

    QueryFlight queryFlight = queryFlightDAO.findById(event.getEntityId());

    // delete the queryFlight
    queryFlightDAO.deleteFlight(queryFlight);
  }
}
