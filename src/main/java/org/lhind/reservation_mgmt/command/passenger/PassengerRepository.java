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
package org.lhind.reservation_mgmt.command.passenger;

import org.fuin.ddd4j.ddd.EntityType;
import org.fuin.ddd4j.esrepo.EventStoreRepository;
import org.fuin.esc.api.EventStoreSync;
import org.lhind.reservation_mgmt.common.model.passenger.PassengerId;

/**
 * A repository to create, receive and updates aggregates of the type Passenger.
 */
public class PassengerRepository extends
    EventStoreRepository<PassengerId, Passenger> {

  /**
   * @param eventStore
   *          of the repository
   */
  protected PassengerRepository(EventStoreSync eventStore) {
    super(eventStore);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#getAggregateClass()
   */
  public Class<Passenger> getAggregateClass() {
    return Passenger.class;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#getAggregateType()
   */
  public EntityType getAggregateType() {
    return PassengerId.TYPE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.fuin.ddd4j.ddd.Repository#create()
   */
  public Passenger create() {
    return new Passenger();
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
