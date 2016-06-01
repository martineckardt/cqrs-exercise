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
package org.lhind.reservation_mgmt.command.flight;

import org.fuin.ddd4j.ddd.EntityType;
import org.fuin.ddd4j.esrepo.EventStoreRepository;
import org.fuin.esc.api.EventStoreSync;
import org.lhind.reservation_mgmt.common.model.flight.FlightId;

/**
 * A repository to create, receive and updates aggregates of the type Flight.
 */
public class FlightRepository extends EventStoreRepository<FlightId, Flight> {

  /**
   * @param eventStore
   *          of the repository
   */
  protected FlightRepository(EventStoreSync eventStore) {
    super(eventStore);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#getAggregateClass()
   */
  public Class<Flight> getAggregateClass() {
    return Flight.class;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#getAggregateType()
   */
  public EntityType getAggregateType() {
    return FlightId.TYPE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#create()
   */
  public Flight create() {
    return new Flight();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.esrepo.EventStoreRepository#getIdParamName()
   */
  @Override
  protected String getIdParamName() {
    return "orderId";
  }

}
