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
package org.lhind.reservation_mgmt.query.FlightSearchProjection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fuin.objects4j.common.Contract;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.FlightSearchResult;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.FlightSearchResultItem;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * Handles all the queries for the Flight Search Projection. Interface to the
 * client and other integrated systems
 *
 */
public class FlightSearchQueryHandler {

  private QueryFlightDAO dao;
  private FlightPricingService pricingService;

  public FlightSearchResult findFlightsByRouteAndDate(Route route,
      LocalDate date, int numberOfSeats) {
    // Authorization
    // ...

    // Validate parameters
    Contract.requireArgNotNull("route", route);
    Contract.requireArgMin("numberOfSeats", numberOfSeats, 1);
    Contract.requireArgMax("numberOfSeats", numberOfSeats, 10);

    // query read model
    List<QueryFlight> flights = dao.findByRouteAndDate(route, date);

    // convert model to search result items
    List<FlightSearchResultItem> items = new ArrayList<>();
    for (QueryFlight flight : flights) {
      // calculating simple value
      boolean seatsAvailable = (numberOfSeats <= flight
          .getNumberOfAvailableSeats());
      int price = pricingService.getPrice(flight.getFlightId());

      FlightSearchResultItem item = new FlightSearchResultItem(
          flight.getFlightId(), flight.getDepartureTime(),
          flight.getArrivalTime(), seatsAvailable, price);
      items.add(item);
    }
    FlightSearchResult result = new FlightSearchResult(items);

    // return result
    return result;
  }
}
