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

import org.fuin.cqrs4j.EventHandler;
import org.fuin.ddd4j.ddd.Event;
import org.fuin.ddd4j.ddd.EventType;
import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.common.ContractViolationException;

/**
 * Abstract event handler for the projection
 * 
 * TODO think of a better way to check the event class and change EventHandler
 * interface to generic type
 *
 */
public abstract class AbstractQueryEventHandler<T extends Event> implements
    EventHandler {

  /**
   * @return
   */
  abstract protected Class<T> getEventClass();

  /**
   * handles checked event
   * 
   * @param event
   *          of type T
   */
  abstract protected void handleEvent(T event);

  /**
   * Checks if the event type of the event type is the specified event type of
   * the handler
   * 
   * @param event
   */
  protected void assertEventType(final Event event) {
    Contract.requireArgNotNull("event", event);
    if (!getEventType().equals(event.getEventType())) {
      throw new ContractViolationException("The event has the wrong type: "
          + event.getEventType());
    }
    if (!getEventClass().isAssignableFrom(event.getClass())) {
      throw new ContractViolationException("The event has wrong class: "
          + event.getClass().getName());
    }
  }

  @Override
  public EventType getEventType() {
    try {
      return (EventType) this.getEventClass().getField("EVENT_TYPE").get(null);
    } catch (IllegalArgumentException | IllegalAccessException
        | NoSuchFieldException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Handles unchecked event
   */
  public void handle(Event event) {
    assertEventType(event);

    final T typedEvent = (T) event;
    handleEvent(typedEvent);
  }

}
