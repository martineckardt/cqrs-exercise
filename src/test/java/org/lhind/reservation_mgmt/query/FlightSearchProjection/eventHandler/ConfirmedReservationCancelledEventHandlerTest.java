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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.lhind.reservation_mgmt.DummyData;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.common.model.flight.events.ConfirmedReservationCancelledEvent;
import org.lhind.reservation_mgmt.query.QueryFlightDAOInMemoryImpl;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class ConfirmedReservationCancelledEventHandlerTest {

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

  @Test
  public void testHandle() {
    // Prepare
    ConfirmedReservationCancelledEvent event = new ConfirmedReservationCancelledEvent(
        DummyData.FLIGHT_ID, DummyData.PASSENGER_ID_1);

    // Test
    handler.handle(event);

    // Verify
    QueryFlight flight = dao.findById(DummyData.FLIGHT_ID);
    assertThat(flight.getNumberOfAvailableSeats()).isEqualTo(3);
  }
}
