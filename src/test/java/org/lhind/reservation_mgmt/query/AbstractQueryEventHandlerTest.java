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
package org.lhind.reservation_mgmt.query;

import org.fuin.objects4j.common.ContractViolationException;
import org.junit.Before;
import org.junit.Test;
import org.lhind.reservation_mgmt.DummyData;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.common.model.flight.events.FlightCancelledEvent;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.eventHandler.ConfirmedReservationCancelledEventHandler;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class AbstractQueryEventHandlerTest {
  private ConfirmedReservationCancelledEventHandler handler;
  private QueryFlightDAO dao;

  @Before
  public void setUp() throws Exception {
    dao = new QueryFlightDAOInMemoryImpl();
    handler = new ConfirmedReservationCancelledEventHandler(dao);

    QueryFlight testFlight = new QueryFlight(DummyData.FLIGHT_ID, new Route(
        "HAM", "FRA"), DummyData.DEPARTURE_TIME, DummyData.ARRIVAL_TIME,
        DummyData.CAPACITY.getValue());
    dao.insertFlight(testFlight);
  }

  @Test(expected = ContractViolationException.class)
  public void testHandleFailsWithWrongEventType() {
    // Prepare
    FlightCancelledEvent event = new FlightCancelledEvent(DummyData.FLIGHT_ID);

    // Test
    handler.handle(event);
  }
}
