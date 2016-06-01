package org.lhind.reservation_mgmt.query;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.lhind.reservation_mgmt.common.model.flight.FlightId;
import org.lhind.reservation_mgmt.common.model.flight.Route;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO;
import org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight;

/**
 * @author U519643
 *
 */
public class QueryFlightDAOInMemoryImpl implements QueryFlightDAO {

  private Map<FlightId, QueryFlight> data;

  public QueryFlightDAOInMemoryImpl() {
    data = new HashMap<>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #findAll()
   */
  @Override
  public List<QueryFlight> findAll() {
    return new ArrayList<QueryFlight>(data.values());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #findById(org.lhind.reservation_mgmt.common.model.flight.FlightId)
   */
  @Override
  public QueryFlight findById(FlightId flightId) {
    return data.get(flightId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #insertFlight
   * (org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight)
   */
  @Override
  public void insertFlight(QueryFlight flight) {
    data.put(flight.getFlightId(), flight);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #updateFlight
   * (org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight)
   */
  @Override
  public void updateFlight(QueryFlight flight) {
    data.put(flight.getFlightId(), flight);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #deleteFlight
   * (org.lhind.reservation_mgmt.query.FlightSearchProjection.model.QueryFlight)
   */
  @Override
  public void deleteFlight(QueryFlight flight) {
    data.remove(flight.getFlightId());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.lhind.reservation_mgmt.query.FlightSearchProjection.dao.QueryFlightDAO
   * #findByRouteAndDate(org.lhind.reservation_mgmt.common.model.flight.Route,
   * java.time.LocalDate)
   */
  @Override
  public List<QueryFlight> findByRouteAndDate(Route route, LocalDate date) {
    return data.entrySet()
        .stream()
        // Filter flight for the route
        .filter(entry -> entry.getValue().getRoute().equals(route))
        // filter flights on the date
        .filter(
            entry -> entry.getValue().getDepartureTime().toLocalDate()
                .equals(date))
        // convert to list
        .map(e -> e.getValue()).collect(Collectors.toList());
  }

}
